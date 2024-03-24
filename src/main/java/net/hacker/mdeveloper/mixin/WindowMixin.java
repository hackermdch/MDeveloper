package net.hacker.mdeveloper.mixin;

import com.mojang.blaze3d.platform.DisplayData;
import com.mojang.blaze3d.platform.ScreenManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.platform.WindowEventHandler;
import net.hacker.mdeveloper.Native;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {
    @Shadow
    @Final
    private long window;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(WindowEventHandler pEventHandler, ScreenManager pScreenManager, DisplayData pDisplayData, String pPreferredFullscreenVideoMode, String pTitle, CallbackInfo ci) {
        Native.init(GLFW.Functions.GetProcAddress, window);
    }

    @Inject(method = "onFocus", at = @At("RETURN"))
    private void onFocus(long window, boolean focused, CallbackInfo ci) {
        Native.windowFocus(window, focused);
    }

    @Inject(method = "onEnter", at = @At("RETURN"))
    private void onEnter(long window, boolean entered, CallbackInfo ci) {
        Native.cursorEnter(window, entered);
    }
}
