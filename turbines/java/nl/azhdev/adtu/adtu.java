package nl.azhdev.adtu;

import nl.azhdev.adtu.core.TileEntities.adtuTileEntities;
import nl.azhdev.adtu.core.blocks.adtuBlocks;
import nl.azhdev.adtu.core.fluids.adtuFluids;
import nl.azhdev.adtu.core.handlers.configHandler;
import nl.azhdev.adtu.core.items.adtuItems;
import nl.azhdev.adtu.core.proxy.commonProxy;
import nl.azhdev.adtu.core.util.CreativeTabAT;
import nl.azhdev.adtu.core.util.Log;
import nl.azhdev.adtu.core.util.initiater;
import nl.azhdev.adtu.lib.Reference;
import nl.azhdev.adtu.turbine.Turbines;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * 
 * @author Azhdev
 *
 */

@Mod(modid = Reference.modid, name = Reference.modName, version = Reference.version, dependencies = Reference.dependencies)
public class adtu {
	
	@Instance(Reference.modid)
	public static adtu instance;
	
	@SidedProxy(clientSide = "nl.azhdev.adtu.core.proxy.clientProxy", serverSide = "nl.azhdev.adtu.core.proxy.commonProxy")
	public static commonProxy proxy;
	
	public static initiater initiater;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Log.addInfo("Advanced Turbines started loading!");
		
		configHandler.init(event.getSuggestedConfigurationFile());
		CreativeTabAT.registerCreativeTabs();
		adtuFluids.initFluids();
		adtuBlocks.init();
		adtuTileEntities.init();
		adtuItems.init();
		Turbines.initTurbines();
		proxy.initRendering();
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
}
