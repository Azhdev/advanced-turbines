package nl.Azhdev.adtu.core.items;

import net.minecraft.item.Item;
import nl.Azhdev.adtu.core.items.custom.itemWheel;
import cpw.mods.fml.common.registry.GameRegistry;

public class adtuItems {
	public static Item Wheel;
	
	public static void init(){
		Wheel = new itemWheel();
		GameRegistry.registerItem(Wheel, "adtuWheel");
	}
}
