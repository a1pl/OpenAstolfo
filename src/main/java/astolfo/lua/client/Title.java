package astolfo.lua.client;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ZeroArgFunction;

public final class Title extends ZeroArgFunction {
   @Override
   public LuaValue call() {
      return ClientAccess.mc.ingameGUI != null ? valueOf(ClientAccess.mc.ingameGUI.displayedTitle) : valueOf("");
   }
}
