package nl.Azhdev.adtu.core.fluids;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class Steam extends Fluid {
	public Steam() {
		super("steam");

		setDensity(-20);
		setViscosity(500);
		setGaseous(true);
		setTemperature(390);
		setUnlocalizedName("adtu:steam");
		setBlock(adtuFluids.adtuSteam);
		FluidRegistry.registerFluid(this);

	}
}