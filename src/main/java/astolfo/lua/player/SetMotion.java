package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ThreeArgFunction;

public final class SetMotion extends ThreeArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
      if (ClientAccess.mc.thePlayer != null) {
         if (luaValue.isnumber() && luaValue2.isnumber() && luaValue3.isnumber()) {
            ClientAccess.mc.thePlayer.motionX = luaValue.todouble();
            ClientAccess.mc.thePlayer.motionY = luaValue2.todouble();
            ClientAccess.mc.thePlayer.motionZ = luaValue3.todouble();
            return valueOf(true);
         } else {
            return valueOf(false);
         }
      } else {
         return valueOf(false);
      }
   }
}
