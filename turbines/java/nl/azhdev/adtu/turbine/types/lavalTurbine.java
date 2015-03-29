package nl.Azhdev.adtu.turbine.types;

import nl.Azhdev.adtu.core.blocks.adtuBlocks;
import nl.Azhdev.adtu.turbine.Turbine;

public class lavalTurbine extends Turbine{

	public lavalTurbine() {
		super();
		setTurbineName("laval");
		setMaxRFOutput(80);
		setMinSteamInput(100);
		setMasterBlock(adtuBlocks.lavalTurbine);
	}

}
