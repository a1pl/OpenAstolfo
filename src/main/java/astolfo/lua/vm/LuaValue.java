package astolfo.lua.vm;

import java.util.Locale;

/**
 * Java-side view of a Lua value, shaped like LuaJ's {@code org.luaj.vm2.LuaValue}
 * so the client's script bindings work unchanged on top of javalua.
 * Scalars are plain Java objects; tables and functions point at javalua objects.
 */
public abstract class LuaValue extends Varargs {
   public static final int TNONE = -1;
   public static final int TNIL = 0;
   public static final int TBOOLEAN = 1;
   public static final int TLIGHTUSERDATA = 2;
   public static final int TNUMBER = 3;
   public static final int TSTRING = 4;
   public static final int TTABLE = 5;
   public static final int TFUNCTION = 6;
   public static final int TUSERDATA = 7;
   public static final int TTHREAD = 8;
   public static final String[] TYPE_NAMES = new String[]{
      "nil", "boolean", "lightuserdata", "number", "string", "table", "function", "userdata", "thread"
   };
   public static final LuaValue NIL = new LuaNil(true);
   public static final LuaValue NONE = new LuaNil(false);
   public static final LuaBoolean TRUE = new LuaBoolean(true);
   public static final LuaBoolean FALSE = new LuaBoolean(false);
   public static final LuaValue ZERO = LuaInteger.valueOf(0L);
   public static final LuaValue ONE = LuaInteger.valueOf(1L);
   public static final LuaValue MINUSONE = LuaInteger.valueOf(-1L);
   public static final LuaString EMPTYSTRING = LuaString.valueOf("");
   public static final LuaValue[] NOVALS = new LuaValue[0];

   abstract com.github.esrrhs.javalua.LuaValue toJl();

   public abstract int type();

   public String typename() {
      int type = this.type();
      return type >= 0 && type < TYPE_NAMES.length ? TYPE_NAMES[type] : "no value";
   }

   @Override
   public LuaValue arg(int i) {
      return i == 1 ? this : NIL;
   }

   @Override
   public int narg() {
      return 1;
   }

   @Override
   public LuaValue arg1() {
      return this;
   }

   public boolean isnil() {
      return false;
   }

   public boolean isboolean() {
      return false;
   }

   public boolean isnumber() {
      return false;
   }

   public boolean isint() {
      return false;
   }

   public boolean islong() {
      return false;
   }

   public boolean isinttype() {
      return false;
   }

   public boolean isstring() {
      return false;
   }

   public boolean istable() {
      return false;
   }

   public boolean isfunction() {
      return false;
   }

   public boolean isclosure() {
      return false;
   }

   public boolean isuserdata() {
      return false;
   }

   public boolean isthread() {
      return false;
   }

   public boolean toboolean() {
      return true;
   }

   public double todouble() {
      return 0.0;
   }

   public float tofloat() {
      return (float)this.todouble();
   }

   public int toint() {
      return (int)this.todouble();
   }

   public long tolong() {
      return (long)this.todouble();
   }

   public abstract String tojstring();

   public LuaValue tostring() {
      return LuaString.valueOf(this.tojstring());
   }

   public LuaValue tonumber() {
      return NIL;
   }

   public LuaValue checknotnil() {
      return this;
   }

   public boolean checkboolean() {
      throw this.typeError("boolean");
   }

   public double checkdouble() {
      throw this.typeError("number");
   }

   public int checkint() {
      throw this.typeError("number");
   }

   public long checklong() {
      throw this.typeError("number");
   }

   public LuaValue checknumber() {
      throw this.typeError("number");
   }

   public String checkjstring() {
      throw this.typeError("string");
   }

   public LuaString checkstring() {
      throw this.typeError("string");
   }

   public LuaTable checktable() {
      throw this.typeError("table");
   }

   public LuaFunction checkfunction() {
      throw this.typeError("function");
   }

   public Globals checkglobals() {
      throw this.typeError("globals");
   }

   public boolean optboolean(boolean def) {
      return this.isnil() ? def : this.checkboolean();
   }

