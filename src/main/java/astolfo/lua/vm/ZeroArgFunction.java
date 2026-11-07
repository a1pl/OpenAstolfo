package astolfo.lua.vm;

public abstract class ZeroArgFunction extends LibFunction {
   @Override
   public abstract LuaValue call();

   @Override
   public LuaValue call(LuaValue a) {
      return this.call();
   }

   @Override
   public LuaValue call(LuaValue a, LuaValue b) {
      return this.call();
   }

   @Override
   public LuaValue call(LuaValue a, LuaValue b, LuaValue c) {
      return this.call();
   }

   @Override
   public Varargs invoke(Varargs args) {
      return this.call();
   }
}
