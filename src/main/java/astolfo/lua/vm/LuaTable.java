package astolfo.lua.vm;

import com.github.esrrhs.javalua.LuaState;
import java.util.ArrayList;
import java.util.List;

public class LuaTable extends LuaValue {
   final com.github.esrrhs.javalua.LuaTable t;
   /** State functions read out of this table run on; null for tables made in Java. */
   final LuaState owner;

   public LuaTable() {
      this(new com.github.esrrhs.javalua.LuaTable(), null);
   }

   public LuaTable(int narray, int nhash) {
      this(new com.github.esrrhs.javalua.LuaTable(narray, nhash), null);
   }

   /** A list of {@code values}, as LuaJ's {@code new LuaTable(varargs)}. */
   public LuaTable(Varargs values) {
      this(values, 1);
   }

   public LuaTable(Varargs values, int firstarg) {
      this();
      for (int i = firstarg, n = values.narg(); i <= n; i++) {
         this.rawset(i - firstarg + 1, values.arg(i));
      }
   }

   LuaTable(com.github.esrrhs.javalua.LuaTable t, LuaState owner) {
      this.t = t;
      this.owner = owner;
   }

   @Override
   com.github.esrrhs.javalua.LuaValue toJl() {
      return com.github.esrrhs.javalua.LuaValue.ofTable(this.t);
   }

   @Override
   public int type() {
      return TTABLE;
   }

   @Override
   public boolean istable() {
      return true;
   }

   @Override
   public LuaTable checktable() {
      return this;
   }

   @Override
   public String tojstring() {
      return "table: 0x" + Integer.toHexString(System.identityHashCode(this.t));
   }

   /** Raw lookup, falling back to an {@code __index} table or function. */
   @Override
   public LuaValue get(LuaValue key) {
      LuaValue value = this.rawget(key);
      if (value.isnil()) {
         com.github.esrrhs.javalua.LuaTable metatable = this.t.getMetatable();
         if (metatable != null) {
            LuaValue index = Interop.fromJl(metatable.get("__index"), this.owner);
            if (index.istable()) {
               return index.get(key);
            }

            if (index.isfunction()) {
               return index.call(this, key);
            }
         }
      }

      return value;
   }

   @Override
   public LuaValue rawget(LuaValue key) {
      if (key.isnil()) {
         return NIL;
      }

      com.github.esrrhs.javalua.LuaValue value = this.t.get(key.toJl());
      return value == null ? NIL : Interop.fromJl(value, this.owner);
   }

   @Override
   public void set(LuaValue key, LuaValue value) {
      this.rawset(key, value);
   }

   @Override
   public void rawset(LuaValue key, LuaValue value) {
      if (key.isnil()) {
         throw new LuaError("table index is nil");
      }

      this.t.set(key.toJl(), value.toJl());
   }

   @Override
   public int length() {
      return (int)this.t.length();
   }

   @Override
   public Varargs next(LuaValue key) {
      com.github.esrrhs.javalua.LuaValue[] entry = this.t.next(key.toJl());
      return entry == null ? NIL : varargsOf(Interop.fromJl(entry[0], this.owner), Interop.fromJl(entry[1], this.owner));
   }

   @Override
   public Varargs inext(LuaValue key) {
      int i = key.isnil() ? 1 : key.toint() + 1;
      LuaValue value = this.rawget(i);
      return value.isnil() ? NIL : varargsOf(valueOf(i), value);
   }

   public LuaValue[] keys() {
      List<LuaValue> keys = new ArrayList<>();
      for (Varargs entry = this.next(NIL); !entry.arg1().isnil(); entry = this.next(entry.arg1())) {
         keys.add(entry.arg1());
      }

      return keys.toArray(new LuaValue[0]);
   }

   public void insert(int pos, LuaValue value) {
      int n = this.length();
      if (pos <= 0 || pos > n) {
         this.rawset(n + 1, value);
         return;
      }

      for (int i = n; i >= pos; i--) {
         this.rawset(i + 1, this.rawget(i));
      }

      this.rawset(pos, value);
   }

   public LuaValue remove(int pos) {
      int n = this.length();
      if (pos <= 0) {
         pos = n;
      }

      LuaValue removed = this.rawget(pos);
      for (int i = pos; i < n; i++) {
         this.rawset(i, this.rawget(i + 1));
      }

      if (n > 0 && pos <= n) {
         this.rawset(n, NIL);
      }

      return removed;
   }

   public void presize(int narray) {
   }

   public void presize(int narray, int nhash) {
   }

   @Override
   public LuaValue getmetatable() {
      com.github.esrrhs.javalua.LuaTable metatable = this.t.getMetatable();
      return metatable == null ? NIL : new LuaTable(metatable, this.owner);
   }

   @Override
   public LuaValue setmetatable(LuaValue metatable) {
      this.t.setMetatable(metatable.isnil() ? null : metatable.checktable().t);
      return this;
   }

   @Override
   public boolean raweq(LuaValue other) {
      return other instanceof LuaTable && ((LuaTable)other).t == this.t;
   }

   @Override
   public boolean equals(Object o) {
      return o instanceof LuaTable && ((LuaTable)o).t == this.t;
   }

   @Override
   public int hashCode() {
      return System.identityHashCode(this.t);
   }
}
