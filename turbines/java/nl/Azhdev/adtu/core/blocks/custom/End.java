package nl.Azhdev.adtu.core.blocks.custom;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityEnd;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityHousing;
import nl.Azhdev.adtu.core.generic.GenericAzhdevBlock;
import nl.Azhdev.adtu.lib.AdtuConstants;

public class End extends GenericAzhdevBlock implements ITileEntityProvider{

	public End(Material mat) {
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityEnd();
	}	
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int j, float p_149727_7_, float p_149727_8_, float p_149727_9_){
		if(!world.isRemote){
			TileEntityHousing housing = (TileEntityHousing) world.getTileEntity(x, y, z);
			if(AdtuConstants.useDebugCode){
				String output = "" + housing.isPartOfMultiBlock();
				player.addChatComponentMessage(new ChatComponentText(output));
			}
		}
		return true;
	}
}
