package astolfo.lua;

import astolfo.ClientAccess;
import astolfo.lua.vm.Globals;
import astolfo.lua.vm.LuaTable;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.TwoArgFunction;

public class ClassLibrary extends TwoArgFunction implements ClientAccess {
   public Globals globals;
   private static final String c = "/assets/minecraft/client/class.lua";

   @Override
   public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
      this.globals = luaValue2.checkglobals();
      LuaTable luatable = new LuaTable();

      try {
         this.globals.loadfile(c);
      } catch (Exception exception) {
         exception.printStackTrace();
      }

      return luatable;
   }
}
