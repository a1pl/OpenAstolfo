package astolfo.lua.vm;

import com.github.esrrhs.javalua.Lua;

/** Userdata and coroutine values, passed through untouched. */
final class LuaOpaque extends LuaValue {
   private final com.github.esrrhs.javalua.LuaValue v;

   LuaOpaque(com.github.esrrhs.javalua.LuaValue v) {
      this.v = v;
   }

   @Override
   com.github.esrrhs.javalua.LuaValue toJl() {
      return new com.github.esrrhs.javalua.LuaValue(this.v);
   }

   @Override
   public int type() {
      return Lua.noVariant(this.v.tt);
   }

   @Override
   public boolean isuserdata() {
      int type = this.type();
      return type == TUSERDATA || type == TLIGHTUSERDATA;
   }

   @Override
   public boolean isthread() {
      return this.type() == TTHREAD;
   }

   @Override
   public String tojstring() {
      return this.v.toString();
   }

   @Override
   public boolean raweq(LuaValue other) {
      return other instanceof LuaOpaque && ((LuaOpaque)other).v.o == this.v.o;
   }
}
