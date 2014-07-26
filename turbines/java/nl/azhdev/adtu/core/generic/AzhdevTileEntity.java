package nl.azhdev.adtu.core.generic;

import net.minecraft.tileentity.TileEntity;

public class AzhdevTileEntity extends TileEntity{
	
	private boolean first = true;
	
	@Override
	public void updateEntity(){
		if(first){
			first = false;
			firstTick();
		}
	}

	public void firstTick() {}
}
