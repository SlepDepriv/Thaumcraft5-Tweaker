package com.github.slepdepriv.thaumcraft5tweaker;

import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.Thaumcraft;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.aspect.AspectBracketHandler;
import com.github.slepdepriv.thaumcraft5tweaker.mods.thaumcraft.handlers.*;
import com.github.slepdepriv.thaumcraft5tweaker.utils.TweakerPlugin;
import minetweaker.MineTweakerAPI;
import minetweaker.MineTweakerImplementationAPI;
import minetweaker.runtime.providers.ScriptProviderDirectory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = "thaumcrafttweaker", useMetadata=true)
public class Thaumcraft5Tweaker {

    public static String modid = "thaumcrafttweaker";
    public static Logger logger = LogManager.getLogger(modid);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        logger.info("Starting Initialization for " + modid);
        MineTweakerAPI.registerBracketHandler(new AspectBracketHandler());
        MineTweakerAPI.registerClass(Arcane.class);
        MineTweakerAPI.registerClass(Aspects.class);
        MineTweakerAPI.registerClass(Crucible.class);
        MineTweakerAPI.registerClass(Infusion.class);
        MineTweakerAPI.registerClass(Research.class);
        MineTweakerAPI.registerClass(Warp.class);
        MineTweakerAPI.registerClass(Loot.class);

        File scripts = new File("scripts");
        if (!scripts.exists()) {
            scripts.mkdir();
        }
        MineTweakerImplementationAPI.setScriptProvider(new ScriptProviderDirectory(scripts));
    }
}
