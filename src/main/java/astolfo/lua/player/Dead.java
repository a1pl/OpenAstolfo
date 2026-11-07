package astolfo.lua.player;

import astolfo.ClientAccess;
import net.minecraft.client.entity.EntityPlayerSP;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ZeroArgFunction;

public final class Dead extends ZeroArgFunction {
   @Override
   public LuaValue call() {
      EntityPlayerSP entityplayersp = ClientAccess.mc.thePlayer;
      if (entityplayersp != null) {
         boolean flag = ClientAccess.mc.thePlayer.isEntityAlive();
         return valueOf(!flag);
      } else {
         return valueOf(false);
      }
   }
}
