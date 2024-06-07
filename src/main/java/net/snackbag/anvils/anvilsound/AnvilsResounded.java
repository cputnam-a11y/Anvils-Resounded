package net.snackbag.anvils.anvilsound;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnvilsResounded implements ModInitializer {
    public static final String MOD_ID = "anvilsound";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Boolean isEnchanting = null;

    @Override
    public void onInitialize() {
        LOGGER.info("Starting Anvils: Resounded");
    }
}
