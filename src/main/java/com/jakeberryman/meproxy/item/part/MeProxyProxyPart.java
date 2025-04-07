package com.jakeberryman.meproxy.item.part;

import appeng.api.networking.IGridNodeListener;
import appeng.api.parts.IPartCollisionHelper;
import appeng.api.parts.IPartItem;
import appeng.api.stacks.AEFluidKey;
import appeng.core.AppEngBase;
import appeng.items.parts.PartModels;
import appeng.parts.PartModel;
import com.jakeberryman.meproxy.MEProxy;
import net.minecraft.resources.ResourceLocation;
import appeng.parts.AEBasePart;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class MeProxyProxyPart extends AEBasePart {


    @PartModels
    public static List<ResourceLocation> MODELS = Arrays.asList(
            ResourceLocation.fromNamespaceAndPath(MEProxy.MODID, "part/me_proxy_base"),
            ResourceLocation.fromNamespaceAndPath(AppEngBase.MOD_ID, "part/interface_on"),
            ResourceLocation.fromNamespaceAndPath(AppEngBase.MOD_ID, "part/interface_off"),
            ResourceLocation.fromNamespaceAndPath(AppEngBase.MOD_ID, "part/interface_has_channel")
    );

    public static final PartModel MODELS_OFF = new PartModel(MODELS.get(0), MODELS.get(2));
    public static final PartModel MODELS_ON = new PartModel(MODELS.get(0), MODELS.get(1));
    public static final PartModel MODELS_HAS_CHANNEL = new PartModel(MODELS.get(0), MODELS.get(3));


    public MeProxyProxyPart(IPartItem<?> partItem) {
        super(partItem);
    }



    @Override
    public void getBoxes(IPartCollisionHelper bch) {
        bch.addBox(2, 2, 14, 14, 14, 16);
        bch.addBox(5, 5, 12, 11, 11, 14);
    }

}
