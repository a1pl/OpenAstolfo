package astolfo.lua.client;

import net.minecraft.client.Minecraft;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ZeroArgFunction;

public final class Fps extends ZeroArgFunction {
   @Override
   public LuaValue call() {
      return valueOf(Minecraft.getDebugFPS());
   }
}
