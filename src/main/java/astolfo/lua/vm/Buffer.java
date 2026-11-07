package astolfo.lua.vm;

public final class Buffer {
   private final StringBuilder sb;

   public Buffer() {
      this.sb = new StringBuilder();
   }

   public Buffer(int initialCapacity) {
      this.sb = new StringBuilder(initialCapacity);
   }

   public Buffer append(String s) {
      this.sb.append(s);
      return this;
   }

   public Buffer append(char c) {
      this.sb.append(c);
      return this;
   }

   public Buffer append(byte b) {
      this.sb.append((char)(b & 255));
      return this;
   }

   public Buffer append(LuaValue v) {
      this.sb.append(v.tojstring());
      return this;
   }

   public String tojstring() {
      return this.sb.toString();
   }

   public LuaValue tostring() {
      return LuaValue.valueOf(this.sb.toString());
   }

   @Override
   public String toString() {
      return this.sb.toString();
   }
}
