package astolfo.lua.vm;

/** Opens javalua's math library into the target environment. */
public class JseMathLib extends TwoArgFunction {
   @Override
   public LuaValue call(LuaValue modname, LuaValue env) {
      env.checkglobals().open(com.github.esrrhs.javalua.MathLib::open);
      return env;
   }
}
