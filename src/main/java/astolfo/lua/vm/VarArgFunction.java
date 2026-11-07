package astolfo.lua.vm;

/** Subclasses override either {@link #invoke} or {@link #onInvoke}, as in LuaJ. */
public abstract class VarArgFunction extends LibFunction {
   @Override
   public LuaValue call() {
      return this.invoke(NONE).arg1();
   }

   @Override
   public LuaValue call(LuaValue a) {
      return this.invoke(a).arg1();
   }

   @Override
   public LuaValue call(LuaValue a, LuaValue b) {
      return this.invoke(varargsOf(a, b)).arg1();
   }

   @Override
   public LuaValue call(LuaValue a, LuaValue b, LuaValue c) {
      return this.invoke(varargsOf(a, b, c)).arg1();
   }

   @Override
   public Varargs invoke(Varargs args) {
      return this.onInvoke(args).eval();
   }

   public Varargs onInvoke(Varargs args) {
      return this.invoke(args);
   }
}
