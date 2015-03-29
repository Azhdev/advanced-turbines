package nl.Azhdev.adtu.core.generic;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import nl.Azhdev.adtu.core.util.CreativeTabAT;

public class GenericAzhdevBlock extends Block{

	public GenericAzhdevBlock(Material mat) {
		super(mat);
		setBlockName("adtu_" + getClass().getSimpleName() + "_AzhdevBlock");
		setBlockTextureName("adtu:" + getClass().getSimpleName());
		setCreativeTab(CreativeTabAT.creativeTabAT);
	}

}
