package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class IsPotionActive extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      return ClientAccess.mc.thePlayer != null && luaValue.isnumber() ? valueOf(ClientAccess.mc.thePlayer.isPotionActive(luaValue.checkint())) : valueOf(false);
   }
}
