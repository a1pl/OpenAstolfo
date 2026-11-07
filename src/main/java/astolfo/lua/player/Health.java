package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ZeroArgFunction;

public final class Health extends ZeroArgFunction {
   @Override
   public LuaValue call() {
      return (LuaValue)(ClientAccess.mc.thePlayer != null ? valueOf((double)ClientAccess.mc.thePlayer.getHealth()) : valueOf(-1));
   }
}
