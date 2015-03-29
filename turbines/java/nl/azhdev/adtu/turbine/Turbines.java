package nl.Azhdev.adtu.turbine;

import nl.Azhdev.adtu.turbine.types.TurbineUnknown;
import nl.Azhdev.adtu.turbine.types.curtisTurbine;
import nl.Azhdev.adtu.turbine.types.lavalTurbine;
import nl.Azhdev.adtu.turbine.types.parssonsTurbine;
import nl.Azhdev.adtu.turbine.types.tinyBabyTurbine;
import nl.Azhdev.adtu.turbine.types.zoellyTurbine;

public class Turbines {
	public static Turbine laval;
	public static Turbine tiny;
	public static Turbine zoelly;
	public static Turbine curtis;
	public static Turbine parssons;
	public static Turbine unknown;
	
	public static void initTurbines(){
		laval = new lavalTurbine();
		tiny = new tinyBabyTurbine();
		zoelly = new zoellyTurbine();
		curtis = new curtisTurbine();
		parssons = new parssonsTurbine();
		unknown = new TurbineUnknown();
	}
}
