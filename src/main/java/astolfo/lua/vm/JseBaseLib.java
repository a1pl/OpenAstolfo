package astolfo.lua.vm;

/** Opens javalua's base functions (assert, pairs, pcall, setmetatable, ...) library into the target environment. */
public class JseBaseLib extends TwoArgFunction {
   @Override
   public LuaValue call(LuaValue modname, LuaValue env) {
      env.checkglobals().open(com.github.esrrhs.javalua.BaseLib::open);
      return env;
   }
}
