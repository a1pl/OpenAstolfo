package astolfo.lua.vm;

public final class LuaDouble extends LuaNumber {
   public final double v;

   private LuaDouble(double v) {
      this.v = v;
   }

   public static LuaDouble valueOf(double v) {
      return new LuaDouble(v);
   }

   @Override
   com.github.esrrhs.javalua.LuaValue toJl() {
      return com.github.esrrhs.javalua.LuaValue.ofFloat(this.v);
   }

   @Override
   public boolean isint() {
      return this.v == (double)(int)this.v;
   }

   @Override
   public boolean islong() {
      return this.v == (double)(long)this.v;
   }

   @Override
   public double todouble() {
      return this.v;
   }

   @Override
   public String tojstring() {
      return formatNumber(this.v);
   }

   @Override
   public boolean equals(Object o) {
      return o instanceof LuaValue && this.raweq((LuaValue)o);
   }

   @Override
   public int hashCode() {
      return Double.hashCode(this.v);
   }
}
