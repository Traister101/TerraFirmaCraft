/*
 * Licensed under the EUPL, Version 1.2.
 * You may obtain a copy of the Licence at:
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 */

package net.dries007.tfc.compat.jei.transfer;

import java.util.List;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.ingredient.IRecipeSlotView;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.transfer.IRecipeTransferError;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.Nullable;

/**
 * Transfer handler which filters {@link #transferRecipe(AbstractContainerMenu, Object, IRecipeSlotsView, Player, boolean, boolean)}s {@link IRecipeSlotsView} param
 * to exclude any fluid. This filtered {@link IRecipeSlotsView} is then passed to the {@link #wrappedTransferHandler}
 */
public class FluidIgnoringRecipeTransferHandler<C extends AbstractContainerMenu, R> extends BaseTransferHandler<C, R>
{
    private final IRecipeTransferHandlerHelper transferHelper;
    private final IRecipeTransferHandler<C, R> wrappedTransferHandler;

    /**
     * @param wrappedTransferHandler The {@link IRecipeTransferHandler} to wrap
     */
    public FluidIgnoringRecipeTransferHandler(IRecipeTransferHandlerHelper transferHelper, IRecipeTransferHandler<C, R> wrappedTransferHandler)
    {
        super(wrappedTransferHandler.getContainerClass(), wrappedTransferHandler.getMenuType().orElse(null), wrappedTransferHandler.getRecipeType());
        this.transferHelper = transferHelper;
        this.wrappedTransferHandler = wrappedTransferHandler;
    }

    @Nullable
    @Override
    public IRecipeTransferError transferRecipe(C container, R recipe, IRecipeSlotsView recipeSlots, Player player, boolean maxTransfer, boolean doTransfer)
    {
        List<IRecipeSlotView> slotViews = recipeSlots.getSlotViews().stream().filter(iRecipeSlotView -> iRecipeSlotView.getIngredients(ForgeTypes.FLUID_STACK).findAny().isEmpty()).toList();
        return wrappedTransferHandler.transferRecipe(container, recipe, transferHelper.createRecipeSlotsView(slotViews), player, maxTransfer, doTransfer);
    }
}