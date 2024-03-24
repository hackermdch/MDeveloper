package net.hacker.mdeveloper.mixin;

import net.hacker.mdeveloper.Native;
import net.minecraft.client.KeyboardHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardHandlerMixin {
    @Inject(method = "keyPress", at = @At("RETURN"))
    private void keyPress(long pWindowPointer, int pKey, int pScanCode, int pAction, int pModifiers, CallbackInfo ci) {
        Native.key(pWindowPointer, pKey, pScanCode, pAction, pModifiers);
    }

    @Inject(method = "charTyped", at = @At("RETURN"))
    private void charTyped(long pWindowPointer, int pCodePoint, int pModifiers, CallbackInfo ci) {
        Native.charMods(pWindowPointer, pCodePoint, pModifiers);
    }
}
