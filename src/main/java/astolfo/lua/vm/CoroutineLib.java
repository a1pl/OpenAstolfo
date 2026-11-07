package astolfo.lua.vm;

/** Opens javalua's coroutine library into the target environment. */
public class CoroutineLib extends TwoArgFunction {
   @Override
   public LuaValue call(LuaValue modname, LuaValue env) {
      env.checkglobals().open(com.github.esrrhs.javalua.CoroutineLib::open);
      return env;
   }
}
