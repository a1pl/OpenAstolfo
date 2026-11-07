package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class SetHeldItemSlot extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      if (!luaValue.isnumber()) {
         return valueOf(false);
      } else if (ClientAccess.mc.thePlayer != null && luaValue.isint()) {
         ClientAccess.mc.thePlayer.inventory.currentItem = luaValue.checkint() + 1;
         ClientAccess.mc.playerController.updateController();
         return valueOf(true);
      } else {
         return valueOf(false);
      }
   }
}
