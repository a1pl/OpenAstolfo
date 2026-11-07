package astolfo.lua.vm;

/** Opens javalua's string, plus the string metatable library into the target environment. */
public class StringLib extends TwoArgFunction {
   @Override
   public LuaValue call(LuaValue modname, LuaValue env) {
      env.checkglobals().open(com.github.esrrhs.javalua.StringLib::open);
      return env;
   }
}
