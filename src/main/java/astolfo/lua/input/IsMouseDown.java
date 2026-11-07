package astolfo.lua.input;

import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;
import org.lwjgl.input.Mouse;

public final class IsMouseDown extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      return !luaValue.isnumber() ? valueOf(false) : valueOf(Mouse.isButtonDown(luaValue.checkint()));
   }
}
