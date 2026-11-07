package astolfo.lua.input;

import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;
import org.lwjgl.input.Keyboard;

public final class GetKeyNumber extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      return (LuaValue)(!luaValue.isstring() ? valueOf(false) : valueOf(Keyboard.getKeyIndex(luaValue.checkjstring())));
   }
}
