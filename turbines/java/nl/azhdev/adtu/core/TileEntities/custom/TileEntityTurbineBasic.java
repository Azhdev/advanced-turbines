package nl.Azhdev.adtu.core.TileEntities.custom;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import nl.Azhdev.adtu.core.blocks.adtuBlocks;
import nl.Azhdev.adtu.core.generic.AzhdevTileEntity;
import nl.Azhdev.adtu.turbine.Turbine;

/**
 * 
 * @author Azhdev
 *
 * @date 14 aug. 2014
 * 
 * the basic class of the turbineTileEntities
 */

public class TileEntityTurbineBasic extends AzhdevTileEntity implements IFluidHandler, IInventory{
	private TileEntityEnd TileEnd;
	private Turbine type;
	private ForgeDirection direction;
	private ArrayList<int[]> locations = new ArrayList<int[]>();
	private ArrayList<Block> blocks = new ArrayList<Block>();
	private FluidTank tank;
	private int CurrentBlockAmount;
	private int MaxBlockAmount;
	
	public TileEntityTurbineBasic(){
		tank = new FluidTank(16000);
	}
	
	@Override
	public void updateEntity(){
		if(!worldObj.isRemote){
			if(hasType())setMaxBlockAmount(type.getMaxBlockAmount());
		}
	}

	public boolean hasType(){
		return type != null;
	}
	
	public void setType(Turbine nType){
		type = nType;
	}
	
	public Turbine getType(){
		return type;
	}
	
	public int getNextBlockNumber(){
		return CurrentBlockAmount + 1;
	}
	
	public int getMaxBlockAmount() {
		return MaxBlockAmount;
	}

	public void setMaxBlockAmount(int maxBlockAmount) {
		MaxBlockAmount = maxBlockAmount;
	}

	public void addBlockToMultiBlock(int x, int y, int z, Block block){
		int[] var1 = new int[2];
		var1[0] = x; var1[1] = y; var1[2] = z;
		this.addBlockToList(var1, block);
	}
	
	public void addBlockToList(int[] par1, Block block){
		if(par1.length == 3) locations.add(par1);
		if(block != null) blocks.add(block);
	}
	
	public ArrayList<int[]> getLocations(){
		return locations;
	}
	
	public ArrayList<Block> getBlocks(){
		return blocks;
	}
	
	public ForgeDirection getDirection(){
		return this.direction;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		tank.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		tank.readFromNBT(nbt);
	}
	
	

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return null;
	}

	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int p_70301_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		// TODO Auto-generated method stub
		return false;
	}
}
