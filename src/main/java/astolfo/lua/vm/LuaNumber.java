package astolfo.lua.vm;

public abstract class LuaNumber extends LuaValue {
   @Override
   public int type() {
      return TNUMBER;
   }

   @Override
   public boolean isnumber() {
      return true;
   }

   /** Numbers coerce to strings, as in LuaJ. */
   @Override
   public boolean isstring() {
      return true;
   }

   @Override
   public LuaValue tonumber() {
      return this;
   }

   @Override
   public double checkdouble() {
      return this.todouble();
   }

   @Override
   public int checkint() {
      return this.toint();
   }

   @Override
   public long checklong() {
      return this.tolong();
   }

   @Override
   public LuaValue checknumber() {
      return this;
   }

   @Override
   public String checkjstring() {
      return this.tojstring();
   }

   @Override
   public LuaString checkstring() {
      return LuaString.valueOf(this.tojstring());
   }

   @Override
   public boolean raweq(LuaValue other) {
      return other instanceof LuaNumber && ((LuaNumber)other).todouble() == this.todouble();
   }
}
