package nl.azhdev.adtu.core.TileEntities.custom;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import nl.azhdev.adtu.core.generic.AzhdevTileEntity;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

public class TileEntityEnd extends TileEntityHousing implements IEnergyHandler{
		
	private EnergyStorage buffer;
	
	public TileEntityEnd(){
		buffer = new EnergyStorage(1000, 200);
	}
	
	@Override
	public void updateEntity(){
		if(!worldObj.isRemote){
			if(isPartOfMultiBlock()){
				for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
					TileEntity ent = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
					if(ent != null && ent instanceof IEnergyHandler){
						IEnergyHandler handler = (IEnergyHandler)ent;
						handler.receiveEnergy(dir.getOpposite(), 40, false);
						this.extractEnergy(dir, 40, false);
					}
				}
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound compound){
		super.writeToNBT(compound);
		buffer.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		buffer.readFromNBT(compound);
	}
	
//----------------------
//RF
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return buffer.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return buffer.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return buffer.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return buffer.getMaxEnergyStored();
	}
}
