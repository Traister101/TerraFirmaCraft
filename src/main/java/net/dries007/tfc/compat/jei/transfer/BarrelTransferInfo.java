/*
 * Licensed under the EUPL, Version 1.2.
 * You may obtain a copy of the Licence at:
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 */

package net.dries007.tfc.compat.jei.transfer;

import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.Nullable;

import net.dries007.tfc.common.blockentities.BarrelBlockEntity;
import net.dries007.tfc.common.container.BarrelContainer;
import net.dries007.tfc.common.container.TFCContainerTypes;

public class BarrelTransferInfo<R> extends BaseTransferInfo<BarrelContainer, R>
{
    private final IRecipeTransferHandlerHelper transferHelper;

    public BarrelTransferInfo(IRecipeTransferHandlerHelper transferHelper, RecipeType<R> recipeType)
    {
        super(BarrelContainer.class, TFCContainerTypes.BARREL.get(), recipeType, BarrelBlockEntity.SLOT_ITEM, 1, BarrelBlockEntity.SLOTS, Inventory.INVENTORY_SIZE);
        this.transferHelper = transferHelper;
    }

    @Override
    public boolean canHandle(BarrelContainer container, R recipe)
    {
        return container.getBlockEntity().canModify();
    }

    @Nullable
    @Override
    public IRecipeTransferError getHandlingError(BarrelContainer container, R recipe)
    {
        return transferHelper.createUserErrorWithTooltip(Component.translatable("tfc.jei.transfer.error.barrel_sealed"));
    }
}