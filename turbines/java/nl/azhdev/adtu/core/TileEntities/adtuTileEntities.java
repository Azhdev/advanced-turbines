package nl.azhdev.adtu.core.TileEntities;

import nl.azhdev.adtu.core.TileEntities.custom.TileEntityEnd;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityHousing;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityTurbine;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityTurbineBasic;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityTurbineTiny;
import cpw.mods.fml.common.registry.GameRegistry;

public class adtuTileEntities {
	public static void init(){
		GameRegistry.registerTileEntity(TileEntityTurbine.class, "adtuturbine");
		GameRegistry.registerTileEntity(TileEntityEnd.class, "adtuEnd");
		GameRegistry.registerTileEntity(TileEntityHousing.class, "Housing");
		GameRegistry.registerTileEntity(TileEntityTurbineBasic.class, "basicTurbine");
		GameRegistry.registerTileEntity(TileEntityTurbineTiny.class, "TileTurbineTiny");
	}
}
