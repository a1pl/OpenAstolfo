package com.github.esrrhs.javalua;

/**
 * Host-side access to the few {@link LuaState} internals an embedder needs but
 * the public API keeps package-private. Added for OpenAstolfo; not upstream.
 */
public final class HostBridge {
    private HostBridge() {
    }

    public static LuaState mainThread(LuaState L) {
        return L.mainThread;
    }

    /** Absolute index of the first free stack slot. */
    public static int top(LuaState L) {
        return L.top;
    }

    /** True for the exceptions the VM itself uses to unwind (errors and coroutine yields). */
    public static boolean isVmUnwind(Throwable t) {
        return t instanceof LuaError || t instanceof LuaYield;
    }

    /** Pop everything at or above absolute index {@code top}. */
    public static void restoreTop(LuaState L, int top) {
        while (L.top > top) {
            L.stack[--L.top].setNil();
        }
    }
}
