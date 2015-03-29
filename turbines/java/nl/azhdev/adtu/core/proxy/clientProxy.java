package nl.Azhdev.adtu.core.proxy;

import nl.Azhdev.adtu.client.models.tinybabyturbine;
import nl.Azhdev.adtu.client.renderers.renderTinyBaby;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityTurbineTiny;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class clientProxy extends commonProxy{
	
	@Override
	public void initRendering(){
		tinybabyturbine tiny = new tinybabyturbine();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTurbineTiny.class, new renderTinyBaby(tiny));
	}
}
