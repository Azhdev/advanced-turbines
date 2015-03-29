package nl.Azhdev.adtu.core.blocks.custom;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityTurbineTiny;
import nl.Azhdev.adtu.turbine.types.tinyBabyTurbine;
/**
 * 
 * @author Azhdev
 *
 * tinyTurbineBlock.java created at 15:22:42 27 jan. 2015
 *
 */
public class tinyTurbineBlock extends turbineBlock {

	public tinyTurbineBlock(Material mat) {
		super(mat);
		
	}

	@Override
	public  TileEntity createNewTileEntity(World var1, int var2){
		return new TileEntityTurbineTiny();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float hitx, float hity, float hitz){
        ForgeDirection direction = ForgeDirection.NORTH;
        int discriminator = 0;
        TileEntityTurbineTiny te = (TileEntityTurbineTiny)world.getTileEntity(x, y, z);
		if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Item.getItemFromBlock(tinyBabyTurbine.getNextBlockType())){
        	
			int l = MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
			
			switch(l){
			case 0:
				direction = ForgeDirection.SOUTH;
				break;
			case 1:
				direction = ForgeDirection.WEST;
				break;
			case 2:
				direction = ForgeDirection.NORTH;
				break;
			case 3:
				direction = ForgeDirection.EAST;
				break;
			default:
				
				break;
			}
			
			/**
			if(player.rotationYaw > 135 && player.rotationYaw <= 180){
        		direction = ForgeDirection.NORTH;
        	}else if(player.rotationYaw >= -180 && player.rotationYaw < -135){
        		direction = ForgeDirection.NORTH;
        	}else if(player.rotationYaw > 45 && player.rotationYaw<= 135){
        		direction = ForgeDirection.WEST;
        	}else if(player.rotationYaw > -45 && player.rotationYaw <= 45){
        		direction = ForgeDirection.SOUTH;
        	}else if(player.rotationYaw <= -45 && player.rotationYaw >= -135){
        		direction = ForgeDirection.EAST;
        	}
        	*/
			if(te.getNextBlockNumber() <= te.getMaxBlockAmount()){
				discriminator = te.getNextBlockNumber();
	        	if(!player.capabilities.isCreativeMode)player.getCurrentEquippedItem().stackSize--;
	            ArrayList<int[]> locationlist = tinyBabyTurbine.getBlocknextPosition(direction);
	            int[] location = locationlist.get(discriminator);
	            world.setBlock(x + location[0], y + location[1], z + location[2], tinyBabyTurbine.getNextBlockType());
	            return true;
			}else{
				return false;
			}
		}
        
			
		return true;
    }
}
