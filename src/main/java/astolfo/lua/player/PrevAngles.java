package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.Varargs;
import astolfo.lua.vm.VarArgFunction;

public final class PrevAngles extends VarArgFunction {
   @Override
   public Varargs onInvoke(Varargs varargs) {
      return LuaValue.varargsOf(
         new LuaValue[]{
            LuaValue.valueOf((double)ClientAccess.mc.thePlayer.prevRotationYaw), LuaValue.valueOf((double)ClientAccess.mc.thePlayer.prevRotationPitch)
         }
      );
   }
}
