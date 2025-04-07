package com.jakeberryman.meproxy.definition;
import java.util.ArrayList;

import appeng.block.AEBaseBlock;
import appeng.block.AEBaseBlockItem;
import appeng.core.definitions.ItemDefinition;
import appeng.items.AEBaseItem;
import com.jakeberryman.meproxy.MEProxy;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
public class MeProxyCreativeTab {
    public static final ResourceLocation ID = MEProxy.makeId("tab");

    public static final CreativeModeTab TAB = CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.eae"))
            .icon(MeProxyBlocks.ME_PROXY::stack)
            .displayItems(MeProxyCreativeTab::populateTab)
            .build();

    private static void populateTab(CreativeModeTab.ItemDisplayParameters ignored, CreativeModeTab.Output output) {
        var itemDefs = new ArrayList<ItemDefinition<?>>();
        itemDefs.addAll(MeProxyItems.getItems());
        itemDefs.addAll(MeProxyBlocks.getBlocks());

        for (var itemDef : itemDefs) {
            var item = itemDef.asItem();

            // For block items, the block controls the creative tab
            if (item instanceof AEBaseBlockItem baseItem && baseItem.getBlock() instanceof AEBaseBlock baseBlock) {
                baseBlock.addToMainCreativeTab(output);
            } else if (item instanceof AEBaseItem baseItem) {
                baseItem.addToMainCreativeTab(output);
            } else {
                output.accept(itemDef);
            }
        }
    }
}
