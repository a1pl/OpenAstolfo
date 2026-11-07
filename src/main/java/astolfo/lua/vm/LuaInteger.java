package astolfo.lua.vm;

public final class LuaInteger extends LuaNumber {
   private static final LuaInteger[] SMALL = new LuaInteger[512];
   public final long v;

   static {
      for (int i = 0; i < SMALL.length; i++) {
         SMALL[i] = new LuaInteger((long)(i - 256));
      }
   }

   private LuaInteger(long v) {
      this.v = v;
   }

   public static LuaInteger valueOf(long v) {
      return v >= -256L && v < 256L ? SMALL[(int)v + 256] : new LuaInteger(v);
   }

   @Override
   com.github.esrrhs.javalua.LuaValue toJl() {
      return com.github.esrrhs.javalua.LuaValue.ofInteger(this.v);
   }

   @Override
   public boolean isint() {
      return this.v == (long)(int)this.v;
   }

   @Override
   public boolean islong() {
      return true;
   }

   @Override
   public boolean isinttype() {
      return true;
   }

   @Override
   public double todouble() {
      return (double)this.v;
   }

   @Override
   public int toint() {
      return (int)this.v;
   }

   @Override
   public long tolong() {
      return this.v;
   }

   @Override
   public String tojstring() {
      return Long.toString(this.v);
   }

   @Override
   public boolean raweq(LuaValue other) {
      return other instanceof LuaInteger ? ((LuaInteger)other).v == this.v : super.raweq(other);
   }

   @Override
   public boolean equals(Object o) {
      return o instanceof LuaValue && this.raweq((LuaValue)o);
   }

   @Override
   public int hashCode() {
      return Long.hashCode(this.v);
   }
}
