package astolfo.lua.world;

import astolfo.ClientAccess;
import astolfo.lua.WorldLibrary;
import net.minecraft.entity.Entity;
import astolfo.lua.vm.LuaTable;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.Varargs;
import astolfo.lua.vm.VarArgFunction;

public final class Entities extends VarArgFunction {
   @Override
   public Varargs invoke(Varargs varargs) {
      if (ClientAccess.mc.theWorld == null) {
         WorldLibrary.entitiesById.clear();
         return NIL;
      }

      WorldLibrary.getEntityIdList().clear();
      WorldLibrary.entitiesById.clear();

      for (Entity entity : ClientAccess.mc.theWorld.loadedEntityList) {
         WorldLibrary.getEntityIdList().add(valueOf(entity.getEntityId()));
         WorldLibrary.entitiesById.put(entity.getEntityId(), entity);
      }

      LuaValue[] luaValues = (LuaValue[])(WorldLibrary.getEntityIdList().toArray(new LuaValue[0]));
      return LuaValue.varargsOf(new LuaValue[]{new LuaTable(LuaValue.varargsOf(luaValues))});
   }
}
