package com.jakeberryman.meproxy.entry;

import com.jakeberryman.meproxy.MEProxy;
import com.jakeberryman.meproxy.content.meProxy.MEProxyBlock;
import com.jakeberryman.meproxy.content.meProxy.MEProxyBlockEntity;
import com.jakeberryman.meproxy.content.meProxy.MEProxyBlockItem;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class Registration {


    static {
        com.jakeberryman.meproxy.MEProxy.REGISTERATE.addRawLang("itemGroup.meproxy", "ME Proxy");
        com.jakeberryman.meproxy.MEProxy.REGISTERATE.addRawLang("tooltip.meproxy.me_proxy", "A proxy to access §fME §rnetwork's storage");
    }

    public static BlockEntry<MEProxyBlock> meProxyBlock = com.jakeberryman.meproxy.MEProxy.REGISTERATE
            .block("me_proxy", MEProxyBlock::new)
            .lang("ME Proxy")
            .properties(BlockBehaviour.Properties::noOcclusion)
            .item(MEProxyBlockItem::new)
            .build()
            .register();

    public static BlockEntityEntry<MEProxyBlockEntity> meProxyBlockEntity = com.jakeberryman.meproxy.MEProxy.REGISTERATE.blockEntity("me_proxy", MEProxyBlockEntity::new)
            .validBlock(meProxyBlock)
            .onRegister(blockEntityType -> meProxyBlock.get().setBlockEntity(MEProxyBlockEntity.class, blockEntityType, (p_155253_, p_155254_, p_155255_, p_155256_) -> {}, (p_155253_, p_155254_, p_155255_, p_155256_) -> {}))
            .register();

    private static final DeferredRegister<CreativeModeTab> REGISTER
            = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, com.jakeberryman.meproxy.MEProxy.MODID);

    public static final RegistryObject<CreativeModeTab> TAB =
            REGISTER.register("meproxy",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.meproxy"))
                            .icon(() -> meProxyBlock.get().asItem().getDefaultInstance())
                            .displayItems(
                                    (parameters, output) ->
                                            output.acceptAll(
                                                    com.jakeberryman.meproxy.MEProxy.REGISTERATE.getAll(Registries.ITEM).stream().map(
                                                            regObj -> new ItemStack(regObj.get())
                                                    ).toList()
                                            )
                            )
                            .build());

    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }
}
