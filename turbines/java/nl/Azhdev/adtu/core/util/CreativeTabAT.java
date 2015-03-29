package nl.Azhdev.adtu.core.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import nl.Azhdev.adtu.core.items.adtuItems;

public class CreativeTabAT {
	public static CreativeTabs creativeTabAT;
	
	public static void registerCreativeTabs(){
		creativeTabAT = new CreativeTabs("Advanced Turbines"){

			@Override
			public Item getTabIconItem() {
				return adtuItems.Wheel;
			}
			
		};
	}
}
