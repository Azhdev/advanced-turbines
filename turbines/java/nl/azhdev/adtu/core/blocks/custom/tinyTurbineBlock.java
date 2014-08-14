package nl.azhdev.adtu.core.blocks.custom;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityTurbineBasic;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityTurbineTiny;
import nl.azhdev.adtu.core.generic.GenericAzhdevBlockContainer;

public class tinyTurbineBlock extends GenericAzhdevBlockContainer {

	public tinyTurbineBlock(Material mat) {
		super(mat);
		
	}

	@Override
	public  TileEntity createNewTileEntity(World var1, int var2){
		return new TileEntityTurbineTiny();
	}
}
