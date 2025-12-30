package com.github.slepdepriv.thaumcraft5tweaker.utils;


import java.util.LinkedList;
import java.util.List;

import com.github.slepdepriv.thaumcraft5tweaker.helpers.InputHelper;
import com.github.slepdepriv.thaumcraft5tweaker.helpers.LogHelper;
import com.github.slepdepriv.thaumcraft5tweaker.helpers.StackHelper;
import minetweaker.api.item.IIngredient;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class BaseCraftingRemoval extends BaseListRemoval<IRecipe> {
	public BaseCraftingRemoval(String name, List<IRecipe> list, List<IRecipe> recipes) {
		super(name, list, recipes);
	}
	
    @Override
    public String getRecipeInfo(IRecipe recipe) {
        return LogHelper.getStackDescription(recipe.getRecipeOutput());
    }
    
    public static List<IRecipe> getRecipes(List<IRecipe> list, IIngredient ingredient) {
        List<IRecipe> recipes = new LinkedList<IRecipe>();
        
        for (IRecipe r : list) {
            if (r != null && r.getRecipeOutput() != null && r.getRecipeOutput() instanceof ItemStack && StackHelper.matches(ingredient, InputHelper.toIItemStack(r.getRecipeOutput()))) {
                recipes.add(r);
            }
        }
        
        return recipes;
    }
}