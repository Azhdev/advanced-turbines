package nl.Azhdev.adtu.turbine;

import net.minecraft.block.Block;
import nl.Azhdev.adtu.core.blocks.adtuBlocks;
/**
 * 
 * @author Azhdev
 *
 * Turbine.java created at 15:23:04 27 jan. 2015
 *
 */
public class Turbine implements ITurbine{
	private int minSteamInput;
	private int maxSteamInput;
	private int maxRFOutput;
	private int blockAmount;
	private Block masterBlock;
	private String TurbineName;
	
	public Turbine(){
		setMaxRFOutput(0);
		setMinSteamInput(0);
		setMaxSteamInput(0);
		setMaxBlockAmount(0);
		setMasterBlock(adtuBlocks.turbineBlock);
		setTurbineName("");
	}
	
	public void setMaxBlockAmount(int amount){
		blockAmount = amount;
	}
	
	public int getMaxBlockAmount(){
		return blockAmount;
	}
	
	public void setTurbineName(String name) {
		this.TurbineName = name;
		
	}

	public String getTurbineName(){
		return TurbineName;
	}
	
	public void setMinSteamInput(int par1){
		this.minSteamInput = par1;
	}
	
	public int getMinSteamInput(){
		return this.minSteamInput;
	}
	
	public void setMaxSteamInput(int par1){
		this.maxSteamInput = par1;
	}
	
	public int getMaxSteamInput(){
		return this.maxSteamInput;
	}

	public int getMaxRFOutput() {
		return maxRFOutput;
	}

	public void setMaxRFOutput(int maxRFOutput) {
		this.maxRFOutput = maxRFOutput;
	}

	public Block getMasterBlock() {
		return masterBlock;
	}

	@Override
	public void setMasterBlock(Block nmasterBlock) {
		masterBlock = nmasterBlock;
	}

	
}
