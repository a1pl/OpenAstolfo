package astolfo.lua.modulemanager;

import astolfo.Astolfo;
import astolfo.lua.ModuleManagerLibrary2;
import astolfo.module.ModuleInfo;
import astolfo.lua.vm.LuaTable;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.Varargs;
import astolfo.lua.vm.VarArgFunction;

public final class Modules extends VarArgFunction {
   @Override
   public Varargs invoke() {
      if (ModuleManagerLibrary2.getModuleNames().isEmpty()) {
         for (ModuleInfo moduleInfo : Astolfo.INSTANCE.getModuleManager().getList()) {
            ModuleManagerLibrary2.getModuleNames().add(valueOf(moduleInfo.getName()));
         }
      }

      LuaValue[] luaValues = (LuaValue[])(ModuleManagerLibrary2.getModuleNames().toArray(new LuaValue[0]));
      return LuaValue.varargsOf(new LuaValue[]{new LuaTable(LuaValue.varargsOf(luaValues))});
   }
}
