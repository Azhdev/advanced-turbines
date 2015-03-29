package nl.Azhdev.adtu.core.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import nl.Azhdev.adtu.core.items.adtuItems;
import nl.Azhdev.adtu.core.items.custom.itemWheel;
import nl.Azhdev.adtu.core.util.Log;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;
/**
 * 
 * @author Azhdev
 *
 * damageRecipeManager.java created at 15:23:13 27 jan. 2015
 *
 */
public class damageRecipeManager {
	
	@SubscribeEvent
	 public void onCrafting(ItemCraftedEvent event) {
	    
		 final IInventory craftMatrix = null;
		 for(int i = 0; i < event.craftMatrix.getSizeInventory(); i++){
			 ItemStack item0 = event.craftMatrix.getStackInSlot(i);
			 if(item0 != null && item0.getItem() instanceof itemWheel){
				 ItemStack item1 = new ItemStack(adtuItems.Wheel, 2, item0.getItemDamage() + 1);
					 
				 if(item1.getItemDamage() >= item1.getMaxDamage()){
					 item1.stackSize--;
				 }
				 event.craftMatrix.setInventorySlotContents(i, item1);
			 }
		 }
	 }
}
