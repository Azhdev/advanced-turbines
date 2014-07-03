package nl.azhdev.adtu.core.TileEntities;

import nl.azhdev.adtu.core.TileEntities.custom.TileEntityEnd;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityTurbine;
import cpw.mods.fml.common.registry.GameRegistry;

public class adtuTileEntities {
	public static void init(){
		GameRegistry.registerTileEntity(TileEntityTurbine.class, "adtuturbine");
		GameRegistry.registerTileEntity(TileEntityEnd.class, "adtuEnd");
	}
}
