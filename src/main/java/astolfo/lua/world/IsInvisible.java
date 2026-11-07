package astolfo.lua.world;

import astolfo.lua.WorldLibrary;
import net.minecraft.entity.Entity;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class IsInvisible extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      if (!luaValue.isnil() && luaValue.isnumber() && luaValue.isint()) {
         Entity entity = WorldLibrary.entitiesById.get(luaValue.checkint());
         if (entity != null) {
            return valueOf(entity.isInvisible());
         }
      }

      return NIL;
   }
}
