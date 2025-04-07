package com.jakeberryman.meproxy.item.part;


import appeng.items.parts.PartItem;

public class MeProxyProxyPartItem extends PartItem<MeProxyProxyPart> {
    public MeProxyProxyPartItem(Properties properties) {
        super(properties, MeProxyProxyPart.class, MeProxyProxyPart::new);
    }
}