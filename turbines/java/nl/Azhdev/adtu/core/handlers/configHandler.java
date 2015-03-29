package nl.Azhdev.adtu.core.handlers;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import nl.Azhdev.adtu.lib.AdtuConstants;

public class configHandler{
	
	public static void init(File file){
		Configuration config = new Configuration(file);
		
		config.load();
		
		AdtuConstants.useDebugCode = config.getBoolean("use debugging code", "MISC", AdtuConstants.useDebugCode_default, "enable this to see the debugging code (your chat might get spammed a bit)");
		
		config.save();
	}
}
