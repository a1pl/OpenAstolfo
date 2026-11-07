package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ThreeArgFunction;

public final class DistanceTo extends ThreeArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
      return (LuaValue)(ClientAccess.mc.thePlayer != null && luaValue.isnumber() && luaValue2.isnumber() && luaValue3.isnumber()
         ? valueOf(ClientAccess.mc.thePlayer.getDistance(luaValue.checkdouble(), luaValue2.checkdouble(), luaValue3.checkdouble()))
         : valueOf(false));
   }
}
