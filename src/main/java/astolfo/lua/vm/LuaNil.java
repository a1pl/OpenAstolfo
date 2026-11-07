package astolfo.lua.vm;

/** {@code nil}; the "none" instance is the empty argument list. */
final class LuaNil extends LuaValue {
   private final boolean isValue;

   LuaNil(boolean isValue) {
      this.isValue = isValue;
   }

   @Override
   com.github.esrrhs.javalua.LuaValue toJl() {
      return com.github.esrrhs.javalua.LuaValue.ofNil();
   }

   @Override
   public int type() {
      return this.isValue ? TNIL : TNONE;
   }

   @Override
   public String typename() {
      return "nil";
   }

   @Override
   public int narg() {
      return this.isValue ? 1 : 0;
   }

   @Override
   public LuaValue arg(int i) {
      return NIL;
   }

   @Override
   public LuaValue arg1() {
      return NIL;
   }

   @Override
   public boolean isnil() {
      return true;
   }

   @Override
   public boolean toboolean() {
      return false;
   }

   @Override
   public String tojstring() {
      return "nil";
   }

   @Override
   public LuaValue checknotnil() {
      throw new LuaError("value expected");
   }

   @Override
   public boolean raweq(LuaValue other) {
      return other.isnil();
   }
}
