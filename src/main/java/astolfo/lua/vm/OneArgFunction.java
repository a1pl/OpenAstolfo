package astolfo.lua.vm;

public abstract class OneArgFunction extends LibFunction {
   @Override
   public abstract LuaValue call(LuaValue a);

   @Override
   public LuaValue call() {
      return this.call(NIL);
   }

   @Override
   public LuaValue call(LuaValue a, LuaValue b) {
      return this.call(a);
   }

   @Override
   public LuaValue call(LuaValue a, LuaValue b, LuaValue c) {
      return this.call(a);
   }

   @Override
   public Varargs invoke(Varargs args) {
      return this.call(args.arg1());
   }
}
