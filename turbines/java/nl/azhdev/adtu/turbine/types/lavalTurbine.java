package nl.azhdev.adtu.turbine.types;

import nl.azhdev.adtu.core.blocks.adtuBlocks;
import nl.azhdev.adtu.turbine.Turbine;

public class lavalTurbine extends Turbine{

	public lavalTurbine() {
		super();
		setTurbineName("laval");
		setMaxRFOutput(80);
		setMinSteamInput(100);
		setMasterBlock(adtuBlocks.lavalTurbine);
	}

}
