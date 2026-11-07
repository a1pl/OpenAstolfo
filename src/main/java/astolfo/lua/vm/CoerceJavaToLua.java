package astolfo.lua.vm;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Converts setting values to plain Lua values. Unlike LuaJ, arbitrary objects
 * become their {@code toString()} instead of reflective wrappers, so scripts
 * can't reach Java methods through them.
 */
public final class CoerceJavaToLua {
   private CoerceJavaToLua() {
   }

   public static LuaValue coerce(Object o) {
      if (o == null) {
         return LuaValue.NIL;
      } else if (o instanceof LuaValue) {
         return (LuaValue)o;
      } else if (o instanceof Boolean) {
         return LuaValue.valueOf((Boolean)o);
      } else if (o instanceof Integer || o instanceof Long || o instanceof Short || o instanceof Byte) {
         return LuaValue.valueOf(((Number)o).longValue());
      } else if (o instanceof Number) {
         return LuaValue.valueOf(((Number)o).doubleValue());
      } else if (o instanceof Character) {
         return LuaValue.valueOf(String.valueOf(o));
      } else if (o instanceof String) {
         return LuaValue.valueOf((String)o);
      } else if (o instanceof byte[]) {
         return LuaValue.valueOf((byte[])o);
      } else if (o.getClass().isArray()) {
         LuaTable table = new LuaTable();
         for (int i = 0, n = Array.getLength(o); i < n; i++) {
            table.rawset(i + 1, coerce(Array.get(o, i)));
         }

         return table;
      } else if (o instanceof Collection) {
         LuaTable table = new LuaTable();
         int i = 1;
         for (Object element : (Collection<?>)o) {
            table.rawset(i++, coerce(element));
         }

         return table;
      } else if (o instanceof Map) {
         LuaTable table = new LuaTable();
         for (Entry<?, ?> entry : ((Map<?, ?>)o).entrySet()) {
            table.rawset(coerce(entry.getKey()), coerce(entry.getValue()));
         }

         return table;
      } else {
         return LuaValue.valueOf(o.toString());
      }
   }
}
