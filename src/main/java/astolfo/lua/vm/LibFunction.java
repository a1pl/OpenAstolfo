package astolfo.lua.vm;

/** Base for functions implemented in Java; dispatches by argument count like LuaJ. */
public abstract class LibFunction extends LuaFunction {
   protected int opcode;
   protected String name;
   private Interop.HostFunction host;

   @Override
   com.github.esrrhs.javalua.LuaValue toJl() {
      if (this.host == null) {
         this.host = new Interop.HostFunction(this);
      }

      return com.github.esrrhs.javalua.LuaValue.ofFunction(this.host);
   }

   @Override
   public LuaValue call() {
      return argerror(1, "value expected");
   }

   @Override
   public LuaValue call(LuaValue a) {
      return this.call();
   }

   @Override
   public LuaValue call(LuaValue a, LuaValue b) {
      return this.call(a);
   }

   @Override
   public LuaValue call(LuaValue a, LuaValue b, LuaValue c) {
      return this.call(a, b);
   }

   @Override
   public Varargs invoke(Varargs args) {
      switch (args.narg()) {
         case 0:
            return this.call();
         case 1:
            return this.call(args.arg1());
         case 2:
            return this.call(args.arg1(), args.arg(2));
         default:
            return this.call(args.arg1(), args.arg(2), args.arg(3));
      }
   }

   @Override
   public String tojstring() {
      return this.name != null ? "function: " + this.name : super.tojstring();
   }
}
