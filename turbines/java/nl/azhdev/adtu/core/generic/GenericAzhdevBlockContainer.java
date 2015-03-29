package nl.Azhdev.adtu.core.generic;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import nl.Azhdev.adtu.core.util.CreativeTabAT;

public class GenericAzhdevBlockContainer extends BlockContainer{
	
	public GenericAzhdevBlockContainer(Material mat) {
		super(mat);
		setBlockName("adtu_" + getClass().getSimpleName() + "_AzhdevBlock");
		setBlockTextureName("adtu:" + getClass().getSimpleName());
		setCreativeTab(CreativeTabAT.creativeTabAT);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return null;
	}
}
