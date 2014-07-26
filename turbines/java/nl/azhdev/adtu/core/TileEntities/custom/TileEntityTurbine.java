package nl.azhdev.adtu.core.TileEntities.custom;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import nl.azhdev.adtu.core.blocks.adtuBlocks;
import nl.azhdev.adtu.core.generic.AzhdevTileEntity;
import nl.azhdev.adtu.core.util.Log;
import nl.azhdev.adtu.turbine.Turbine;
import nl.azhdev.adtu.turbine.Turbines;

public class TileEntityTurbine extends AzhdevTileEntity implements ISidedInventory,IFluidHandler{
	
	//TODO	
	// EVERYTHING!!!
	
	private ItemStack[] items;
	private FluidTank tank;
	
	public TileEntityTurbine(){
		items = new ItemStack[1];
		tank = new FluidTank(1000);
	}
	
	@Override
	public void updateEntity(){
		
	}

	@Override
	public void writeToNBT(NBTTagCompound comp){
		super.writeToNBT(comp);
		tank.writeToNBT(comp);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound comp){
		super.readFromNBT(comp);
		tank.readFromNBT(comp);
		
	}
	
	@Override
	public int getSizeInventory() {
		return items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int count) {
		ItemStack itemstack = getStackInSlot(i);
		
		if (itemstack != null) {
			if (itemstack.stackSize <= count) {
				setInventorySlotContents(i, null);
			}else{
				itemstack = itemstack.splitStack(count);
				markDirty();
			}
		}

		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		ItemStack item = getStackInSlot(i);
		setInventorySlotContents(i, null);
		return item;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		items[i] = itemstack;
		
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
		}
		
		markDirty();
	}

	@Override
	public String getInventoryName() {
		return "inventoryTurbine";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}
//-----------------------------------------------------------------------------------
//ISided Stuff
	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int var1, ItemStack var2, int var3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int var1, ItemStack var2, int var3) {
		// TODO Auto-generated method stub
		return false;
	}	
//--------------------------------------------------
//fluids \/
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if(worldObj.isRemote) return 0;
		if(resource == null) //What!?
			return 0;
		if(resource.getFluid() == null)
			return 0;

		if(tank != null && tank.getFluid() != null && tank.getFluidAmount() > 0){
			if(resource.getFluid().getID() != tank.getFluid().getFluid().getID()){
				return 0;
			}
		}else if(tank == null){
			return 0;
		}

		int filled = tank.fill(resource, doFill);
		return filled;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource,
			boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		FluidStack drained = tank.drain(maxDrain, doDrain);
		if(tank.getFluidAmount() == 0 && doDrain){
			tank.setFluid(null);
		}
		return drained;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return fluid.getID() == FluidRegistry.getFluidID("steam");
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return fluid.getID() == FluidRegistry.getFluidID("steam");
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		if(tank != null){
			FluidTankInfo[] tankInfo = {new FluidTankInfo(tank)};
			return tankInfo;
		}else{
			return null;
		}
	}

	public int getPowerToOutput() {
		return tank.getCapacity();
	}

}
