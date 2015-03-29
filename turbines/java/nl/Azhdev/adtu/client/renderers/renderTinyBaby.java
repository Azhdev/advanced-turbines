package nl.Azhdev.adtu.client.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import nl.Azhdev.adtu.client.models.tinybabyturbine;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityTurbineTiny;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class renderTinyBaby extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler{

	private tinybabyturbine model;
	
	private static ResourceLocation texture = new ResourceLocation("adtu", "textures/models/tinybabyturbine.png");
	
	public renderTinyBaby(tinybabyturbine model){
		this.model = model;
	}
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return -1;
	}

	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float var8) {
		TileEntityTurbineTiny te = (TileEntityTurbineTiny)var1;
		GL11.glPushMatrix();
		
		GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		model.renderTile(te, 0, 0, 0, 0, 0, 0.125F);
		
		GL11.glPopMatrix();
	}

}
