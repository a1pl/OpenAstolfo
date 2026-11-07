package astolfo.lua.client;

import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ZeroArgFunction;

public final class Time extends ZeroArgFunction {
   @Override
   public LuaValue call() {
      return valueOf((double)System.currentTimeMillis());
   }
}
