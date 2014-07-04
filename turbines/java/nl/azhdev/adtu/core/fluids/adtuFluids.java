package nl.azhdev.adtu.core.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class adtuFluids {
	public static Block adtuSteam;
	
	
	
	
	public static void initFluids(){
		Material materialSteam = new MaterialLiquid(MapColor.ironColor);
		Steam steam = new Steam();
		adtuSteam = new FluidAdtuSteam(steam, materialSteam);
		GameRegistry.registerBlock(adtuSteam, "adtuSteam");
		FluidRegistry.registerFluid(steam);
	}
}
