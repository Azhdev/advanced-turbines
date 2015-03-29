package nl.Azhdev.adtu;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import nl.Azhdev.adtu.core.TileEntities.adtuTileEntities;
import nl.Azhdev.adtu.core.blocks.adtuBlocks;
import nl.Azhdev.adtu.core.fluids.adtuFluids;
import nl.Azhdev.adtu.core.handlers.configHandler;
import nl.Azhdev.adtu.core.items.adtuItems;
import nl.Azhdev.adtu.core.proxy.commonProxy;
import nl.Azhdev.adtu.core.recipe.damageRecipeManager;
import nl.Azhdev.adtu.core.util.CreativeTabAT;
import nl.Azhdev.adtu.core.util.Log;
import nl.Azhdev.adtu.core.util.initiater;
import nl.Azhdev.adtu.lib.Reference;
import nl.Azhdev.adtu.turbine.Turbines;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * @author Azhdev
 *
 */

@Mod(modid = Reference.modid, name = Reference.modName, version = Reference.version)
public class adtu {
	
	@Instance(Reference.modid)
	public static adtu instance;
	
	@SidedProxy(clientSide = "nl.Azhdev.adtu.core.proxy.clientProxy", serverSide = "nl.Azhdev.adtu.core.proxy.commonProxy")
	public static commonProxy proxy;
	public static boolean partOfCore = true;
	public static initiater initiater;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Log.addInfo("Advanced Turbines started loading!");
		
		configHandler.init(event.getSuggestedConfigurationFile());
		CreativeTabAT.registerCreativeTabs();
		adtuFluids.initFluids();
		adtuBlocks.init();
		
		adtuItems.init();
		Turbines.initTurbines();
		proxy.initRendering();
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		FMLCommonHandler.instance().bus().register(new damageRecipeManager());
		adtuTileEntities.init();
		GameRegistry.addShapelessRecipe(new ItemStack(Items.arrow), new ItemStack(adtuItems.Wheel, 1, OreDictionary.WILDCARD_VALUE), Items.arrow);
		GameRegistry.addShapelessRecipe(new ItemStack(adtuItems.Wheel), Items.apple);
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
}
