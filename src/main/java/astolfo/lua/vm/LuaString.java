package astolfo.lua.vm;

public final class LuaString extends LuaValue {
   final com.github.esrrhs.javalua.LuaString s;
   private String jstring;

   private LuaString(com.github.esrrhs.javalua.LuaString s, String jstring) {
      this.s = s;
      this.jstring = jstring;
   }

   static LuaString wrap(com.github.esrrhs.javalua.LuaString s) {
      return new LuaString(s, null);
   }

   public static LuaString valueOf(String s) {
      return new LuaString(com.github.esrrhs.javalua.LuaString.utf8(s), s);
   }

   public static LuaString valueOf(byte[] bytes) {
      return new LuaString(com.github.esrrhs.javalua.LuaString.of(bytes), null);
   }

   @Override
   com.github.esrrhs.javalua.LuaValue toJl() {
      return com.github.esrrhs.javalua.LuaValue.ofString(this.s);
   }

   @Override
   public int type() {
      return TSTRING;
   }

   @Override
   public boolean isstring() {
      return true;
   }

   /** Numeric strings count as numbers, as in LuaJ. */
   @Override
   public boolean isnumber() {
      return !Double.isNaN(this.scanNumber());
   }

   @Override
   public boolean isint() {
      double d = this.scanNumber();
      return d == (double)(int)d;
   }

   @Override
   public LuaValue tonumber() {
      double d = this.scanNumber();
      return Double.isNaN(d) ? NIL : valueOf(d);
   }

   @Override
   public double todouble() {
      double d = this.scanNumber();
      return Double.isNaN(d) ? 0.0 : d;
   }

   @Override
   public double checkdouble() {
      double d = this.scanNumber();
      if (Double.isNaN(d)) {
         throw this.typeError("number");
      }

      return d;
   }

   @Override
   public int checkint() {
      return (int)this.checkdouble();
   }

   @Override
   public long checklong() {
      return (long)this.checkdouble();
   }

   @Override
   public LuaValue checknumber() {
      return valueOf(this.checkdouble());
   }

   @Override
   public String tojstring() {
      if (this.jstring == null) {
         this.jstring = this.s.toJavaString();
      }

      return this.jstring;
   }

   @Override
   public String checkjstring() {
      return this.tojstring();
   }

   @Override
   public LuaString checkstring() {
      return this;
   }

   @Override
   public int length() {
      return this.s.length();
   }

   @Override
   public boolean raweq(LuaValue other) {
      return other instanceof LuaString && ((LuaString)other).s.equals(this.s);
   }

   @Override
   public boolean equals(Object o) {
      return o instanceof LuaString && this.raweq((LuaString)o);
   }

   @Override
   public int hashCode() {
      return this.s.hashCode();
   }

   private double scanNumber() {
      String str = this.tojstring().trim();
      if (str.isEmpty()) {
         return Double.NaN;
      }

      try {
         String body = str.startsWith("-") || str.startsWith("+") ? str.substring(1) : str;
         if (body.startsWith("0x") || body.startsWith("0X")) {
            double d = (double)Long.parseLong(body.substring(2), 16);
            return str.startsWith("-") ? -d : d;
         }

         char last = str.charAt(str.length() - 1);
         return last != 'd' && last != 'D' && last != 'f' && last != 'F' ? Double.parseDouble(str) : Double.NaN;
      } catch (NumberFormatException numberFormatException) {
         return Double.NaN;
      }
   }
}
