package net.snackbag.anvils.anvilsound.mixin;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.snackbag.anvils.anvilsound.AnvilsResounded;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow public abstract void playSoundAtBlockCenter(BlockPos pos, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean useDistance);

    @Inject(method = "playSoundAtBlockCenter", at = @At("HEAD"), cancellable = true)
    private void onProcessWorld(BlockPos pos, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean useDistance, CallbackInfo ci) {
        if (sound == SoundEvents.BLOCK_ANVIL_USE) {
            if (AnvilsResounded.isEnchanting != null) {
                if (AnvilsResounded.isEnchanting) {
                    ci.cancel();
                    AnvilsResounded.isEnchanting = null;
                    playSoundAtBlockCenter(pos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, category, volume, pitch, useDistance);
                }
            }
        }
    }
}
