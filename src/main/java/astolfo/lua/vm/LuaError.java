package astolfo.lua.vm;

public class LuaError extends RuntimeException {
   private static final long serialVersionUID = 1L;

   public LuaError(String message) {
      super(message);
   }

   public LuaError(String message, int level) {
      super(message);
   }

   public LuaError(Throwable cause) {
      super(String.valueOf(cause), cause);
   }

   public LuaValue getMessageObject() {
      return LuaValue.valueOf(String.valueOf(this.getMessage()));
   }
}
