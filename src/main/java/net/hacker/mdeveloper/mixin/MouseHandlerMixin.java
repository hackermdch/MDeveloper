package net.hacker.mdeveloper.mixin;

import net.hacker.mdeveloper.Native;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {
    @Inject(method = "onMove", at = @At("RETURN"))
    private void onMove(long pWindowPointer, double pXpos, double pYpos, CallbackInfo ci) {
        Native.cursorPos(pWindowPointer, pXpos, pYpos);
    }

    @Inject(method = "onPress", at = @At("RETURN"))
    private void onPress(long pWindowPointer, int pButton, int pAction, int pModifiers, CallbackInfo ci) {
        Native.mouseButton(pWindowPointer, pButton, pAction, pModifiers);
    }

    @Inject(method = "onScroll", at = @At("RETURN"))
    private void onScroll(long pWindowPointer, double pXOffset, double pYOffset, CallbackInfo ci) {
        Native.scroll(pWindowPointer, pXOffset, pYOffset);
    }
}
