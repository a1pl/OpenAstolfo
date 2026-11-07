package astolfo.lua.vm;

import com.github.esrrhs.javalua.HostBridge;
import com.github.esrrhs.javalua.Lua;
import com.github.esrrhs.javalua.LuaState;

/** Conversions and calls between the LuaJ-shaped API and javalua. */
final class Interop {
   private static final ThreadLocal<LuaState> RUNNING = new ThreadLocal<>();
   private static LuaState detached;

   static {
      // Scripts only get the pure-Java runtime; javalua's optional JNI shim is never loaded.
      System.setProperty("javalua.nonative", "true");
   }

   private Interop() {
   }

   static LuaState newState() {
      return new LuaState();
   }

   static LuaValue fromJl(com.github.esrrhs.javalua.LuaValue v, LuaState owner) {
      switch (Lua.noVariant(v.tt)) {
         case Lua.TNIL:
            return LuaValue.NIL;
         case Lua.TBOOLEAN:
            return LuaValue.valueOf(v.toBoolean());
         case Lua.TNUMBER:
            return v.isInteger() ? LuaInteger.valueOf(v.i) : LuaDouble.valueOf(v.n);
         case Lua.TSTRING:
            return LuaString.wrap(v.asString());
         case Lua.TTABLE:
            return new LuaTable(v.asTable(), owner);
         case Lua.TFUNCTION:
            if (v.o instanceof HostFunction) {
               return ((HostFunction)v.o).target;
            }

            return new ScriptFunction(new com.github.esrrhs.javalua.LuaValue(v), owner);
         default:
            return new LuaOpaque(new com.github.esrrhs.javalua.LuaValue(v));
      }
   }

   /** Calls a Lua-side function from Java; script errors surface as {@link LuaError}. */
   static Varargs call(com.github.esrrhs.javalua.LuaValue fn, LuaState owner, Varargs args) {
      LuaState running = RUNNING.get();
      LuaState main = owner != null ? owner : (running != null ? HostBridge.mainThread(running) : detachedState());
      LuaState L = running != null && HostBridge.mainThread(running) == main ? running : main;
      synchronized (main) {
         int base = HostBridge.top(L);
         L.push(fn);
         for (int i = 1, n = args.narg(); i <= n; i++) {
            L.push(args.arg(i).toJl());
         }

         com.github.esrrhs.javalua.LuaValue err;
         try {
            err = L.pcall(base, LuaState.MULTRET);
         } catch (RuntimeException runtimeException) {
            HostBridge.restoreTop(L, base);
            throw new LuaError(runtimeException);
         }

         if (err != null) {
            String message = err.toString();
            HostBridge.restoreTop(L, base);
            throw new LuaError(message);
         }

         int count = HostBridge.top(L) - base;
         LuaValue[] results = new LuaValue[count];
         for (int i = 0; i < count; i++) {
            results[i] = fromJl(L.at(base + i), main);
         }

         HostBridge.restoreTop(L, base);
         return LuaValue.varargsOf(results);
      }
   }

   static LuaState enter(LuaState L) {
      LuaState previous = RUNNING.get();
      RUNNING.set(L);
      return previous;
   }

   static void exit(LuaState previous) {
      if (previous == null) {
         RUNNING.remove();
      } else {
         RUNNING.set(previous);
      }
   }

   static com.github.esrrhs.javalua.LuaError vmError(String message) {
      return new com.github.esrrhs.javalua.LuaError(
         com.github.esrrhs.javalua.LuaValue.ofString(com.github.esrrhs.javalua.LuaString.utf8(String.valueOf(message)))
      );
   }

   /** Runs functions that arrive with no known state, e.g. ones stored into Java-made tables. */
   private static synchronized LuaState detachedState() {
      if (detached == null) {
         detached = newState();
      }

      return detached;
   }

   /** javalua's view of a Java-defined function. */
   static final class HostFunction implements com.github.esrrhs.javalua.LuaFunction {
      final LuaFunction target;

      HostFunction(LuaFunction target) {
         this.target = target;
      }

      @Override
      public int call(LuaState L) {
         LuaState main = HostBridge.mainThread(L);
         int n = L.argCount();
         LuaValue[] args = new LuaValue[n];
         for (int i = 0; i < n; i++) {
            args[i] = fromJl(L.arg(i), main);
         }

         LuaState previous = enter(L);
         Varargs result;
         try {
            result = this.target.invoke(LuaValue.varargsOf(args));
         } catch (LuaError luaError) {
            throw vmError(luaError.getMessage());
         } catch (RuntimeException runtimeException) {
            if (HostBridge.isVmUnwind(runtimeException)) {
               throw runtimeException;
            }

            throw vmError(runtimeException.toString());
         } finally {
            exit(previous);
         }

         int count = result.narg();
         for (int i = 1; i <= count; i++) {
            L.push(result.arg(i).toJl());
         }

         return count;
      }
   }
}
