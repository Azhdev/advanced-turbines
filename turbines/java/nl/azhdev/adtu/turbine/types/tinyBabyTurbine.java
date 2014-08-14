package nl.azhdev.adtu.turbine.types;

import nl.azhdev.adtu.core.blocks.adtuBlocks;
import nl.azhdev.adtu.turbine.Turbine;

public class tinyBabyTurbine extends Turbine{
	public tinyBabyTurbine(){
		setTurbineName("Tiny Baby Turbine");
		setMaxRFOutput(10);
		setMinSteamInput(5);
		setMaxSteamInput(10);
		setMasterBlock(adtuBlocks.tinyTurbine);
	}
}
