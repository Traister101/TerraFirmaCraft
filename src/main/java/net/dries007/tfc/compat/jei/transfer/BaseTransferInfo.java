/*
 * Licensed under the EUPL, Version 1.2.
 * You may obtain a copy of the Licence at:
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 */

package net.dries007.tfc.compat.jei.transfer;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferInfo;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.Nullable;

public abstract class BaseTransferInfo<C extends AbstractContainerMenu, R> implements IRecipeTransferInfo<C, R>
{
    private final Class<? extends C> containerClass;
    @Nullable private final MenuType<C> menuType;
    private final RecipeType<R> recipeType;
    private final int recipeSlotStart;
    private final int recipeSlotCount;
    private final int inventorySlotStart;
    private final int inventorySlotCount;

    protected BaseTransferInfo(Class<? extends C> containerClass, @Nullable MenuType<C> menuType, RecipeType<R> recipeType, int recipeSlotStart, int recipeSlotCount, int inventorySlotStart, int inventorySlotCount)
    {
        this.containerClass = containerClass;
        this.menuType = menuType;
        this.recipeType = recipeType;
        this.recipeSlotStart = recipeSlotStart;
        this.recipeSlotCount = recipeSlotCount;
        this.inventorySlotStart = inventorySlotStart;
        this.inventorySlotCount = inventorySlotCount;
    }

    @Override
    public final Class<? extends C> getContainerClass()
    {
        return containerClass;
    }

    @Override
    public final Optional<MenuType<C>> getMenuType()
    {
        return Optional.ofNullable(menuType);
    }

    @Override
    public final RecipeType<R> getRecipeType()
    {
        return recipeType;
    }

    @Override
    public List<Slot> getRecipeSlots(C container, R recipe)
    {
        return IntStream.range(recipeSlotStart, recipeSlotStart + recipeSlotCount).mapToObj(container::getSlot).toList();
    }

    @Override
    public List<Slot> getInventorySlots(C container, R recipe)
    {
        return IntStream.range(inventorySlotStart, inventorySlotStart + inventorySlotCount).mapToObj(container::getSlot).toList();
    }
}