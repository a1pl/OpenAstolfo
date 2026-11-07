package astolfo.lua.vm;

import com.github.esrrhs.javalua.LuaState;

/** A function living in a Lua state (a script closure or a stdlib builtin). */
final class ScriptFunction extends LuaFunction {
   private final com.github.esrrhs.javalua.LuaValue fn;
   private final LuaState owner;

   ScriptFunction(com.github.esrrhs.javalua.LuaValue fn, LuaState owner) {
      this.fn = fn;
      this.owner = owner;
   }

   @Override
   com.github.esrrhs.javalua.LuaValue toJl() {
      return new com.github.esrrhs.javalua.LuaValue(this.fn);
   }

   @Override
   public boolean isclosure() {
      return true;
   }

   @Override
   public Varargs invoke(Varargs args) {
      return Interop.call(this.fn, this.owner, args);
   }

   @Override
   public boolean raweq(LuaValue other) {
      return other instanceof ScriptFunction && ((ScriptFunction)other).fn.o == this.fn.o;
   }

   @Override
   public boolean equals(Object o) {
      return o instanceof LuaValue && this.raweq((LuaValue)o);
   }

   @Override
   public int hashCode() {
      return System.identityHashCode(this.fn.o);
   }
}
