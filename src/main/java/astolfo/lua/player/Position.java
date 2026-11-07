package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.Varargs;
import astolfo.lua.vm.VarArgFunction;

public final class Position extends VarArgFunction {
   @Override
   public Varargs onInvoke(Varargs varargs) {
      return (Varargs)(ClientAccess.mc.thePlayer == null
         ? NIL
         : LuaValue.varargsOf(
            new LuaValue[]{
               LuaValue.valueOf(ClientAccess.mc.thePlayer.posX),
               LuaValue.valueOf(ClientAccess.mc.thePlayer.posY),
               LuaValue.valueOf(ClientAccess.mc.thePlayer.posZ)
            }
         ));
   }
}
