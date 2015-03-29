package nl.Azhdev.adtu.client.models;

import java.util.ArrayList;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import nl.Azhdev.adtu.core.TileEntities.custom.TileEntityTurbineTiny;

public class tinybabyturbine extends ModelBase{
	
	private ArrayList<ModelRenderer> parts;
	
	public tinybabyturbine(){
		parts = new ArrayList<ModelRenderer>();
		textureHeight = textureWidth = 128;
		
		
		ModelRenderer support1 = new ModelRenderer(this);
		support1.addBox(-2, -1, -3, 4, 2, 6);
		support1.setRotationPoint(0,0,0);
		parts.add(support1);
	}
	
	public void renderTile(TileEntityTurbineTiny te, float rotation, float pos, float a, float b, float c, float mult){
		for(ModelRenderer part : parts){
			part.render(mult);
		}
	}
}
