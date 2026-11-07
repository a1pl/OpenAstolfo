package astolfo.lua.input;

import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;
import org.lwjgl.input.Keyboard;

public final class IsKeyDown extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      return !luaValue.isnumber() ? valueOf(false) : valueOf(Keyboard.isKeyDown(luaValue.checkint()));
   }
}
