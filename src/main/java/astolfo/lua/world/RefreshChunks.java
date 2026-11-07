package astolfo.lua.world;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ZeroArgFunction;

public final class RefreshChunks extends ZeroArgFunction {
   @Override
   public LuaValue call() {
      ClientAccess.mc.renderGlobal.loadRenderers();
      return valueOf(true);
   }
}
