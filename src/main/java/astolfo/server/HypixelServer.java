package astolfo.server;

public final class HypixelServer extends Server {
   private static String Y;

   public HypixelServer() {
      super("cc", "rewound", ServerType.HYPIXEL);
   }

   @Override
   public boolean isBrandValid() {
      return true;
   }

   public static void V(String string) {
      Y = string;
   }

   static {
      V("QYo5Qc");
   }
}
