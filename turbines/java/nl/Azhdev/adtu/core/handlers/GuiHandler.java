package nl.Azhdev.adtu.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.Azhdev.adtu.client.containers.ContainerTurbine;
import nl.Azhdev.adtu.client.guis.GUITurbine;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityTurbineBasic;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID){
		case 0:
			TileEntity te = world.getTileEntity(x, y, z);
			if (te != null && te instanceof TileEntityTurbineBasic) {
				return new ContainerTurbine(player.inventory, (TileEntityTurbineBasic)te);
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
			if (te != null && te instanceof TileEntityTurbineBasic) {
				return new GUITurbine(player.inventory, (TileEntityTurbineBasic)te);
			}
			break;

		}
		return null;
	}

}
