package astolfo.script;

import astolfo.lua.OsLibrary;
import astolfo.lua.vm.Globals;
import astolfo.lua.vm.LoadState;
import astolfo.lua.vm.LuaC;
import astolfo.lua.vm.CoroutineLib;
import astolfo.lua.vm.StringLib;
import astolfo.lua.vm.JseBaseLib;
import astolfo.lua.vm.JseMathLib;

public class LuaGlobals {
   public static Globals globals;
   private static String[] k;

   public static void init() {
      String[] astring = new String[0];
      globals = new Globals();
      globals.load(new JseBaseLib());
      globals.load(new OsLibrary());
      globals.load(new StringLib());
      globals.load(new JseMathLib());
      globals.load(new CoroutineLib());
      LoadState.install(globals);
      LuaC.install(globals);
   }
}
