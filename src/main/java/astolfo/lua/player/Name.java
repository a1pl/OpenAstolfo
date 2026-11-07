package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ZeroArgFunction;

public final class Name extends ZeroArgFunction {
   @Override
   public LuaValue call() {
      return (LuaValue)(ClientAccess.mc.thePlayer != null ? valueOf(ClientAccess.mc.thePlayer.getName()) : valueOf(-1));
   }
}
