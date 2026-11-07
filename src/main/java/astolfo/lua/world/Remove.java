package astolfo.lua.world;

import astolfo.ClientAccess;
import astolfo.lua.WorldLibrary;
import net.minecraft.entity.Entity;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class Remove extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      if (!luaValue.isnil() && luaValue.isnumber() && luaValue.isint()) {
         Entity entity = WorldLibrary.entitiesById.get(luaValue.checkint());
         if (entity != null) {
            ClientAccess.mc.theWorld.removeEntity(entity);
            return valueOf(true);
         }
      }

      return valueOf(false);
   }
}
