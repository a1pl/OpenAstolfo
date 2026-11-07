package astolfo.command;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;

public abstract class Command extends AbstractCommand implements ClientAccess {
   public LuaValue luaTable;

   public Command(String string, String string2) {
      super(string, string2);
   }
}
