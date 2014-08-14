package nl.azhdev.adtu.core.blocks.custom;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import nl.azhdev.adtu.adtu;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityTurbine;
import nl.azhdev.adtu.core.TileEntities.custom.TileEntityTurbineBasic;
import nl.azhdev.adtu.core.generic.GenericAzhdevBlock;
import nl.azhdev.adtu.core.generic.GenericAzhdevBlockContainer;
import nl.azhdev.adtu.core.items.adtuItems;

public class turbineBlock extends GenericAzhdevBlockContainer{
	
	public TileEntityTurbine turbine;
	
	public turbineBlock(Material mat) {
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileEntityTurbineBasic();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int j, float p_149727_7_, float p_149727_8_, float p_149727_9_){
		if(!world.isRemote){
			player.openGui(adtu.instance, 0, world, x, y, z);
		}
		return true;
	}
}
