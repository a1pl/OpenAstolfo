package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.TwoArgFunction;

public final class SetAngles extends TwoArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue, LuaValue luaValue2) {
      if (ClientAccess.mc.thePlayer != null && luaValue.isnumber() && luaValue2.isnumber()) {
         ClientAccess.mc.thePlayer.rotationYaw = luaValue.tofloat();
         ClientAccess.mc.thePlayer.rotationPitch = luaValue2.tofloat();
      }

      return valueOf(false);
   }
}
