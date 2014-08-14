package nl.azhdev.adtu.core.TileEntities.custom;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import nl.azhdev.adtu.core.blocks.adtuBlocks;
import nl.azhdev.adtu.core.generic.AzhdevTileEntity;
import nl.azhdev.adtu.core.util.Log;
import nl.azhdev.adtu.turbine.Turbine;
import nl.azhdev.adtu.turbine.Turbines;

/**
 * 
 * @author Azhdev
 *
 * @date 14 aug. 2014
 * 
 * the basic class of the turbineTileEntities
 */

public class TileEntityTurbineBasic extends AzhdevTileEntity implements IFluidHandler{
	private Block end = adtuBlocks.End;
	private Block h = adtuBlocks.TurbineHousing;
	private TileEntityEnd TileEnd;
	private Turbine type;
	private ForgeDirection direction;
	private boolean isAMultiBlock = false;
	private boolean firstAsMB = true;
	private boolean firstAsSingle = true;
	private FluidTank tank;
	
	public TileEntityTurbineBasic(){
		tank = new FluidTank(1000);
	}
	
	@Override
	public void updateEntity(){
		if(!worldObj.isRemote){
			isAMultiBlock = isAMultiBlock();
			if(isAMultiBlock){
				if(firstAsMB){
					firstAsMB = false;
					firstAsMB();
					firstAsSingle = true;
				}
				//output section
				if(canOutput()){
					outputEnergy();
					removeSteam();
				}
			}else{
				if(firstAsSingle){
					firstAsSingle();
					firstAsSingle = false;
					firstAsMB = true;
				}
			}
			type = getType(worldObj, xCoord, yCoord, zCoord);
			setCorrectBlock(xCoord, yCoord, zCoord);
			
			
			
		}
	}

	private void removeSteam() {
		
	}

	private void outputEnergy() {
		
	}

	private boolean canOutput() {
		return false;
	}

	private void firstAsSingle() {
		//TODO remove from multiblock, remove rendering etc.
		Log.addInfo("multiBlock at: " + xCoord + ", " + yCoord + ", " + zCoord + " has been removed");
		this.setPartOfMB(worldObj, xCoord, yCoord, zCoord, direction, type, false);
	}

	private void firstAsMB() {
		//TODO add to multiblock, rendering etc.
		Log.addInfo("multiBlock Formed at: " + xCoord + ", " + yCoord + ", " + zCoord);
		Log.addInfo("multiBlock has type: " + type.getTurbineName() + "and direction: " + direction.name());
		this.setPartOfMB(worldObj, xCoord, yCoord, zCoord, direction, type, true);
	}

	private boolean isAMultiBlock(){
		if(worldObj.getBlock(xCoord, yCoord, zCoord) != adtuBlocks.turbineBlock){
			return true;
		}else{
			return false;
		}
	}
	
	public void setCorrectBlock(int x, int y, int z){
		if(type != null && type != Turbines.unknown){
			if(isRestFilled(worldObj, x, y, z) /*&& !isHousingAlreadyPart(direction)*/){
				worldObj.setBlock(x, y, z, type.getBlock());
			}else{
				worldObj.setBlock(x, y, z, adtuBlocks.turbineBlock);
			}
		}else{
			worldObj.setBlock(x, y, z, adtuBlocks.turbineBlock);
		}
	}
	
	public Turbine getType(World world, int x, int y, int z){
		if(world.getBlock(x - 1, y, z) == end){
			direction = ForgeDirection.WEST;
			return Turbines.tiny;
		}else if(world.getBlock(x + 1, y, z) == end){
			direction = ForgeDirection.EAST;
			return Turbines.tiny;
		}else if(world.getBlock(x, y, z - 1) == end){
			direction = ForgeDirection.SOUTH;
			return Turbines.tiny;
		}else if(world.getBlock(x, y, z + 1) == end){
			direction = ForgeDirection.NORTH;
			return Turbines.tiny;
		}else if(world.getBlock(x - 3, y, z) == end){
			direction = ForgeDirection.WEST;
			return Turbines.laval;
		}else if(world.getBlock(x + 3, y, z) == end){
			direction = ForgeDirection.EAST;
			return Turbines.laval;
		}else if(world.getBlock(x, y, z - 3) == end){
			direction = ForgeDirection.NORTH;
			return Turbines.laval;
		}else if(world.getBlock(x, y, z + 3) == end){
			direction = ForgeDirection.SOUTH;
			return Turbines.laval;
		}else{
			return Turbines.unknown;
		}
	}
	
