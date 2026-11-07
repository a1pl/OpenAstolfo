package astolfo.lua.render;

import astolfo.gui.screen.LuaScreen;
import net.minecraft.client.Minecraft;
import astolfo.lua.vm.LuaTable;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class ShowGui extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      LuaTable luatable = null;
      if (luaValue.isfunction()) {
         LuaValue luavalue = luaValue.call();
         if (luavalue.istable()) {
            luatable = (LuaTable)luavalue.call();
         }
      } else if (luaValue.istable()) {
         luatable = (LuaTable)luaValue;
      }

      if (luatable != null) {
         Minecraft.getMinecraft().displayGuiScreen(new LuaScreen(luatable));
         return valueOf(true);
      } else {
         return valueOf(false);
      }
   }
}
