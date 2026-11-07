package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ThreeArgFunction;

public final class Click extends ThreeArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
      if (ClientAccess.mc.thePlayer != null && luaValue.isnumber() && luaValue2.isnumber() && luaValue3.isnumber()) {
         int i = 0;
         if (ClientAccess.mc.thePlayer.openContainer != null) {
            i = ClientAccess.mc.thePlayer.openContainer.windowId;
         }

         ClientAccess.mc.playerController.windowClick(i, luaValue.checkint(), luaValue2.checkint(), luaValue3.checkint(), ClientAccess.mc.thePlayer);
         return valueOf(true);
      } else {
         return NIL;
      }
   }
}
