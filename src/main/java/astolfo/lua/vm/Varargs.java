package astolfo.lua.vm;

/**
 * An ordered list of Lua values (arguments or results), 1-indexed like LuaJ's
 * {@code org.luaj.vm2.Varargs}. A single {@link LuaValue} is a list of one.
 */
public abstract class Varargs {
   public abstract LuaValue arg(int i);

   public abstract int narg();

   public LuaValue arg1() {
      return this.arg(1);
   }

   public Varargs eval() {
      return this;
   }

   public boolean isTailcall() {
      return false;
   }

   public Varargs subargs(int start) {
      int n = this.narg() - start + 1;
      if (n <= 0) {
         return LuaValue.NONE;
      }

      LuaValue[] values = new LuaValue[n];
      for (int i = 0; i < n; i++) {
         values[i] = this.arg(start + i);
      }

      return LuaValue.varargsOf(values);
   }

   public int type(int i) {
      return this.arg(i).type();
   }

   public boolean isvalue(int i) {
      return i > 0 && i <= this.narg();
   }

   public boolean isnoneornil(int i) {
      return !this.isvalue(i) || this.arg(i).isnil();
   }

   public boolean isnil(int i) {
      return this.arg(i).isnil();
   }

   public boolean isfunction(int i) {
      return this.arg(i).isfunction();
   }

   public boolean isnumber(int i) {
      return this.arg(i).isnumber();
   }

   public boolean isstring(int i) {
      return this.arg(i).isstring();
   }

   public boolean istable(int i) {
      return this.arg(i).istable();
   }

   public boolean isboolean(int i) {
      return this.arg(i).isboolean();
   }

   public LuaValue checkvalue(int i) {
      if (!this.isvalue(i)) {
         throw new LuaError("bad argument #" + i + " (value expected)");
      }

      return this.arg(i);
   }

   public LuaValue checknotnil(int i) {
      return this.arg(i).checknotnil();
   }

   public boolean checkboolean(int i) {
      return this.arg(i).checkboolean();
   }

   public double checkdouble(int i) {
      return this.arg(i).checkdouble();
   }

   public int checkint(int i) {
      return this.arg(i).checkint();
   }

   public long checklong(int i) {
      return this.arg(i).checklong();
   }

   public LuaValue checknumber(int i) {
      return this.arg(i).checknumber();
   }

   public String checkjstring(int i) {
      return this.arg(i).checkjstring();
   }

   public LuaString checkstring(int i) {
      return this.arg(i).checkstring();
   }

   public LuaTable checktable(int i) {
      return this.arg(i).checktable();
   }

   public LuaFunction checkfunction(int i) {
      return this.arg(i).checkfunction();
   }

   public boolean optboolean(int i, boolean def) {
      return this.arg(i).optboolean(def);
   }

   public double optdouble(int i, double def) {
      return this.arg(i).optdouble(def);
   }

   public int optint(int i, int def) {
      return this.arg(i).optint(def);
   }

   public long optlong(int i, long def) {
      return this.arg(i).optlong(def);
   }

   public String optjstring(int i, String def) {
      return this.arg(i).optjstring(def);
   }

   public LuaTable opttable(int i, LuaTable def) {
      return this.arg(i).opttable(def);
   }

   public LuaValue optvalue(int i, LuaValue def) {
      return this.isnoneornil(i) ? def : this.arg(i);
   }

   public boolean toboolean(int i) {
      return this.arg(i).toboolean();
   }

   public double todouble(int i) {
      return this.arg(i).todouble();
   }

   public float tofloat(int i) {
      return this.arg(i).tofloat();
   }

   public int toint(int i) {
      return this.arg(i).toint();
   }

   public long tolong(int i) {
      return this.arg(i).tolong();
   }

   public String tojstring(int i) {
      return this.arg(i).tojstring();
   }

   public String tojstring() {
      StringBuilder sb = new StringBuilder("(");
      for (int i = 1, n = this.narg(); i <= n; i++) {
         if (i > 1) {
            sb.append(',');
         }

         sb.append(this.arg(i).tojstring());
      }

      return sb.append(')').toString();
   }

   @Override
   public String toString() {
      return this.tojstring();
   }

   static final class ArrayVarargs extends Varargs {
      private final LuaValue[] values;
      private final Varargs more;

      ArrayVarargs(LuaValue[] values, Varargs more) {
         this.values = values;
         this.more = more;
      }

      @Override
      public LuaValue arg(int i) {
         if (i < 1) {
            return LuaValue.NIL;
         }

         return i <= this.values.length ? this.values[i - 1] : this.more.arg(i - this.values.length);
      }

      @Override
      public int narg() {
         return this.values.length + this.more.narg();
      }
   }

   static final class PairVarargs extends Varargs {
      private final LuaValue first;
      private final Varargs rest;

      PairVarargs(LuaValue first, Varargs rest) {
         this.first = first;
         this.rest = rest;
      }

      @Override
      public LuaValue arg(int i) {
         return i == 1 ? this.first : (i > 1 ? this.rest.arg(i - 1) : LuaValue.NIL);
      }

      @Override
      public int narg() {
         return 1 + this.rest.narg();
      }
   }
}
