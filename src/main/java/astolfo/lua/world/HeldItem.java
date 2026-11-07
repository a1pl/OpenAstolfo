package astolfo.lua.world;

import astolfo.lua.WorldLibrary;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.OneArgFunction;

public final class HeldItem extends OneArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue) {
      if (!luaValue.isnil() && luaValue.isnumber() && luaValue.isint()) {
         Entity entity = WorldLibrary.entitiesById.get(luaValue.checkint());
         if (entity instanceof EntityLivingBase) {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entity;
            ItemStack itemstack = entitylivingbase.getHeldItem();
            if (itemstack != null) {
               return valueOf(entitylivingbase.getHeldItem().getDisplayName());
            }

            return valueOf("hand");
         }
      }

      return NIL;
   }
}
