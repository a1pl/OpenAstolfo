package astolfo.lua.client;

import astolfo.ClientChat;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class Print extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      ClientChat.CHAT.print(luaValue.tojstring());
      return valueOf(true);
   }
}