   public double optdouble(double def) {
      return this.isnil() ? def : this.checkdouble();
   }

   public int optint(int def) {
      return this.isnil() ? def : this.checkint();
   }

   public long optlong(long def) {
      return this.isnil() ? def : this.checklong();
   }

   public String optjstring(String def) {
      return this.isnil() ? def : this.checkjstring();
   }

   public LuaTable opttable(LuaTable def) {
      return this.isnil() ? def : this.checktable();
   }

   public LuaValue optvalue(LuaValue def) {
      return this.isnil() ? def : this;
   }

   public LuaValue get(LuaValue key) {
      throw this.indexError();
   }

   public LuaValue get(int key) {
      return this.get(LuaInteger.valueOf((long)key));
   }

   public LuaValue get(String key) {
      return this.get(LuaString.valueOf(key));
   }

   public LuaValue rawget(LuaValue key) {
      throw this.indexError();
   }

   public LuaValue rawget(int key) {
      return this.rawget(LuaInteger.valueOf((long)key));
   }

   public LuaValue rawget(String key) {
      return this.rawget(LuaString.valueOf(key));
   }

   public void set(LuaValue key, LuaValue value) {
      throw this.indexError();
   }

   public void set(int key, LuaValue value) {
      this.set(LuaInteger.valueOf((long)key), value);
   }

   public void set(int key, String value) {
      this.set(LuaInteger.valueOf((long)key), valueOf(value));
   }

   public void set(String key, LuaValue value) {
      this.set(LuaString.valueOf(key), value);
   }

   public void set(String key, double value) {
      this.set(LuaString.valueOf(key), valueOf(value));
   }

   public void set(String key, int value) {
      this.set(LuaString.valueOf(key), valueOf(value));
   }

   public void set(String key, String value) {
      this.set(LuaString.valueOf(key), valueOf(value));
   }

   public void set(LuaValue key, int value) {
      this.set(key, valueOf(value));
   }

   public void set(LuaValue key, double value) {
      this.set(key, valueOf(value));
   }

   public void set(LuaValue key, String value) {
      this.set(key, valueOf(value));
   }

   public void rawset(LuaValue key, LuaValue value) {
      throw this.indexError();
   }

   public void rawset(int key, LuaValue value) {
      this.rawset(LuaInteger.valueOf((long)key), value);
   }

   public void rawset(String key, LuaValue value) {
      this.rawset(LuaString.valueOf(key), value);
   }

   public int length() {
      throw new LuaError("attempt to get length of a " + this.typename() + " value");
   }

   public LuaValue len() {
      return valueOf(this.length());
   }

   public int rawlen() {
      return this.length();
   }

   public Varargs next(LuaValue key) {
      throw this.typeError("table");
   }

   public Varargs inext(LuaValue key) {
      throw this.typeError("table");
   }

   public LuaValue getmetatable() {
      return NIL;
   }

   public LuaValue setmetatable(LuaValue metatable) {
      throw new LuaError("cannot set metatable of a " + this.typename() + " value");
   }

   public LuaValue call() {
      return this.invoke(NONE).arg1();
   }

   public LuaValue call(LuaValue a) {
      return this.invoke(a).arg1();
   }

   public LuaValue call(LuaValue a, LuaValue b) {
      return this.invoke(varargsOf(a, b)).arg1();
   }

   public LuaValue call(LuaValue a, LuaValue b, LuaValue c) {
      return this.invoke(varargsOf(a, b, c)).arg1();
   }

   public LuaValue call(String a) {
      return this.call(valueOf(a));
   }

   public Varargs invoke() {
      return this.invoke(NONE);
   }

   public Varargs invoke(Varargs args) {
      throw new LuaError("attempt to call a " + this.typename() + " value");
   }

   public Varargs invoke(LuaValue[] args) {
      return this.invoke(varargsOf(args));
   }

   public LuaValue method(String name) {
      return this.get(name).call(this);
   }

   public LuaValue method(String name, LuaValue a) {
      return this.get(name).call(this, a);
   }

   public boolean raweq(LuaValue other) {
      return this == other;
   }

