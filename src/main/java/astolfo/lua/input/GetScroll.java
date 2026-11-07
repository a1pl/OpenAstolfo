package astolfo.lua.input;

import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ZeroArgFunction;
import org.lwjgl.input.Mouse;

public final class GetScroll extends ZeroArgFunction {
   @Override
   public LuaValue call() {
      return valueOf(Mouse.getDWheel());
   }
}
