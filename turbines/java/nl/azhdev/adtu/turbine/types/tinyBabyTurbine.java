package nl.Azhdev.adtu.turbine.types;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import nl.Azhdev.adtu.core.blocks.adtuBlocks;
import nl.Azhdev.adtu.turbine.Turbine;
/**
 * 
 * @author Azhdev
 *
 * tinyBabyTurbine.java created at 15:22:51 27 jan. 2015
 *
 */
public class tinyBabyTurbine extends Turbine{
	
	public int blockAmount;
	
	public tinyBabyTurbine(){
		setTurbineName("Tiny Baby Turbine");
		setMaxRFOutput(10);
		setMinSteamInput(5);
		setMaxSteamInput(10);
		setMasterBlock(adtuBlocks.tinyTurbine);
		blockAmount = 1;
	}
	
	public static Block getNextBlockType(){
		return adtuBlocks.End;
	}
	
	public static ArrayList<int[]> getBlocknextPosition(ForgeDirection direction){
		ArrayList<int[]> list = new ArrayList<int[]>();
		switch(direction){
		case EAST:
			int[] eposition1 = new int[3];
			eposition1[0] = 1;
			eposition1[1] = 0;
			eposition1[2] = 0;
			list.add(eposition1);
			break;
		case WEST:
			int[] wposition1 = new int[3];
			wposition1[0] = -1;
			wposition1[1] = 0;
			wposition1[2] = 0;
			list.add(wposition1);
			break;
		case NORTH:
			int[] nposition1 = new int[3];
			nposition1[0] = 0;
			nposition1[1] = 0;
			nposition1[2] = -1;
			list.add(nposition1);
			break;
		case SOUTH:
			int[] sposition1 = new int[3]; 
			sposition1[0] = 0;
			sposition1[1] = 0;
			sposition1[2] = 1;
			list.add(sposition1);
			break;
		default:
			
			break;
		}
		return list;
	}
}
