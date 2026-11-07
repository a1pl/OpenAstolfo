package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.Varargs;
import astolfo.lua.vm.VarArgFunction;

public final class Strafe extends VarArgFunction {
   @Override
   public Varargs onInvoke(Varargs varargs) {
      return (Varargs)(ClientAccess.mc.thePlayer == null
         ? NIL
         : LuaValue.varargsOf(
            new LuaValue[]{
               LuaValue.valueOf((double)ClientAccess.mc.thePlayer.movementInput.moveForward),
               LuaValue.valueOf((double)ClientAccess.mc.thePlayer.movementInput.moveStrafe)
            }
         ));
   }
}
