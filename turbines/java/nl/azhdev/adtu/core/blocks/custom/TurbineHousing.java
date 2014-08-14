package nl.azhdev.adtu.core.blocks.custom;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityHousing;
import nl.azhdev.adtu.core.generic.GenericAzhdevBlockContainer;

public class TurbineHousing extends GenericAzhdevBlockContainer {

	public TurbineHousing(Material mat) {
		super(mat);
	}

	
	@Override
	public TileEntity createNewTileEntity(World world, int var2){
		return new TileEntityHousing();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int j, float p_149727_7_, float p_149727_8_, float p_149727_9_){
		if(!world.isRemote){
			TileEntityHousing housing = (TileEntityHousing) world.getTileEntity(x, y, z);
			System.out.println(housing.isPartOfMultiBlock());
		}
		return true;
	}
}
