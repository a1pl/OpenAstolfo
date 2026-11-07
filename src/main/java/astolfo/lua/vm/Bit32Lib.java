package astolfo.lua.vm;

/** Opens javalua's bit32 library into the target environment. */
public class Bit32Lib extends TwoArgFunction {
   @Override
   public LuaValue call(LuaValue modname, LuaValue env) {
      env.checkglobals().open(com.github.esrrhs.javalua.BitLib::open);
      return env;
   }
}
