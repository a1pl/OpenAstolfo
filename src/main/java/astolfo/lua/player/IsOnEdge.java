package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.module.player.Scaffold;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ZeroArgFunction;

public final class IsOnEdge extends ZeroArgFunction {
   @Override
   public LuaValue call() {
      return ClientAccess.mc.thePlayer != null ? valueOf(Scaffold.isOnEdge()) : valueOf(false);
   }
}
