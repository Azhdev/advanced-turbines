package nl.Azhdev.adtu.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import nl.Azhdev.adtu.core.blocks.custom.End;
import nl.Azhdev.adtu.core.blocks.custom.TurbineHousing;
import nl.Azhdev.adtu.core.blocks.custom.lavalTurbineBlock;
import nl.Azhdev.adtu.core.blocks.custom.tinyTurbineBlock;
import nl.Azhdev.adtu.core.blocks.custom.turbineBlock;
import cpw.mods.fml.common.registry.GameRegistry;

public class adtuBlocks {
	public static Block turbineBlock;
	public static Block End;
	public static Block TurbineHousing;
	public static Block lavalTurbine;
	public static Block tinyTurbine;
	
	public static void init(){
		turbineBlock = new turbineBlock(Material.iron);
		GameRegistry.registerBlock(turbineBlock, "adtuTurbine");
		
		End = new End(Material.iron);
		GameRegistry.registerBlock(End, "adtuend");
		
		TurbineHousing = new TurbineHousing(Material.iron);
		GameRegistry.registerBlock(TurbineHousing, "adtu_turbinehousing");
		
		lavalTurbine = new lavalTurbineBlock(Material.iron);
		GameRegistry.registerBlock(lavalTurbine, "lavalTurbineBlock");
		
		tinyTurbine = new tinyTurbineBlock(Material.iron);
		GameRegistry.registerBlock(tinyTurbine, "tinyTurbineBlock");
	}
}
