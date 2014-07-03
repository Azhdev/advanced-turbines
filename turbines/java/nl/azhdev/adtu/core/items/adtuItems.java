package nl.azhdev.adtu.core.items;

import net.minecraft.item.Item;
import nl.azhdev.adtu.core.items.custom.itemWheel;
import nl.azhdev.adtu.core.items.custom.itemWrench;
import cpw.mods.fml.common.registry.GameRegistry;

public class adtuItems {
	public static Item Wheel;
	public static Item Wrench;
	
	public static void init(){
		Wheel = new itemWheel();
		GameRegistry.registerItem(Wheel, "adtuWheel");
		
		Wrench = new itemWrench();
		GameRegistry.registerItem(Wrench, "Wrench");
	}
}
