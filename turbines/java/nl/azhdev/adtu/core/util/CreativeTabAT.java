package nl.azhdev.adtu.core.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import nl.azhdev.adtu.core.items.adtuItems;

public class CreativeTabAT {
	public static CreativeTabs creativeTabAT;
	
	public static void registerCreativeTabs(){
		creativeTabAT = new CreativeTabs("Advanced Turbines"){

			@Override
			public Item getTabIconItem() {
				return adtuItems.Wrench;
			}
			
		};
	}
}
