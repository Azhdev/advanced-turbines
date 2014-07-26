package nl.azhdev.adtu.core.blocks.custom;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityEnd;
import nl.azhdev.adtu.core.generic.GenericAzhdevBlock;

public class End extends GenericAzhdevBlock implements ITileEntityProvider{

	public End(Material mat) {
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityEnd();
	}	
}
