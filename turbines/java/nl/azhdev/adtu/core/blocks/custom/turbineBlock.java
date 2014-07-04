package nl.azhdev.adtu.core.blocks.custom;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.azhdev.adtu.adtu;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityTurbine;
import nl.azhdev.adtu.core.generic.GenericAzhdevBlock;
import nl.azhdev.adtu.core.generic.GenericAzhdevBlockContainer;
import nl.azhdev.adtu.core.items.adtuItems;

public class turbineBlock extends GenericAzhdevBlockContainer{
	
	public TileEntityTurbine turbine;
	
	public turbineBlock(Material mat) {
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityTurbine();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int j, float p_149727_7_, float p_149727_8_, float p_149727_9_){
		turbine = (TileEntityTurbine)world.getTileEntity(x, y, z);
		if(!world.isRemote){
			FMLNetworkHandler.openGui(player, adtu.instance, 0, world, x, y, z);
		}
		return true;
		/**
		if(!world.isRemote){
			FMLNetworkHandler.openGui(player, adtu.instance, 0, world, x, y, z);
			if(turbine.isMultiBlock){
				
				return true;
			}else{
				if(!world.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == adtuItems.Wrench && !turbine.isMultiBlock){
					turbine.tryFormMultiBlock(world, x, y, z);
					return true;
				}else{
					return false;
				}
			}
		}else{
			return false;
		}
		*/
	}
}
