package nl.azhdev.adtu.core.client.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityTurbine;

public class ContainerTurbine extends Container {

	private TileEntityTurbine turbine;
	
	public ContainerTurbine(InventoryPlayer playerInv, TileEntityTurbine tur){
		this.turbine = tur;
		addVanillaSlots(playerInv);
		addSlotToContainer(new Slot(turbine, 0, 80, 29));
	}
	
	private void addVanillaSlots(InventoryPlayer playerInv){
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(playerInv, x, 8 + 18 * x, 194));
		}
		
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + 18 * x, 136 + y * 18));
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

}