	public boolean isRestFilled(World world, int x, int y, int z){
		if(type == Turbines.tiny){
			return true;
		}else if(type == Turbines.laval){
			if(direction == ForgeDirection.NORTH){
				if(world.getBlock(x - 1, y - 1, z - 1) == h && world.getBlock(x, y - 1, z - 1) == h && world.getBlock(x + 1, y - 1, z - 1) == h && world.getBlock(x - 1, y, z - 1) == h && world.getBlock(x, y, z - 1) == h
						&& world.getBlock(x + 1, y, z - 1) == h && world.getBlock(x - 1, y + 1, z - 1) == h && world.getBlock(x, y + 1, z - 1) == h && world.getBlock(x + 1, y + 1, z - 1) == h 
						&& world.getBlock(x, y, z - 2) == h && world.getBlock(x, y, z - 3) == end){
					
					return true;
				}else{
					return false;
				}
			}else if(direction == ForgeDirection.SOUTH){
				if(world.getBlock(x - 1, y - 1, z + 1) == h && world.getBlock(x, y - 1, z + 1) == h && world.getBlock(x + 1, y - 1, z + 1) == h && world.getBlock(x - 1, y, z + 1) == h && world.getBlock(x, y, z + 1) == h
						&& world.getBlock(x + 1, y, z + 1) == h && world.getBlock(x - 1, y + 1, z + 1) == h && world.getBlock(x, y + 1, z + 1) == h && world.getBlock(x + 1, y + 1, z + 1) == h 
						&& world.getBlock(x, y, z + 2) == h && world.getBlock(x, y, z + 3) == end){
					
					return true;
				}else{
					return false;
				}
			}else if(direction == ForgeDirection.WEST){
				if(world.getBlock(x - 1, y - 1, z - 1) == h && world.getBlock(x - 1, y - 1, z) == h && world.getBlock(x - 1, y - 1, z + 1) == h && world.getBlock(x - 1, y, z - 1) == h && world.getBlock(x - 1, y, z) == h
						&& world.getBlock(x - 1, y, z + 1) == h && world.getBlock(x - 1, y + 1, z - 1) == h && world.getBlock(x - 1, y + 1, z) == h && world.getBlock(x - 1, y + 1, z + 1) == h 
						&& world.getBlock(x - 2, y, z) == h && world.getBlock(x - 3, y, z) == end){
					
					return true;
				}else{
					return false;
				}
			}else if(direction == ForgeDirection.EAST){
				if(world.getBlock(x + 1, y - 1, z - 1) == h && world.getBlock(x + 1, y - 1, z) == h && world.getBlock(x + 1, y - 1, z + 1) == h && world.getBlock(x + 1, y, z -1) == h && world.getBlock(x + 1, y, z ) == h
						&& world.getBlock(x + 1, y, z + 1) == h && world.getBlock(x + 1, y + 1, z - 1) == h && world.getBlock(x + 1, y + 1, z) == h && world.getBlock(x + 1, y + 1, z + 1) == h 
						&& world.getBlock(x + 2, y, z) == h && world.getBlock(x + 3, y, z) == end){
					
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
	
	private void setPartOfMB(World world, int x, int y, int z, ForgeDirection direction, Turbine type, boolean part){
		if(type == Turbines.tiny){
			if(direction == ForgeDirection.EAST){
				TileEntityHousing housing = (TileEntityHousing)world.getTileEntity(x + 1, y, z);
				housing.setPartOfMultiBlock(part);
			}
			if(direction == ForgeDirection.WEST){
				TileEntityHousing housing = (TileEntityHousing) world.getTileEntity(x - 1, y, z);
				housing.setPartOfMultiBlock(part);
			}
			if(direction == ForgeDirection.NORTH){
				TileEntityHousing housing = (TileEntityHousing) world.getTileEntity(x, y, z + 1);
				housing.setPartOfMultiBlock(part);
			}
			if(direction == ForgeDirection.SOUTH){
				TileEntityHousing housing = (TileEntityHousing) world.getTileEntity(x, y, z -1 );
				housing.setPartOfMultiBlock(part);
			}
		}
		if(type == Turbines.laval){
			if(direction == ForgeDirection.EAST){
				for(int i = -1; i  < 1; i++){
					for(int j = -1; j < 1; j++){
						TileEntityHousing housing = ((TileEntityHousing)world.getTileEntity(x + 1, y + i, z + j));
						if(housing != null){
							housing.setPartOfMultiBlock(part);
						}
					}
				}
				TileEntityHousing housing1 = (TileEntityHousing) world.getTileEntity(x + 2, y, z);
				if(housing1 != null){
					housing1.setPartOfMultiBlock(part);
				}
			}
			if(direction == ForgeDirection.WEST){
				for(int i = -1; i  < 1; i++){
					for(int j = -1; j < 1; j++){
						TileEntityHousing housing = (TileEntityHousing) world.getTileEntity(x - 1, y + i, z + j);
						if(housing != null){
							housing.setPartOfMultiBlock(part);
						}
					}
				}
				TileEntityHousing housing1 = (TileEntityHousing) world.getTileEntity(x - 2, y, z);
				if(housing1 != null){
					housing1.setPartOfMultiBlock(part);
				}
				
			}
			if(direction == ForgeDirection.NORTH){
				for(int i = -1; i  < 1; i++){
					for(int j = -1; j < 1; j++){
						TileEntityHousing housing = (TileEntityHousing) world.getTileEntity(x + j, y + i, z - 1);
						if(housing != null){
							housing.setPartOfMultiBlock(part);
						}
					}
				}
				TileEntityHousing housing1 = (TileEntityHousing) world.getTileEntity(x, y, z - 2);
				if(housing1 != null){
					housing1.setPartOfMultiBlock(part);
				}

				
			}
			if(direction == ForgeDirection.SOUTH){
				for(int i = -1; i  < 1; i++){
					for(int j = -1; j < 1; j++){
						TileEntityHousing housing = (TileEntityHousing) world.getTileEntity(x + j, y + i, z + 1);
						if(housing != null){
							housing.setPartOfMultiBlock(part);
						}
					}
				}
				TileEntityHousing housing1 = (TileEntityHousing) world.getTileEntity(x, y, z + 2);
				if(housing1 != null){
					housing1.setPartOfMultiBlock(part);
				}
			}
		}
	}
	
	public boolean isHousingAlreadyPart(ForgeDirection direction){
		if(type == Turbines.tiny){
			if(direction == ForgeDirection.EAST){
				return ((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord, zCoord)).isPartOfMultiBlock();
			}
			if(direction == ForgeDirection.WEST){
				return ((TileEntityHousing)worldObj.getTileEntity(xCoord - 1, yCoord, zCoord)).isPartOfMultiBlock();
			}
			if(direction == ForgeDirection.NORTH){
				return ((TileEntityHousing)worldObj.getTileEntity(xCoord, yCoord, zCoord - 1)).isPartOfMultiBlock();
			}
			if(direction == ForgeDirection.SOUTH){
				return ((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord, zCoord + 1)).isPartOfMultiBlock();
			}else{
				return false;
			}
		}
		if(type == Turbines.laval){
			boolean f = false;
			if(direction == ForgeDirection.EAST){
				for(int i = -1; i  < 1; i++){
					for(int j = -1; j < 1; j++){
						TileEntityHousing housing = ((TileEntityHousing)worldObj.getTileEntity(xCoord + 1, yCoord + i, zCoord + j));
						if(housing != null && housing.isPartOfMultiBlock()){
							f = true;
						}
					}
				}
				TileEntityHousing housing1 = (TileEntityHousing) worldObj.getTileEntity(xCoord + 2, yCoord, zCoord);
				if(housing1 != null && housing1.isPartOfMultiBlock()){
					f = true;
				}
			}
			if(direction == ForgeDirection.WEST){
				for(int i = -1; i  < 1; i++){
					for(int j = -1; j < 1; j++){
						TileEntityHousing housing = ((TileEntityHousing)worldObj.getTileEntity(xCoord - 1, yCoord + i, zCoord + j));
						if(housing != null && housing.isPartOfMultiBlock()){
							f = true;
						}
					}
				}
				TileEntityHousing housing1 = (TileEntityHousing) worldObj.getTileEntity(xCoord - 2, yCoord, zCoord);
				if(housing1 != null && housing1.isPartOfMultiBlock()){
					f = true;
				}
			}
			if(direction == ForgeDirection.NORTH){
				for(int i = -1; i  < 1; i++){
					for(int j = -1; j < 1; j++){
						TileEntityHousing housing = ((TileEntityHousing)worldObj.getTileEntity(xCoord + j, yCoord + i, zCoord - 1));
						if(housing != null && housing.isPartOfMultiBlock()){
							f = true;
						}
					}
				}
				TileEntityHousing housing1 = (TileEntityHousing) worldObj.getTileEntity(xCoord, yCoord, zCoord - 2);
				if(housing1 != null && housing1.isPartOfMultiBlock()){
					f = true;
				}
			}
			if(direction == ForgeDirection.SOUTH){
				for(int i = -1; i  < 1; i++){
					for(int j = -1; j < 1; j++){
						TileEntityHousing housing = ((TileEntityHousing)worldObj.getTileEntity(xCoord + j, yCoord + i, zCoord + 1));
						if(housing != null && housing.isPartOfMultiBlock()){
							f = true;
						}
					}
				}
				TileEntityHousing housing1 = (TileEntityHousing) worldObj.getTileEntity(xCoord, yCoord, zCoord + 2);
				if(housing1 != null && housing1.isPartOfMultiBlock()){
					f = true;
				}

			}
			return f;
		}else{
			return false;
		}
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
}
