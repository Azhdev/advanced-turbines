package nl.azhdev.adtu.turbine;

import nl.azhdev.adtu.turbine.types.lavalTurbine;

public class Turbines {
	public static Turbine laval;
	
	public static void initTurbines(){
		laval = new lavalTurbine();
	}
}
