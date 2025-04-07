package com.jakeberryman.meproxy.definition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import appeng.core.definitions.BlockDefinition;
import com.jakeberryman.meproxy.MEProxy;
import com.jakeberryman.meproxy.block.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MeProxyBlocks {
    private static final List<BlockDefinition<?>> BLOCKS = new ArrayList<>();
    public static final BlockDefinition<MeProxyProxyBlock> ME_PROXY = block(
            "ME Proxy",
            "me_proxy",
            MeProxyProxyBlock::new,
            MeProxyProxyBlockItem::new
    );

    public static void init() {
        // controls static load order
        MEProxy.LOGGER.info("Initialised blocks.");
    }

    public static List<BlockDefinition<?>> getBlocks() {
        return Collections.unmodifiableList(BLOCKS);
    }

    public static <T extends Block> BlockDefinition<T> block(
            String englishName,
            String id,
            Supplier<T> blockSupplier,
            BiFunction<Block, Item.Properties, BlockItem> itemFactory) {
        var block = blockSupplier.get();
        var item = itemFactory.apply(block, new Item.Properties());

        var definition = new BlockDefinition<>(englishName, MEProxy.makeId(id), block, item);
        BLOCKS.add(definition);
        return definition;
    }

}
