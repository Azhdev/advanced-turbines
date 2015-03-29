package nl.Azhdev.adtu.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import nl.Azhdev.adtu.client.containers.ContainerTurbine;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityTurbineBasic;

import org.lwjgl.opengl.GL11;

public class GUITurbine extends GuiContainer{

	public GUITurbine(InventoryPlayer playerInv, TileEntityTurbineBasic tur) {
		super(new ContainerTurbine(playerInv, tur));
		xSize = 176;
		ySize = 218;
	}

	private static final ResourceLocation texture = new ResourceLocation("adtu", "textures/gui/turbine.png");
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1, 1, 1, 1);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
	}

}
