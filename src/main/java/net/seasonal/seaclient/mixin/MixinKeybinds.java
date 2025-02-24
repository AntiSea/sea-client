package net.seasonal.seaclient.mixin;

import net.minecraft.client.MinecraftClient;
import net.seasonal.seaclient.SeaClientClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinKeybinds {
    
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo info) {
        SeaClientClient.checkKeybinds();
    }
}
