package astolfo.lua.player;

import astolfo.ClientAccess;
import astolfo.lua.WorldLibrary;
import net.minecraft.entity.Entity;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class DistanceToEntity extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      if (ClientAccess.mc.thePlayer != null && luaValue.isint()) {
         Entity entity = WorldLibrary.entitiesById.get(luaValue.checkint());
         if (entity != null) {
            return valueOf((double)ClientAccess.mc.thePlayer.getDistanceToEntity(entity));
         }
      }

      return valueOf(false);
   }
}
