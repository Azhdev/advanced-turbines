package nl.azhdev.adtu.core.fluids;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class FluidAdtuSteam extends BlockFluidFinite{

	public FluidAdtuSteam(Fluid fluid, Material material) {
		super(fluid, material);
		setBlockName("adtuSteam");
		setCreativeTab(CreativeTabs.tabBlock);
	}

}
