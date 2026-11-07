package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class SetSprinting extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      if (ClientAccess.mc.thePlayer != null && luaValue.isboolean()) {
         ClientAccess.mc.thePlayer.setSprinting(luaValue.checkboolean());
      }

      return valueOf(false);
   }
}
