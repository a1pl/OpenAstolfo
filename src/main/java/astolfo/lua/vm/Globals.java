package astolfo.lua.vm;

import com.github.esrrhs.javalua.HostBridge;
import com.github.esrrhs.javalua.LClosure;
import com.github.esrrhs.javalua.LuaState;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/** A script environment: the global table of its own javalua state. */
public class Globals extends LuaTable {
   final LuaState state;

   public Globals() {
      this(Interop.newState());
   }

   private Globals(LuaState state) {
      super(state.getGlobals(), state);
      this.state = state;
   }

   @Override
   public Globals checkglobals() {
      return this;
   }

   /** Installs a library: calls it with ({@code ""}, this), as LuaJ does. */
   public LuaValue load(LuaValue library) {
      return library.call(EMPTYSTRING, this);
   }

   public LuaValue load(String script) {
      return this.load(script, "script", this);
   }

   public LuaValue load(String script, String chunkname) {
      return this.load(script, chunkname, this);
   }

   /** Compiles source text into the state that owns {@code env}. */
   public LuaValue load(String script, String chunkname, LuaValue env) {
      return env.checkglobals().compile(script, chunkname);
   }

   /** Compiles a classpath resource, or failing that a file, without running it. */
   public LuaValue loadfile(String filename) {
      try (InputStream in = openSource(filename)) {
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         byte[] buffer = new byte[8192];
         for (int n; (n = in.read(buffer)) != -1; ) {
            out.write(buffer, 0, n);
         }

         return this.compile(new String(out.toByteArray(), StandardCharsets.UTF_8), filename);
      } catch (IOException ioException) {
         throw new LuaError("cannot open " + filename);
      }
   }

   private static InputStream openSource(String filename) throws IOException {
      InputStream resource = Globals.class.getResourceAsStream(filename);
      return resource != null ? resource : new FileInputStream(filename);
   }

   private LuaValue compile(String script, String chunkname) {
      byte[] source = script.getBytes(StandardCharsets.UTF_8);
      if (source.length > 0 && source[0] == 27) {
         throw new LuaError("binary chunks are not allowed");
      }

      synchronized (this.state) {
         try {
            LClosure closure = this.state.load(source, "=" + chunkname);
            return new ScriptFunction(com.github.esrrhs.javalua.LuaValue.ofClosure(closure), this.state);
         } catch (RuntimeException runtimeException) {
            throw new LuaError(runtimeException.getMessage());
         }
      }
   }

   void open(Consumer<LuaState> opener) {
      synchronized (this.state) {
         int top = HostBridge.top(this.state);
         opener.accept(this.state);
         HostBridge.restoreTop(this.state, top);
      }
   }
}
