package astolfo.lua.vm;

public abstract class TwoArgFunction extends LibFunction {
   @Override
   public abstract LuaValue call(LuaValue a, LuaValue b);

   @Override
   public LuaValue call() {
      return this.call(NIL, NIL);
   }

   @Override
   public LuaValue call(LuaValue a) {
      return this.call(a, NIL);
   }

   @Override
   public LuaValue call(LuaValue a, LuaValue b, LuaValue c) {
      return this.call(a, b);
   }

   @Override
   public Varargs invoke(Varargs args) {
      return this.call(args.arg1(), args.arg(2));
   }
}
