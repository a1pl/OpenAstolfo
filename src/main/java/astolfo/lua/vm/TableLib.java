package astolfo.lua.vm;

/** Opens javalua's table library into the target environment. */
public class TableLib extends TwoArgFunction {
   @Override
   public LuaValue call(LuaValue modname, LuaValue env) {
      env.checkglobals().open(com.github.esrrhs.javalua.TableLib::open);
      return env;
   }
}