   public boolean eq_b(LuaValue other) {
      return this.raweq(other);
   }

   public LuaValue eq(LuaValue other) {
      return valueOf(this.eq_b(other));
   }

   public LuaValue error(String message) {
      throw new LuaError(message);
   }

   @Override
   public String toString() {
      return this.tojstring();
   }

   LuaError typeError(String expected) {
      return new LuaError(expected + " expected, got " + this.typename());
   }

   private LuaError indexError() {
      return new LuaError("attempt to index ? (a " + this.typename() + " value)");
   }

   public static LuaValue argerror(int i, String message) {
      throw new LuaError("bad argument #" + i + " (" + message + ")");
   }

   public static LuaBoolean valueOf(boolean b) {
      return b ? TRUE : FALSE;
   }

   public static LuaValue valueOf(int i) {
      return LuaInteger.valueOf((long)i);
   }

   public static LuaValue valueOf(long l) {
      return LuaInteger.valueOf(l);
   }

   /** Like LuaJ, an integral double that fits an int becomes an integer. */
   public static LuaValue valueOf(double d) {
      int i = (int)d;
      return d == (double)i ? LuaInteger.valueOf((long)i) : LuaDouble.valueOf(d);
   }

   public static LuaString valueOf(String s) {
      return LuaString.valueOf(s);
   }

   public static LuaString valueOf(byte[] bytes) {
      return LuaString.valueOf(bytes);
   }

   public static LuaTable tableOf() {
      return new LuaTable();
   }

   public static LuaTable tableOf(int narray, int nhash) {
      return new LuaTable(narray, nhash);
   }

   /** {@code namedValues} alternates key, value. */
   public static LuaTable tableOf(LuaValue[] namedValues) {
      return tableOf(namedValues, null);
   }

   public static LuaTable tableOf(LuaValue[] namedValues, LuaValue[] unnamedValues) {
      LuaTable table = new LuaTable();
      if (unnamedValues != null) {
         for (int i = 0; i < unnamedValues.length; i++) {
            table.rawset(i + 1, unnamedValues[i]);
         }
      }

      if (namedValues != null) {
         for (int i = 0; i + 1 < namedValues.length; i += 2) {
            table.rawset(namedValues[i], namedValues[i + 1]);
         }
      }

      return table;
   }

   public static LuaTable listOf(LuaValue[] values) {
      return tableOf(null, values);
   }

   public static Varargs varargsOf(LuaValue[] values) {
      switch (values.length) {
         case 0:
            return NONE;
         case 1:
            return values[0];
         default:
            return new Varargs.ArrayVarargs(values, NONE);
      }
   }

   public static Varargs varargsOf(LuaValue[] values, Varargs more) {
      return new Varargs.ArrayVarargs(values, more);
   }

   public static Varargs varargsOf(LuaValue first, Varargs rest) {
      return new Varargs.PairVarargs(first, rest);
   }

   public static Varargs varargsOf(LuaValue a, LuaValue b, Varargs rest) {
      return new Varargs.PairVarargs(a, new Varargs.PairVarargs(b, rest));
   }

   /** Lua 5.3's {@code %.14g} number formatting, with integral values printed bare. */
   static String formatNumber(double d) {
      if (Double.isNaN(d)) {
         return d < 0 || Double.doubleToRawLongBits(d) < 0 ? "-nan" : "nan";
      }

      if (Double.isInfinite(d)) {
         return d < 0 ? "-inf" : "inf";
      }

      long l = (long)d;
      if ((double)l == d && Math.abs(d) < 1.0E15) {
         return Long.toString(l);
      }

      String s = String.format(Locale.ROOT, "%.14g", d);
      int exp = s.indexOf('e');
      String mantissa = exp >= 0 ? s.substring(0, exp) : s;
      String exponent = exp >= 0 ? s.substring(exp) : "";
      if (mantissa.indexOf('.') >= 0) {
         mantissa = mantissa.replaceAll("0+$", "");
         if (mantissa.endsWith(".")) {
            mantissa = mantissa.substring(0, mantissa.length() - 1);
         }
      }

      return mantissa + exponent;
   }
}
