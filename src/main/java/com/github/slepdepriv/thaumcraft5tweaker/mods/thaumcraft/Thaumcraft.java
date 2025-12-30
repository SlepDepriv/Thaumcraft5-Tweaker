package com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft;

import minetweaker.MineTweakerAPI;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.aspect.AspectBracketHandler;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers.Arcane;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers.Aspects;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers.Crucible;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers.Infusion;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers.Loot;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers.Research;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers.Warp;

public class Thaumcraft {
	public Thaumcraft() {
	    MineTweakerAPI.registerBracketHandler(new AspectBracketHandler());
		MineTweakerAPI.registerClass(Arcane.class);
		MineTweakerAPI.registerClass(Aspects.class);
		MineTweakerAPI.registerClass(Crucible.class);
		MineTweakerAPI.registerClass(Infusion.class);
		MineTweakerAPI.registerClass(Research.class);
		MineTweakerAPI.registerClass(Warp.class);
		MineTweakerAPI.registerClass(Loot.class);
	}
}
