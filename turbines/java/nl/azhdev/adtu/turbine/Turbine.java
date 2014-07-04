package nl.azhdev.adtu.turbine;

public class Turbine{
	private int maxEUOutput;
	private int minSteamInput;
	private int maxSteamInput;
	private String TurbineName;
	
	public Turbine(){
		setMaxEUOutput(0);
		setMinSteamInput(0);
		setMaxSteamInput(0);
		setTurbineName("");
	}
	
	public void setTurbineName(String name) {
		this.TurbineName = name;
		
	}

	public String getTurbineName(){
		return TurbineName;
	}
	
	public void setMaxEUOutput(int par1){
		this.maxEUOutput = par1;
	}
	
	public int getMaxEUOutput(){
		return this.maxEUOutput;
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
}
