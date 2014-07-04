package nl.azhdev.adtu.core.TileEntities.custom;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityHousing extends TileEntity{
	
	private static boolean isPartOfMultiBlock;

	public boolean isPartOfMultiBlock() {
		return isPartOfMultiBlock;
	}

	public void setPartOfMultiBlock(boolean isPartOfMultiBlock) {
		TileEntityHousing.isPartOfMultiBlock = isPartOfMultiBlock;
	}
	
	@Override
	public void updateEntity(){
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound comp){
		super.writeToNBT(comp);
		comp.setBoolean("MultiBlock", isPartOfMultiBlock);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound comp){
		isPartOfMultiBlock = comp.getBoolean("MultiBlock");
	}
}
