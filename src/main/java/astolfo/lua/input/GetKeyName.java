package astolfo.lua.input;

import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;
import org.lwjgl.input.Keyboard;

public final class GetKeyName extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      return (LuaValue)(!luaValue.isnumber() ? valueOf(false) : valueOf(Keyboard.getKeyName(luaValue.checkint())));
   }
}
