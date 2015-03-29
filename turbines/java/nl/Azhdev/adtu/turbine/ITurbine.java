package nl.Azhdev.adtu.turbine;

import net.minecraft.block.Block;

public interface ITurbine {
	
	public void setMaxBlockAmount(int amount);
	public int getMaxBlockAmount();
	public void setTurbineName(String name);
	public String getTurbineName();
	public void setMinSteamInput(int steamInput);
	public int getMinSteamInput();
	public void setMaxSteamInput(int par1);
	public int getMaxSteamInput();
	public int getMaxRFOutput();
	public void setMaxRFOutput(int maxRFOutput);
	public Block getMasterBlock();
	public void setMasterBlock(Block masterBlock);
}
