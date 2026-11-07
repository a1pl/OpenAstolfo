package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class SetSneaking extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      if (!luaValue.isboolean()) {
         return valueOf(false);
      } else if (ClientAccess.mc.thePlayer != null && luaValue.isboolean()) {
         ClientAccess.mc.gameSettings.keyBindSneak.pressed = luaValue.checkboolean();
         return valueOf(true);
      } else {
         return valueOf(false);
      }
   }
}
