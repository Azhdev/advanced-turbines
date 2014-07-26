package nl.azhdev.adtu.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityTurbine;
import nl.azhdev.adtu.core.client.containers.ContainerTurbine;
import nl.azhdev.adtu.core.client.guis.GUITurbine;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case 0:
			TileEntity te = world.getTileEntity(x, y, z);
			if (te != null && te instanceof TileEntityTurbine) {
				return new ContainerTurbine(player.inventory, (TileEntityTurbine)te);
			}
			break;

		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case 0:
			TileEntity te = world.getTileEntity(x, y, z);
			if (te != null && te instanceof TileEntityTurbine) {
				return new GUITurbine(player.inventory, (TileEntityTurbine)te);
			}
			break;

		}
		return null;
	}

}
