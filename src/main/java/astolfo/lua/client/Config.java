package astolfo.lua.client;

import astolfo.Astolfo;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ZeroArgFunction;

public final class Config extends ZeroArgFunction {
   @Override
   public LuaValue call() {
      return valueOf(Astolfo.INSTANCE.getConfigName());
   }
}
