package com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers;

import java.util.LinkedList;
import java.util.List;

import com.github.slepdepriv.thaumcraft5tweaker.helpers.LogHelper;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.ThaumcraftHelper;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.recipe.MTInfusionRecipe;
import com.github.slepdepriv.thaumcraft5tweaker.utils.BaseListAddition;
import com.github.slepdepriv.thaumcraft5tweaker.utils.BaseListRemoval;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.InfusionRecipe;

import static com.github.slepdepriv.thaumcraft5tweaker.helpers.InputHelper.*;
import static com.github.slepdepriv.thaumcraft5tweaker.helpers.StackHelper.matches;

@ZenClass("mods.thaumcraft.Infusion")
public class Infusion {

    public static final String name = "Thaumcraft Infusion";

	@ZenMethod
	public static void addRecipe(String key, IItemStack input, IItemStack[] recipe, String aspects, IItemStack result, int instability) {
		
			MineTweakerAPI.apply(new Add(new InfusionRecipe(key, toStack(result), instability, ThaumcraftHelper.parseAspects(aspects), toStack(input), toStacks(recipe))));
	}

	// A version that allows you to specify whether the detection should be
	// fuzzy or not
	@ZenMethod
	public static void addRecipe(String key, IItemStack input, IItemStack[] recipe, String aspects, IItemStack result, int instability, boolean fuzzyCentre, boolean[] fuzzyRecipe) {
	    MineTweakerAPI.apply(new Add(new MTInfusionRecipe(key, toStack(result), instability, ThaumcraftHelper.parseAspects(aspects), toStack(input), toStacks(recipe), fuzzyCentre, fuzzyRecipe)));
	}

	private static class Add extends BaseListAddition<InfusionRecipe> {
		public Add(InfusionRecipe recipe) {
			super(Infusion.name, ThaumcraftApi.getCraftingRecipes());
			recipes.add(recipe);
		}

		@Override
		protected String getRecipeInfo(InfusionRecipe recipe) {
            Object out = ((InfusionRecipe) recipe).getRecipeOutput();
            if (out instanceof ItemStack) {
                return ((ItemStack) out).getDisplayName();
            } else
                return "Unknown item";
		}
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@ZenMethod
	public static void removeRecipe(IIngredient output) {
	    List<InfusionRecipe> recipes = new LinkedList<InfusionRecipe>();

	    for (Object o : ThaumcraftApi.getCraftingRecipes()) {
            if (o instanceof InfusionRecipe) {
                InfusionRecipe r = (InfusionRecipe) o;
                if (r.getRecipeOutput() != null && r.getRecipeOutput() instanceof ItemStack && matches(output, toIItemStack((ItemStack)r.getRecipeOutput()))) {
                    recipes.add(r);
                }
            }
        }
        
	    if(!recipes.isEmpty()) {
	        MineTweakerAPI.apply(new Remove(recipes));
	    } else {
	        LogHelper.logWarning(String.format("No %s Recipe found for %s. Command ignored!", Infusion.name, output.toString()));
	    }
	}


	private static class Remove extends BaseListRemoval<InfusionRecipe> {
		public Remove(List<InfusionRecipe> recipes) {
			super(Infusion.name, ThaumcraftApi.getCraftingRecipes(), recipes);
		}

		@Override
		protected String getRecipeInfo(InfusionRecipe recipe) {
		    
		    Object o = recipe.getRecipeOutput();
		    
		    if(o instanceof ItemStack) {
		        return LogHelper.getStackDescription((ItemStack)o);    
		    } else {
		        return "Unknown Item";
		    }
		}
	}
}

