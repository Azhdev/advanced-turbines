package nl.azhdev.adtu.core.TileEntities.custom;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySource;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import nl.azhdev.adtu.core.blocks.adtuBlocks;
import nl.azhdev.adtu.core.fluids.Steam;
import nl.azhdev.adtu.core.util.Log;
import nl.azhdev.adtu.turbine.Turbine;
import nl.azhdev.adtu.turbine.Turbines;

import com.cofh.api.energy.EnergyStorage;
import com.cofh.api.energy.IEnergyHandler;

import cpw.mods.fml.common.Optional;

public class TileEntityTurbine extends TileEntity implements ISidedInventory, IEnergyEmitter, IEnergySource, IFluidHandler, IEnergyHandler{
	
	//TODO	import steam export energy, do rendering/disable rendering
	
	public boolean isMultiBlock = false;
	private Turbine type;
	private Turbine temp_type;
	private ForgeDirection direction = ForgeDirection.UNKNOWN;
	public int currentEUOutput;
	private boolean firstTick = true;
	private ItemStack[] items;
	private EnergyStorage buffer;
	private FluidTank tank;
	
	public TileEntityTurbine(){
		items = new ItemStack[1];
		buffer = new EnergyStorage(1000);
		tank = new FluidTank(1000);
	}
	
	@Override
	public void updateEntity(){
		if(!worldObj.isRemote){
			if(firstTick){
				firstTick = false;
				firstTick();
			}
			if(!isActuallyAMultiBlock(worldObj, xCoord, yCoord, zCoord)){
				this.isMultiBlock = false;
				this.setHousingParts(getDirection(), false);
			}else{
				
			}
		}
	}
	
	public void firstTick() {
		MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
	}
	
	public int getCurrentEUOutput(){
		Turbine t = this.getTurbineType();
		return ((getCurrentSteamInput()-t.getMinSteamInput()) * t.getMaxEUOutput()) / t.getMaxSteamInput();
	}
	
	private int getCurrentSteamInput() {
		return 0;
	}

	public ForgeDirection getDirection(){
		return direction;
	}
	
	public void setDirection(ForgeDirection dire){
		this.direction = dire;
	}
	
	public void setTurbineType(Turbine par1){
		type = par1;
	}
	
	public Turbine getTurbineType(){
		return type;
	}

