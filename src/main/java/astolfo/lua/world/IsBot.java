package astolfo.lua.world;

import astolfo.lua.WorldLibrary;
import astolfo.module.hidden.BotDetector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class IsBot extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      if (!luaValue.isnil() && luaValue.isnumber() && luaValue.isint()) {
         Entity entity = WorldLibrary.entitiesById.get(luaValue.checkint());
         if (entity instanceof EntityLivingBase) {
            return valueOf(BotDetector.bots.contains(entity));
         }
      }

      return NIL;
   }
}
