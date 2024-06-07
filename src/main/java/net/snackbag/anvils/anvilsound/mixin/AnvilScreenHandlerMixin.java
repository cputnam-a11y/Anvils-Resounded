package net.snackbag.anvils.anvilsound.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.snackbag.anvils.anvilsound.AnvilsResounded;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {
    @Inject(method = "updateResult", at = @At("RETURN"))
    private void onUpdateResult(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        AnvilScreenHandler handler = (AnvilScreenHandler) (Object) this;
        Slot leftSlot = handler.getSlot(0);
        Slot rightSlot = handler.getSlot(1);
        Slot outputSlot = handler.getSlot(2);

        ItemStack leftStack = leftSlot.getStack();
        ItemStack rightStack = rightSlot.getStack();
        ItemStack outputStack = outputSlot.getStack();

        if (client.player != null && client.world != null) {
            // Check if combining an item with an enchanted book
            if (!leftStack.isEmpty() && rightStack.getItem() == Items.ENCHANTED_BOOK && !outputStack.isEmpty()) {
                AnvilsResounded.isEnchanting = true;
            }

            if (!leftStack.isEmpty() && rightStack.getItem() != Items.ENCHANTED_BOOK && !outputStack.isEmpty()) {
                AnvilsResounded.isEnchanting = false;
            }
        }
    }
}
