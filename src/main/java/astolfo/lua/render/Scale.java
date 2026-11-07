package astolfo.lua.render;

import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;
import org.lwjgl.opengl.GL11;

public final class Scale extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      if (luaValue.isnumber()) {
         GL11.glScalef(luaValue.tofloat(), luaValue.tofloat(), luaValue.tofloat());
         return valueOf(true);
      } else {
         return valueOf(0);
      }
   }
}
