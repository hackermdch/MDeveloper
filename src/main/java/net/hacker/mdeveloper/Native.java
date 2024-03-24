package net.hacker.mdeveloper;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

public class Native {
    static {
        var arch = System.getProperty("os.arch");
        var path = "";
        var os = System.getProperty("os.name");
        var name = "";
        if (arch.equals("aarch64")) path = "/natives/arm/";
        else if (arch.equals("amd64")) path = "/natives/";
        if (path.isEmpty()) throw new RuntimeException("Unsupported CPU architecture: " + arch);
        if (os.contains("Windows")) path += name = "MDeveloper.dll";
        else if (os.contains("Linux")) path += name = "libMDeveloper.so";
        else if (os.contains("Mac OS")) path += name = "libMDeveloper.dylib";
        if (path.endsWith("/")) throw new RuntimeException("Unsupported system: " + os);
        var lib = System.getProperty("java.io.tmpdir") + "/" + name;
        try {
            try (var fo = new FileOutputStream(lib); var in = Native.class.getResourceAsStream(path)) {
                fo.write(Objects.requireNonNull(in).readAllBytes());
            }
            System.load(lib);
            try (var res = Native.class.getResourceAsStream("/natives/res")) {
                var data = Objects.requireNonNull(res).readAllBytes();
                res(ByteBuffer.allocateDirect(data.length).put(data));
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static native void res(ByteBuffer buff);

    public static native void init(long ptr, long handle);

    public static native void render();

    public static native void windowFocus(long window, boolean focused);

    public static native void cursorEnter(long window, boolean entered);

    public static native void cursorPos(long window, double xpos, double ypos);

    public static native void mouseButton(long window, int button, int action, int mods);

    public static native void scroll(long window, double xoffset, double yoffset);

    public static native void key(long window, int key, int scancode, int action, int mods);

    public static native void charMods(long window, int codepoint, int mods);

    public static native void monitor(long monitor, int event);
}
