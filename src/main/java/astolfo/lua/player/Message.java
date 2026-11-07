package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaString;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class Message extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      if (!luaValue.isstring()) {
         return valueOf(false);
      } else if (ClientAccess.mc.thePlayer != null) {
         LuaString luaString = (LuaString)luaValue;
         System.out.println(luaString.checkjstring());
         ClientAccess.mc.thePlayer.sendClientChatMessage(luaString.checkjstring());
         return valueOf(true);
      } else {
         return valueOf(false);
      }
   }
}
