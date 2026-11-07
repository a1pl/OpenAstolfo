package astolfo.lua.vm;

public abstract class LuaFunction extends LuaValue {
   @Override
   public int type() {
      return TFUNCTION;
   }

   @Override
   public boolean isfunction() {
      return true;
   }

   @Override
   public LuaFunction checkfunction() {
      return this;
   }

   @Override
   public String tojstring() {
      return "function: 0x" + Integer.toHexString(System.identityHashCode(this));
   }
}
