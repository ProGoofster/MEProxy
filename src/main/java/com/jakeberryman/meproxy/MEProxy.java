package com.jakeberryman.meproxy;

import com.jakeberryman.meproxy.entry.Registration;
import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(com.jakeberryman.meproxy.MEProxy.MODID)
public class MEProxy {

    // Directly reference a slf4j logger
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final String MODID = "meproxy";

    public MEProxy() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Registration.register(eventBus);
    }


    public static final Registrate REGISTERATE = Registrate.create(MODID);

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
