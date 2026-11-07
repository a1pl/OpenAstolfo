package astolfo.lua.vm;

public final class LuaBoolean extends LuaValue {
   public final boolean v;

   LuaBoolean(boolean v) {
      this.v = v;
   }

   @Override
   com.github.esrrhs.javalua.LuaValue toJl() {
      return com.github.esrrhs.javalua.LuaValue.ofBoolean(this.v);
   }

   @Override
   public int type() {
      return TBOOLEAN;
   }

   @Override
   public boolean isboolean() {
      return true;
   }

   @Override
   public boolean toboolean() {
      return this.v;
   }

   @Override
   public boolean checkboolean() {
      return this.v;
   }

   public boolean booleanValue() {
      return this.v;
   }

   @Override
   public String tojstring() {
      return this.v ? "true" : "false";
   }

   @Override
   public boolean raweq(LuaValue other) {
      return other instanceof LuaBoolean && ((LuaBoolean)other).v == this.v;
   }
}
