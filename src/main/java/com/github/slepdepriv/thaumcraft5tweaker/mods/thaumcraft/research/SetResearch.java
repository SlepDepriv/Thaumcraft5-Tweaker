package com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.research;

import java.lang.reflect.Field;

import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.ThaumcraftHelper;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers.Research;
import minetweaker.IUndoableAction;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;

public class SetResearch implements IUndoableAction {
    String key;
    String tab;
    Research.SetType type;
    boolean flag;
    boolean applied = false;

    public SetResearch(String res, boolean f, Research.SetType typ) {
        key = res;
        tab = ThaumcraftHelper.getResearchTab(key);
        type = typ;
        flag = f;
    }

    @Override
    public void apply() {
        ResearchItem research = ResearchCategories.researchCategories.get(tab).research.get(key);
        if (flag) {
            if (type == Research.SetType.AUTO) research.setAutoUnlock();
            else if (type == Research.SetType.ROUND) research.setRound();
            else if (type == Research.SetType.SPIKE) research.setSpecial();
            else if (type == Research.SetType.SECONDARY) research.setSecondary();
            else if (type == Research.SetType.STUB) research.setStub();
//            else if (type == SetType.VIRTUAL) research.setsetVirtual();
//            else if (type == SetType.CONCEAL) research.setConcealed();
            applied = true;
        } else {
            try {
                Field target = null;
                if (type == Research.SetType.AUTO) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isAutoUnlock");
                else if (type == Research.SetType.ROUND) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isRound");
                else if (type == Research.SetType.SPIKE) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isSpecial");
                else if (type == Research.SetType.SECONDARY) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isSecondary");
                else if (type == Research.SetType.STUB) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isStub");
                else if (type == Research.SetType.VIRTUAL) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isVirtual");
                else if (type == Research.SetType.CONCEAL) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isConcealed");

                if (target != null) {
                    target.setAccessible(true);
                    target.setBoolean(research, false);
                    applied = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String describe() {
        return "Setting tag for " + key;
    }

    @Override
    public boolean canUndo() {
        return applied;
    }

    @Override
    public void undo() {
        ResearchItem research = ResearchCategories.researchCategories.get(tab).research.get(key);
        if (!flag) {
            if (type == Research.SetType.AUTO) research.setAutoUnlock();
            else if (type == Research.SetType.ROUND) research.setRound();
            else if (type == Research.SetType.SPIKE) research.setSpecial();
            else if (type == Research.SetType.SECONDARY) research.setSecondary();
            else if (type == Research.SetType.STUB) research.setStub();
//            else if (type == SetType.VIRTUAL) research.setVirtual();
//            else if (type == SetType.CONCEAL) research.setConcealed();
        } else {
            try {
                Field target = null;
                if (type == Research.SetType.AUTO) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isAutoUnlock");
                else if (type == Research.SetType.ROUND) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isRound");
                else if (type == Research.SetType.SPIKE) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isSpecial");
                else if (type == Research.SetType.SECONDARY) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isSecondary");
                else if (type == Research.SetType.STUB) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isStub");
                else if (type == Research.SetType.VIRTUAL) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isVirtual");
                else if (type == Research.SetType.CONCEAL) target = Class.forName("thaumcraft.api.research.ResearchItem").getDeclaredField("isConcealed");

                if (target != null) {
                    target.setAccessible(true);
                    target.setBoolean(research, false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String describeUndo() {
        return "Reversing tag for " + key;
    }

    @Override
    public String getOverrideKey() {
        return null;
    }

}