package com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.research;

import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.ThaumcraftHelper;
import minetweaker.IUndoableAction;
import thaumcraft.api.research.ResearchCategories;

public class ClearSiblings implements IUndoableAction {
    String key;
    String tab;
    String[] siblings;

    public ClearSiblings(String res) {
        key = res;
        tab = ThaumcraftHelper.getResearchTab(key);
    }

    @Override
    public void apply() {
        siblings = ResearchCategories.researchCategories.get(tab).research.get(key).siblings;
        ResearchCategories.researchCategories.get(tab).research.get(key).setSiblings(new String[0]);
    }

    @Override
    public String describe() {
        return "Clearing Siblings for " + key;
    }

    @Override
    public boolean canUndo() {
        return siblings != null;
    }

    @Override
    public void undo() {
        ResearchCategories.researchCategories.get(tab).research.get(key).setSiblings(siblings);
    }

    @Override
    public String describeUndo() {
        return "Restoring Siblings for " + key;
    }

    @Override
    public String getOverrideKey() {
        return null;
    }

}