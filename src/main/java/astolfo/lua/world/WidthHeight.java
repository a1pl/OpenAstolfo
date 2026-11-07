package astolfo.lua.world;

import astolfo.lua.WorldLibrary;
import net.minecraft.entity.Entity;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.Varargs;
import astolfo.lua.vm.VarArgFunction;

public final class WidthHeight extends VarArgFunction {
   @Override
   public Varargs onInvoke(Varargs varargs) {
      LuaValue luavalue = varargs.arg1();
      if (luavalue != null && !luavalue.isnil() && luavalue.isnumber()) {
         Entity entity = WorldLibrary.entitiesById.get(luavalue.checkint());
         if (entity != null) {
            return LuaValue.varargsOf(new LuaValue[]{LuaValue.valueOf((double)entity.width), LuaValue.valueOf((double)entity.height)});
         }
      }

      return NIL;
   }
}
