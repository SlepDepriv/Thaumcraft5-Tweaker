package com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers;

import static com.github.slepdepriv.thaumcraft5tweaker.helpers.InputHelper.toIItemStack;
import static com.github.slepdepriv.thaumcraft5tweaker.helpers.InputHelper.toObject;
import static com.github.slepdepriv.thaumcraft5tweaker.helpers.InputHelper.toStack;
import static com.github.slepdepriv.thaumcraft5tweaker.helpers.StackHelper.matches;

import java.util.LinkedList;
import java.util.List;

import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.ThaumcraftHelper;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import com.github.slepdepriv.thaumcraft5tweaker.helpers.LogHelper;
import com.github.slepdepriv.thaumcraft5tweaker.utils.BaseListAddition;
import com.github.slepdepriv.thaumcraft5tweaker.utils.BaseListRemoval;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.CrucibleRecipe;

@ZenClass("mods.thaumcraft.Crucible")
public class Crucible {
    
    public static final String name = "Thaumcraft Crucible";
    
	@ZenMethod
	public static void addRecipe(String key[], IItemStack result, IIngredient catalyst, String aspects) {
	    MineTweakerAPI.apply(new Add(new CrucibleRecipe(key, toStack(result), toObject(catalyst), ThaumcraftHelper.parseAspects(aspects))));
	}
    
	private static class Add extends BaseListAddition<CrucibleRecipe> {
		public Add(CrucibleRecipe recipe) {
			super(Crucible.name, ThaumcraftApi.getCraftingRecipes());
			recipes.add(recipe);
		}
		
		@Override
		protected String getRecipeInfo(CrucibleRecipe recipe) {
		    return LogHelper.getStackDescription(recipe.getRecipeOutput());
		}
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@ZenMethod
	public static void removeRecipe(IIngredient output) {
	    List<CrucibleRecipe> recipes = new LinkedList<CrucibleRecipe>();
	    
        for (Object o : ThaumcraftApi.getCraftingRecipes()) {
            if (o instanceof CrucibleRecipe) {
                CrucibleRecipe r = (CrucibleRecipe) o;
                if (r.getRecipeOutput() != null && matches(output, toIItemStack(r.getRecipeOutput()))) {
                    recipes.add(r);
                }
            }
        }
        
        if(!recipes.isEmpty()) {
			MineTweakerAPI.apply(new Remove(recipes));
        } else {
            LogHelper.logWarning(String.format("No %s Recipe found for %s. Command ignored!", Crucible.name, output.toString()));
        }
	}

	private static class Remove extends BaseListRemoval<CrucibleRecipe> {
		public Remove(List<CrucibleRecipe> recipes) {
			super(Crucible.name, ThaumcraftApi.getCraftingRecipes(), recipes);
		}

        @Override
        protected String getRecipeInfo(CrucibleRecipe recipe) {
            return LogHelper.getStackDescription(recipe.getRecipeOutput());
        }
	}
}
