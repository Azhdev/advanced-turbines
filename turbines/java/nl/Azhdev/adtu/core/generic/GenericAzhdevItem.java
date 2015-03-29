package nl.Azhdev.adtu.core.generic;

import net.minecraft.item.Item;
import nl.Azhdev.adtu.core.util.CreativeTabAT;

public class GenericAzhdevItem extends Item{
	public GenericAzhdevItem(){
		super();
		setUnlocalizedName("adtu_" + getClass().getSimpleName());
		setTextureName("adtu:" + getClass().getSimpleName());
		setCreativeTab(CreativeTabAT.creativeTabAT);
	}
}
