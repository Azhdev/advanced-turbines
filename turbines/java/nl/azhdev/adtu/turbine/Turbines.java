package nl.azhdev.adtu.turbine;

import nl.azhdev.adtu.turbine.types.TurbineUnknown;
import nl.azhdev.adtu.turbine.types.lavalTurbine;
import nl.azhdev.adtu.turbine.types.tinyBabyTurbine;

public class Turbines {
	public static Turbine laval;
	public static Turbine tiny;
	public static Turbine unknown;
	
	public static void initTurbines(){
		laval = new lavalTurbine();
		tiny = new tinyBabyTurbine();
		unknown = new TurbineUnknown();
	}
}
