package com.jakeberryman.meproxy;

import com.jakeberryman.meproxy.definition.*;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MEProxy.MODID)
public class MEProxy {

    public static final String MODID = "meproxy";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MEProxy() {
        initResources();
        //IEventBus modEventBus = MinecraftForge.EVENT_BUS;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener((RegisterEvent event) -> {
            if (event.getRegistryKey().equals(Registries.BLOCK)) {
                MeProxyBlocks.getBlocks().forEach(b -> {
                    ForgeRegistries.BLOCKS.register(b.id(), b.block());
                    ForgeRegistries.ITEMS.register(b.id(), b.asItem());
                });
            }
            if (event.getRegistryKey().equals(Registries.ITEM)) {
                MeProxyItems.getItems().forEach(i -> ForgeRegistries.ITEMS.register(i.id(), i.asItem()));
            }
            if (event.getRegistryKey().equals(Registries.BLOCK_ENTITY_TYPE)) {
                MeProxyBlockEntities.getBlockEntityTypes().forEach(ForgeRegistries.BLOCK_ENTITY_TYPES::register);
            }
            if (event.getRegistryKey().equals(Registries.CREATIVE_MODE_TAB)) {
                Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MeProxyCreativeTab.ID, MeProxyCreativeTab.TAB);
            }
        });
    }

    @Contract("_ -> new")
    public static @NotNull ResourceLocation makeId(String path) {
        //return ResourceLocation.fromNamespaceAndPath(MODID, path);
        return new ResourceLocation(MODID, path);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    public static void initResources() {
        MeProxyBlocks.init();
        MeProxyItems.init();
    }
}
