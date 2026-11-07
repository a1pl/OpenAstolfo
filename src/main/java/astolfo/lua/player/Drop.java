package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.util.InventoryUtil;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class Drop extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      if (ClientAccess.mc.thePlayer != null && luaValue.isnumber()) {
         InventoryUtil.dropSlot(luaValue.checkint(), 0);
      }

      return NIL;
   }
}
