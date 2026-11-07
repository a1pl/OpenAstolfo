package astolfo.lua.vm;

public abstract class ThreeArgFunction extends LibFunction {
   @Override
   public abstract LuaValue call(LuaValue a, LuaValue b, LuaValue c);

   @Override
   public LuaValue call() {
      return this.call(NIL, NIL, NIL);
   }

   @Override
   public LuaValue call(LuaValue a) {
      return this.call(a, NIL, NIL);
   }

   @Override
   public LuaValue call(LuaValue a, LuaValue b) {
      return this.call(a, b, NIL);
   }

   @Override
   public Varargs invoke(Varargs args) {
      return this.call(args.arg1(), args.arg(2), args.arg(3));
   }
}
