/*
 * Licensed under the EUPL, Version 1.2.
 * You may obtain a copy of the Licence at:
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 */

package net.dries007.tfc.compat.jei.transfer;

import java.util.Optional;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.Nullable;

public abstract class BaseTransferHandler<C extends AbstractContainerMenu, R> implements IRecipeTransferHandler<C, R>
{
    private final Class<? extends C> containerClass;
    @Nullable private final MenuType<C> menuType;
    private final RecipeType<R> recipeType;

    protected BaseTransferHandler(Class<? extends C> containerClass, @Nullable MenuType<C> menuType, RecipeType<R> recipeType)
    {
        this.containerClass = containerClass;
        this.menuType = menuType;
        this.recipeType = recipeType;
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
}