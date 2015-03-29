package nl.Azhdev.adtu.core.blocks.custom;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import nl.Azhdev.adtu.adtu;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityTurbineBasic;
import nl.Azhdev.adtu.core.blocks.adtuBlocks;
import nl.Azhdev.adtu.core.generic.GenericAzhdevBlockContainer;
import nl.Azhdev.adtu.turbine.Turbines;

public class turbineBlock extends GenericAzhdevBlockContainer{
	
	public turbineBlock(Material mat) {
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileEntityTurbineBasic();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int j, float p_149727_7_, float p_149727_8_, float p_149727_9_){
		if(!world.isRemote){
			if(player.getHeldItem() != null && player.getHeldItem().getItem() == Items.diamond){
				world.setBlock(x, y, z, adtuBlocks.lavalTurbine);
				TileEntityTurbineBasic tile = (TileEntityTurbineBasic)world.getTileEntity(x, y, z);
				tile.setType(Turbines.laval);
			}else if(player.getHeldItem() != null && player.getHeldItem().getItem() == Items.iron_ingot){
				world.setBlock(x, y, z, adtuBlocks.tinyTurbine);
				TileEntityTurbineBasic tile = (TileEntityTurbineBasic)world.getTileEntity(x, y, z);
				tile.setType(Turbines.tiny);
			}
		}
		if(player.isSneaking()){
			TileEntityTurbineBasic tile = (TileEntityTurbineBasic)world.getTileEntity(x, y, z);
			
			if(tile.getType() != null){
				String v = "" + tile.getType().getTurbineName();
				player.addChatComponentMessage(new ChatComponentText(v));
			}
		}
		return true;
	}
}
