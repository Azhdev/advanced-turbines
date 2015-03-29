package nl.Azhdev.adtu.core.blocks.custom;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityTurbineBasic;
import nl.Azhdev.adtu.core.generic.GenericAzhdevBlockContainer;

public class lavalTurbineBlock extends turbineBlock {

	public lavalTurbineBlock(Material mat) {
		super(mat);
	}
	
	@Override
	public  TileEntity createNewTileEntity(World var1, int var2){
		return new TileEntityTurbineBasic();
	}
}
