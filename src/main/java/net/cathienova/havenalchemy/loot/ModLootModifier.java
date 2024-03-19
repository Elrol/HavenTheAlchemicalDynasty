package net.cathienova.havenalchemy.loot;

import com.mojang.serialization.Codec;
import net.cathienova.havenalchemy.HavenAlchemy;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifier
{
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, HavenAlchemy.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> add_item =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", AddItemModifier.CODEC);

    public static void register(IEventBus eventBus)
    {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
