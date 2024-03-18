package net.cathienova.havenalchemy;

import com.mojang.logging.LogUtils;
import net.cathienova.havenalchemy.block.ModBlocks;
import net.cathienova.havenalchemy.block.entity.ModBlockEntities;
import net.cathienova.havenalchemy.handler.BootsofMeowHandler;
import net.cathienova.havenalchemy.handler.*;
import net.cathienova.havenalchemy.item.*;
import net.cathienova.havenalchemy.loot.ModLootModifier;
import net.cathienova.havenalchemy.networking.ModMessages;
import net.cathienova.havenalchemy.recipe.ModRecipes;
import net.cathienova.havenalchemy.screen.AlchemicalChamberScreen;
import net.cathienova.havenalchemy.screen.GeneratorScreen;
import net.cathienova.havenalchemy.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.util.ArrayList;

@Mod(HavenAlchemy.MOD_ID)
public class HavenAlchemy
{
    public static final String MOD_ID = "havenalchemy";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static ArrayList<LivingEntity> tendrilEntities = new ArrayList<>();

    public HavenAlchemy()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);
        ModLootModifier.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMessages.register();
        ModEffects.register(modEventBus);
        ModEnchants.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(new MobDropHandler());
        MinecraftForge.EVENT_BUS.register(new DeathHandler());
        MinecraftForge.EVENT_BUS.register(BootsofMeowHandler.class);
        ModRecipes.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    public static void registerRenders(final FMLClientSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            registerRenders(event);
        }
    }
}
