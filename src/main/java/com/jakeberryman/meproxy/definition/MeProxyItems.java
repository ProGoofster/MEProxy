package com.jakeberryman.meproxy.definition;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import appeng.api.parts.IPart;
import appeng.api.parts.IPartItem;
import appeng.api.parts.PartModels;
import appeng.core.definitions.ItemDefinition;
import appeng.items.parts.PartItem;
import appeng.items.parts.PartModelsHelper;
import com.jakeberryman.meproxy.MEProxy;
import com.jakeberryman.meproxy.item.part.MeProxyProxyPart;
import com.jakeberryman.meproxy.item.part.MeProxyProxyPartItem;
import net.minecraft.Util;
import net.minecraft.world.item.Item;

public class MeProxyItems {

    public static void init() {
        // controls static load order
        MEProxy.LOGGER.info("Initialised items.");
    }

    private static final List<ItemDefinition<?>> ITEMS = new ArrayList<>();

    public static final ItemDefinition<MeProxyProxyPartItem> ME_PROXY_PART = Util.make(() -> {
        PartModels.registerModels(PartModelsHelper.createModels(MeProxyProxyPart.class));
        return item("ME Proxy", "me_proxy_part", MeProxyProxyPartItem::new);
    });


    public static List<ItemDefinition<?>> getItems() {
        return Collections.unmodifiableList(ITEMS);
    }

    public static <T extends IPart> ItemDefinition<PartItem<T>> part(
            String englishName, String id, Class<T> partClass, Function<IPartItem<T>, T> factory) {
        PartModels.registerModels(PartModelsHelper.createModels(partClass));
        return item(englishName, id, p -> new PartItem<>(p, partClass, factory));
    }

    public static <T extends Item> ItemDefinition<T> item(
            String englishName, String id, Function<Item.Properties, T> factory) {
        var definition = new ItemDefinition<>(englishName, MEProxy.makeId(id), factory.apply(new Item.Properties()));
        ITEMS.add(definition);
        return definition;
    }
}
