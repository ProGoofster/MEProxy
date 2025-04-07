package com.jakeberryman.meproxy.block.entity;

import appeng.api.networking.GridFlags;
import appeng.blockentity.grid.AENetworkBlockEntity;
import appeng.me.storage.NetworkStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MeProxyProxyBlockEntity extends AENetworkBlockEntity {
    public MeProxyProxyBlockEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState blockState) {
        super(blockEntityType, pos, blockState);
        this.getMainNode().setFlags(GridFlags.REQUIRE_CHANNEL);
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (getMainNode().getGrid() != null && (cap == ForgeCapabilities.ITEM_HANDLER || cap == ForgeCapabilities.FLUID_HANDLER)) {
            return LazyOptional.of(() -> new MEProxyInventoryHandler(((NetworkStorage) getMainNode().getGrid().getStorageService().getInventory()))).cast();
        }

        return super.getCapability(cap, side);
    }
}