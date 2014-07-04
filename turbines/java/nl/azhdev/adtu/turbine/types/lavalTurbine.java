package nl.azhdev.adtu.turbine.types;

import nl.azhdev.adtu.turbine.Turbine;

public class lavalTurbine extends Turbine{

	public lavalTurbine() {
		super();
		setTurbineName("laval");
		setMaxEUOutput(32);
		setMinSteamInput(100);
	}

}
