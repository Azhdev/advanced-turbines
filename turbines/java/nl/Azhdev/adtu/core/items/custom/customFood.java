package nl.Azhdev.adtu.core.items.custom;

import net.minecraft.item.ItemFood;

public class customFood extends ItemFood{

	public customFood(int healamount, boolean isWolfFood, String name) {
		super(healamount, isWolfFood);
		setUnlocalizedName(name);
		setTextureName(name);
	}

}
