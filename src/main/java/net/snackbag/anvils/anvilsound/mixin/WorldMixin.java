package net.snackbag.anvils.anvilsound.mixin;

import net.snackbag.anvils.anvilsound.AnvilsResounded;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

@Mixin(World.class)
public abstract class WorldMixin {

	@ModifyVariable(method = "playSoundAtBlockCenterClient", at = @At("HEAD"), argsOnly = true, index = 2)
	private SoundEvent onProcessWorld(SoundEvent sound) {
		if (sound == SoundEvents.BLOCK_ANVIL_USE) {
			if (AnvilsResounded.isEnchanting != null) {
				if (AnvilsResounded.isEnchanting) {
					AnvilsResounded.isEnchanting = null;
					return SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE;
				}
			}
		}
		return sound;
	}
}
