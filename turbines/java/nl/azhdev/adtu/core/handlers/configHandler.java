package nl.azhdev.adtu.core.handlers;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import nl.azhdev.adtu.lib.Reference;

public class configHandler{
	
	public static void init(File file){
		Configuration config = new Configuration(file);
		
		config.load();
		
		Reference.loadIC2 = config.get("mods loaded", "IC2", Reference.loadIC2_default).getBoolean(Reference.loadIC2_default);
		
		config.save();
	}
}
