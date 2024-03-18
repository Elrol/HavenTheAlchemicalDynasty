package net.cathienova.havenalchemy.mixin;

import net.cathienova.havenalchemy.HavenAlchemy;
import net.cathienova.havenalchemy.item.ModEffects;
import net.cathienova.havenalchemy.util.SoundUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @Shadow @Nullable public LocalPlayer player;

    @Inject(method = "shouldEntityAppearGlowing", at = @At("HEAD"), cancellable = true)
    public void shouldEntityAppearGlowingCallback(Entity entity, CallbackInfoReturnable<Boolean> cir){
        if(SoundUtil.entityMap.containsKey(entity) && SoundUtil.distanceMap.containsKey(entity)){
            assert this.player != null;
            float distance = SoundUtil.distanceMap.get(entity);
            if(this.player.closerThan(entity, distance, distance * 0.7F) && (HavenAlchemy.tendrilEntities.contains(this.player)||this.player.hasEffect(ModEffects.ECHOLOCATE.get()))){
                cir.setReturnValue(true);
            }
        }
    }
}