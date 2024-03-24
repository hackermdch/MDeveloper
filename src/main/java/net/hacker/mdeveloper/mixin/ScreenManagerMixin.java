package net.hacker.mdeveloper.mixin;

import com.mojang.blaze3d.platform.ScreenManager;
import net.hacker.mdeveloper.Native;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenManager.class)
public class ScreenManagerMixin {
    @Inject(method = "onMonitorChange", at = @At("RETURN"))
    private void onMonitorChange(long monitor, int event, CallbackInfo ci) {
        Native.monitor(monitor, event);
    }
}
