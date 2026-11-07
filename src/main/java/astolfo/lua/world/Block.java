package astolfo.lua.world;

import astolfo.ClientAccess;
import net.minecraft.util.BlockPos;
import astolfo.lua.vm.LuaValue;
import astolfo.lua.vm.ThreeArgFunction;

public final class Block extends ThreeArgFunction {
   @Override
   public LuaValue call(LuaValue luaValue, LuaValue luaValue2, LuaValue luaValue3) {
      if (luaValue.isnumber() && luaValue2.isnumber() && luaValue3.isnumber()) {
         net.minecraft.block.Block block2 = ClientAccess.mc
            .theWorld
            .getBlockState(new BlockPos(luaValue.todouble(), luaValue2.todouble(), luaValue3.todouble()))
            .getBlock();
         return block2 != null ? valueOf(block2.getUnlocalizedName()) : valueOf("air");
      } else {
         return NIL;
      }
   }
}
