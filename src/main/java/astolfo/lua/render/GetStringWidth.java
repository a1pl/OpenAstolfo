package astolfo.lua.render;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class GetStringWidth extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      return luaValue.isstring() ? valueOf(ClientAccess.mc.fontRendererObj.getStringWidth(luaValue.checkjstring())) : valueOf(0);
   }
}
