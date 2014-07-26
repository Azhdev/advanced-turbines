package nl.azhdev.adtu.core.TileEntities.custom;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import nl.azhdev.adtu.core.generic.AzhdevTileEntity;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

public class TileEntityEnd extends AzhdevTileEntity implements IEnergyHandler, IEnergySource{

	private int ic2EnergyStored;
	private int energyToAdd;
	private int RFOutputATM;
	
	public boolean isPartOfMB = false;
	private TileEntityTurbine parent;
	
	private EnergyStorage buffer;
	
	public TileEntityEnd(){
		buffer = new EnergyStorage(1000, 200);
	}
	
	@Override
	public void updateEntity(){
		if(!worldObj.isRemote){
			if(isPartOfMB){
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

//---------------------------------
//IC2
	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return true;
	}

	@Override
	public double getOfferedEnergy() {
		if(worldObj.isRemote){
			if(ic2EnergyStored <= energyToAdd){
				return energyToAdd;
			}else{
				return Math.min(ic2EnergyStored, 1028);
			}
		}else{
			return Math.min(ic2EnergyStored, 1028);
		}
	}
	
	@Override
	public void drawEnergy(double amount) {
		ic2EnergyStored -= amount;
		if(ic2EnergyStored < 0){
			ic2EnergyStored = 0;
		}
	}
	
	@Override
	public void firstTick() {
		MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
	}
	
	@Override
    public void invalidate(){
        if(worldObj != null && !worldObj.isRemote) {
            MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        }
        super.invalidate();
    }
	
	@Override
	public void onChunkUnload(){
		if(worldObj != null && !worldObj.isRemote) {
			MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
	    }
	    super.onChunkUnload();
	}
//----------------------------------------
//utility methods
	public void setPartOfMultiBlock(boolean part) {
		isPartOfMB = part;
	}
	
	public boolean getPartOFMultiBlock(){
		return isPartOfMB;
	}
	
	public void SetParent(TileEntityTurbine turbine){
		this.parent = turbine;
	}
	
	public TileEntityTurbine getParent(){
		return parent;
	}
	
	public int getPowerToOutput(){
		return parent.getPowerToOutput();
	}
}