	@Override
	public void writeToNBT(NBTTagCompound comp){
		super.writeToNBT(comp);
		comp.setBoolean("isMultiBlock", isMultiBlock);
		
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < getSizeInventory(); i++) {		
			ItemStack stack = getStackInSlot(i);
			
			if (stack != null) {
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte)i);
				stack.writeToNBT(item);
				items.appendTag(item);
			}
		}
		
		comp.setTag("Items", items);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound comp){
		super.readFromNBT(comp);
		isMultiBlock = comp.getBoolean("isMultiBlock");
		
		NBTTagList items = comp.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		
		for (int i = 0; i < items.tagCount(); i++) {
			NBTTagCompound item = (NBTTagCompound)items.getCompoundTagAt(i);
			int slot = item.getByte("Slot");
			
			if (slot >= 0 && slot < getSizeInventory()) {
				setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
			}
		}
	}
	
	public void tryFormMultiBlock(World world, int x, int y, int z) {
		if(!world.isRemote){
			if(isActuallyAMultiBlock(world, x, y, z)){
				formMultiBlock(world, x, y, z);
			}
		}
	}
	
	//determines wether the structure is complete and what kind of turbine it is.
	public boolean isActuallyAMultiBlock(World world, int x, int y, int z){
		
		if(!world.isRemote){
			if(world.getBlock(x + 3, y, z) == adtuBlocks.End){
				setTemp_type(Turbines.laval);
				setDirection(ForgeDirection.EAST);
				//checking if the multiblock is complete
					//first layer
				if(world.getBlock(x + 1, y - 1, z - 1) == adtuBlocks.TurbineHousing && world.getBlock(x + 1, y -1 , z) 
						== adtuBlocks.TurbineHousing && world.getBlock(x + 1,  y - 1, z + 1) == adtuBlocks.TurbineHousing
						&& world.getBlock(x + 1, y, z - 1) == adtuBlocks.TurbineHousing && world.getBlock(x + 1, y, z) == adtuBlocks.TurbineHousing
						&& world.getBlock(x + 1, y, z + 1) == adtuBlocks.TurbineHousing && world.getBlock(x + 1, y + 1, z - 1)
						== adtuBlocks.TurbineHousing && world.getBlock(x + 1, y + 1, z) == adtuBlocks.TurbineHousing
						&& world.getBlock(x + 1, y + 1, z + 1) == adtuBlocks.TurbineHousing && world.getBlock(x + 2, y, z) == adtuBlocks.TurbineHousing
						/*&& !this.arePartsAlreadyPart(world, getDirection())*/){
						
					return true;
				}else{
					return false;
				}
			}else if(world.getBlock(x - 3, y, z) == adtuBlocks.End){
				setTemp_type(Turbines.laval);
				setDirection(ForgeDirection.WEST);
				//checking if the multiblock is complete
				if(world.getBlock(x - 1, y - 1, z - 1) == adtuBlocks.TurbineHousing && world.getBlock(x - 1, y - 1 , z) 
						== adtuBlocks.TurbineHousing && world.getBlock(x - 1,  y - 1, z + 1) == adtuBlocks.TurbineHousing
						&& world.getBlock(x - 1, y, z - 1) == adtuBlocks.TurbineHousing && world.getBlock(x - 1, y, z) == adtuBlocks.TurbineHousing
						&& world.getBlock(x - 1, y, z + 1) == adtuBlocks.TurbineHousing && world.getBlock(x - 1, y + 1, z - 1)
						== adtuBlocks.TurbineHousing && world.getBlock(x - 1, y + 1, z) == adtuBlocks.TurbineHousing
						&& world.getBlock(x - 1, y + 1, z + 1) == adtuBlocks.TurbineHousing && world.getBlock(x - 2, y, z) == adtuBlocks.TurbineHousing){
						
					return true;
					
				}else{
					return false;
				}
			}else if(world.getBlock(x, y, z + 3) == adtuBlocks.End){
				setTemp_type(Turbines.laval);
				setDirection(ForgeDirection.SOUTH);
				
				if(world.getBlock(x-1, y-1, z+1) == adtuBlocks.TurbineHousing && world.getBlock(x, y-1, z+1) == adtuBlocks.TurbineHousing
					&& world.getBlock(x+1, y-1, z+1) == adtuBlocks.TurbineHousing && world.getBlock(x-1, y, z+1) == adtuBlocks.TurbineHousing
					&& world.getBlock(x, y, z+1) == adtuBlocks.TurbineHousing && world.getBlock(x+1, y, z+1) == adtuBlocks.TurbineHousing
					&& world.getBlock(x-1, y+1, z+1) == adtuBlocks.TurbineHousing && world.getBlock(x, y+1, z+1) == adtuBlocks.TurbineHousing
					&& world.getBlock(x+1, y+1, z+1) == adtuBlocks.TurbineHousing && world.getBlock(x, y, z+2) == adtuBlocks.TurbineHousing){
					//-------------------------------------------
					return true;
				}else{
					return false;
				}
			
			}else if(world.getBlock(x, y, z - 3) == adtuBlocks.End){
				setTemp_type(Turbines.laval);
				setDirection(ForgeDirection.NORTH);
				
				if(world.getBlock(x-1, y-1, z-1)==adtuBlocks.TurbineHousing && world.getBlock(x, y-1, z-1)==adtuBlocks.TurbineHousing
					&& world.getBlock(x+1, y-1, z-1)==adtuBlocks.TurbineHousing && world.getBlock(x-1, y, z-1)==adtuBlocks.TurbineHousing
					&& world.getBlock(x, y, z-1)==adtuBlocks.TurbineHousing && world.getBlock(x+1, y, z-1)==adtuBlocks.TurbineHousing
					&& world.getBlock(x-1, y+1, z-1)==adtuBlocks.TurbineHousing && world.getBlock(x, y+1, z-1)==adtuBlocks.TurbineHousing
					&& world.getBlock(x+1, y+1, z-1)==adtuBlocks.TurbineHousing && world.getBlock(x, y, z-2)==adtuBlocks.TurbineHousing){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	/**
	 * this sounds weird...
	 * 
	 * returns true if there is already a multiblock at the location
	 * 
	 * @param ForgeDirection: the direction the turbines is facing (can get by calling getDirection())
	 * @return
	 */
	private boolean arePartsAlreadyPart(World world, ForgeDirection direction){
		if(direction == ForgeDirection.EAST){
			if(((TileEntityHousing)world.getTileEntity(xCoord + 1, yCoord - 1, zCoord - 1)).isPartOfMultiBlock() ||
			   ((TileEntityHousing)world.getTileEntity(xCoord + 1, yCoord - 1, zCoord)).isPartOfMultiBlock() ||
			   ((TileEntityHousing)world.getTileEntity(xCoord + 1, yCoord - 1, zCoord + 1)).isPartOfMultiBlock() ||
			   ((TileEntityHousing)world.getTileEntity(xCoord + 1, yCoord, zCoord - 1)).isPartOfMultiBlock() ||
			   ((TileEntityHousing)world.getTileEntity(xCoord + 1, yCoord, zCoord)).isPartOfMultiBlock() || 
			   ((TileEntityHousing)world.getTileEntity(xCoord + 1, yCoord, zCoord + 1)).isPartOfMultiBlock() || 
			   ((TileEntityHousing)world.getTileEntity(xCoord + 1, yCoord + 1, zCoord - 1)).isPartOfMultiBlock() || 
			   ((TileEntityHousing)world.getTileEntity(xCoord + 1, yCoord + 1, zCoord)).isPartOfMultiBlock() || 
			   ((TileEntityHousing)world.getTileEntity(xCoord + 1, yCoord + 1, zCoord + 1)).isPartOfMultiBlock() ||
			   ((TileEntityHousing)world.getTileEntity(xCoord + 1, yCoord, zCoord)).isPartOfMultiBlock()){
				//---------------------------------------------------------------------------------------------------
				
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}			
	}
	
	private void setHousingParts(ForgeDirection direction, boolean part){
		if(direction == direction.EAST){
			((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord - 1, zCoord - 1)).setPartOfMultiBlock(part);
			((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord - 1, zCoord)).setPartOfMultiBlock(part);
			((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord - 1, zCoord + 1)).setPartOfMultiBlock(part);
			((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord, zCoord - 1)).setPartOfMultiBlock(part);
			((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord, zCoord)).setPartOfMultiBlock(part);
			((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord, zCoord + 1)).setPartOfMultiBlock(part);
			((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord + 1, zCoord - 1)).setPartOfMultiBlock(part);
			((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord + 1, zCoord)).setPartOfMultiBlock(part);
			((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord + 1, zCoord + 1)).setPartOfMultiBlock(part);
			((TileEntityHousing)worldObj.getTileEntity(xCoord + 2, yCoord, zCoord)).setPartOfMultiBlock(part);
		}
	}
	
	public void formMultiBlock(World world, int x, int y, int z){
		//TODO do renderering
		
		setTurbineType(this.getTemp_type());
		this.isMultiBlock = true;
		Log.addInfo("multiblock formed at :" + x + ", " + y + ", " + z);
		Log.addInfo("multiblock has type: " + this.getTurbineType().getTurbineName());
		Log.addInfo("multiblock direction: " + direction.toString().toLowerCase());
	}

	public Turbine getTemp_type() {
		return temp_type;
	}

	public void setTemp_type(Turbine temp_type) {
		this.temp_type = temp_type;
	}

	private void convertSteamIntoPower(){
		
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

//------------------------------------
//IC2 API stuff \/
	@Optional.Method(modid = "IC2")
	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
		return true;
	}

	@Optional.Method(modid = "IC2")
	@Override
	public double getOfferedEnergy() {
		return 40;
	}

	@Optional.Method(modid = "IC2")
	@Override
	public void drawEnergy(double amount) {
		
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
		if(resource.getFluid().getID() != FluidRegistry.getFluidID("Steam")){
			return 0;
		}

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
	
//----------------------------------------------------
//RF API stuff \/

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return buffer.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return buffer.getMaxEnergyStored();
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
}
