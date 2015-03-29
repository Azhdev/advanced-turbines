package nl.Azhdev.adtu.core.TileEntities;

import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityEnd;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityHousing;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityTurbineBasic;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityTurbineTiny;
import cpw.mods.fml.common.registry.GameRegistry;

public class adtuTileEntities {
	public static void init(){
		GameRegistry.registerTileEntity(TileEntityEnd.class, "adtuEnd");
		GameRegistry.registerTileEntity(TileEntityHousing.class, "Housing");
		GameRegistry.registerTileEntity(TileEntityTurbineBasic.class, "basicTurbine");
		GameRegistry.registerTileEntity(TileEntityTurbineTiny.class, "TileTurbineTiny");
	}
}
