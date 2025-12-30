package com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers;

import com.github.slepdepriv.thaumcraft5tweaker.helpers.InputHelper;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.ThaumcraftHelper;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.research.*;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.research.*;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IItemStack;
import minetweaker.api.player.IPlayer;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import thaumcraft.api.research.ResearchHelper;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage.PageType;

import static com.github.slepdepriv.thaumcraft5tweaker.helpers.InputHelper.toStack;

@ZenClass("mods.thaumcraft.Research")
public class Research {
	private static final ResourceLocation defaultBackground = new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png");

	@ZenMethod
	public static void addTab(String key, String researchKey, String iconDomain, String iconPath) {
		addTab(key,researchKey, iconDomain, iconPath, null, null);
	}

	@ZenMethod
	public static void addTab(String key,String researchKey, String iconDomain, String iconPath, String backDomain, String backPath) {
		ResourceLocation icon = new ResourceLocation(iconDomain, iconPath);
		ResourceLocation background;
		if (backPath == null)
			background = defaultBackground;
		else
			background = new ResourceLocation(backDomain, backPath);
		addTab(key, researchKey, icon, background);
	}

	private static void addTab(String key, String researchKey, ResourceLocation icon, ResourceLocation background) {
		
			MineTweakerAPI.apply(new AddTab(key, researchKey, icon, background));
	}

	@ZenMethod
	public static void removeTab(String tab) {

		
			MineTweakerAPI.apply(new RemoveTab(tab));
	}

	@ZenMethod
	public static void removeResearch(String research) {
		
			MineTweakerAPI.apply(new RemoveResearch(research));
	}

	@ZenMethod
	public static void orphanResearch(String research) {
		
			MineTweakerAPI.apply(new OrphanResearch(research));
	}

	@ZenMethod
	public static void addResearch(String key, String tab, @Optional String aspects, int x, int y, int difficulty, String domain, String path) {
		
			MineTweakerAPI.apply(new AddResearch(new ResearchItem(key, tab, ThaumcraftHelper.parseAspects(aspects), x, y, difficulty, new ResourceLocation(domain, path)), null, null));
	}

	@ZenMethod
	public static void addResearch(String key, String tab, @Optional String aspects, int x, int y, int difficulty, IItemStack item) {
		
			MineTweakerAPI.apply(new AddResearch(new ResearchItem(key, tab, ThaumcraftHelper.parseAspects(aspects), x, y, difficulty, toStack(item)),null, null));
	}
	
	@ZenMethod
    public static void addResearch(String key, String tab, @Optional String aspects, int x, int y, int difficulty, IItemStack item, IItemStack[] triggers, String[] entTriggers) {
	
        MineTweakerAPI.apply(new AddResearch(new ResearchItem(key, tab, ThaumcraftHelper.parseAspects(aspects), x, y, difficulty, toStack(item)), InputHelper.toStacks(triggers), entTriggers));
    }

	@ZenMethod
	public static void addPage(String key, String unlocalized) {
		
			MineTweakerAPI.apply(new AddPage(key, PageType.TEXT, unlocalized));
	}

	@ZenMethod
	public static void addCraftingPage(String key, IItemStack item) {
		
			MineTweakerAPI.apply(new AddPage(key, PageType.NORMAL_CRAFTING, toStack(item)));
	}

	@ZenMethod
	public static void addCruciblePage(String key, IItemStack item) {
		
			MineTweakerAPI.apply(new AddPage(key, PageType.CRUCIBLE_CRAFTING, toStack(item)));
	}

	@ZenMethod
	public static void addArcanePage(String key, IItemStack item) {
		
			MineTweakerAPI.apply(new AddPage(key, PageType.ARCANE_CRAFTING, toStack(item)));
	}

	@ZenMethod
	public static void addInfusionPage(String key, IItemStack item) {
		
			MineTweakerAPI.apply(new AddPage(key, PageType.INFUSION_CRAFTING, toStack(item)));
	}

	@ZenMethod
	public static void clearPages(String key) {
		
			MineTweakerAPI.apply(new ClearPages(key));
	}

	@ZenMethod
	public static void addPrereq(String key, String req, @Optional boolean hidden) {
		
			MineTweakerAPI.apply(new AddPrereq(key, req, hidden));
	}

	@ZenMethod
	public static void clearPrereqs(String key) {
		
			MineTweakerAPI.apply(new ClearPrereqs(key));
	}

	@ZenMethod
	public static void addSibling(String key, String sibling) {
		
			MineTweakerAPI.apply(new AddSibling(key, sibling));
	}

	@ZenMethod
	public static void clearSiblings(String key) {
		
			MineTweakerAPI.apply(new ClearSiblings(key));
	}

	@ZenMethod
	public static void setRound(String key, boolean flag) {
		
			MineTweakerAPI.apply(new SetResearch(key, flag, SetType.ROUND));
	}

	@ZenMethod
	public static void setSpikey(String key, boolean flag) {
		
			MineTweakerAPI.apply(new SetResearch(key, flag, SetType.SPIKE));
	}

	@ZenMethod
	public static void setStub(String key, boolean flag) {
		
			MineTweakerAPI.apply(new SetResearch(key, flag, SetType.STUB));
	}

	@ZenMethod
	public static void setSecondary(String key, boolean flag) {
		
			MineTweakerAPI.apply(new SetResearch(key, flag, SetType.SECONDARY));
	}

	@ZenMethod
	public static void setVirtual(String key, boolean flag) {
		
			MineTweakerAPI.apply(new SetResearch(key, flag, SetType.VIRTUAL));
	}

	@ZenMethod
	public static void setAutoUnlock(String key, boolean flag) {
		
			MineTweakerAPI.apply(new SetResearch(key, flag, SetType.AUTO));
	}

	@ZenMethod
	public static void setConcealed(String key, boolean flag) {
		
			MineTweakerAPI.apply(new SetResearch(key, flag, SetType.CONCEAL));
	}

	@ZenMethod
	public static void setAspects(String key, String aspects) {
		
			MineTweakerAPI.apply(new SetAspects(key, ThaumcraftHelper.parseAspects(aspects)));
	}

	@ZenMethod
	public static void setComplexity(String key, int complexity) {
		
			MineTweakerAPI.apply(new Difficulty(key, complexity));
	}

	@ZenMethod
	public static void refreshResearchRecipe(String key) {
		
			MineTweakerAPI.apply(new RefreshResearch(key));
	}

	@ZenMethod
	public static void moveResearch(String key, String destination, int x, int y) {
		
			MineTweakerAPI.apply(new MoveResearch(key, destination, x, y));
	}

	@ZenMethod
	public static boolean hasResearched(IPlayer player, String key) {
		return ResearchHelper.isResearchComplete(player.getName(), key);
	}

	public static enum SetType {
		AUTO, ROUND, SPIKE, SECONDARY, STUB, VIRTUAL, CONCEAL
	}
}
