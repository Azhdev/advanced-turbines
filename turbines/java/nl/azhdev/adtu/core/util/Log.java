package nl.azhdev.adtu.core.util;

import nl.azhdev.adtu.lib.Reference;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	private static Logger logger = LogManager.getLogger("FML");
	private static String modId = " [" + Reference.modid + "]: ";
	
	public static void init(){
		logger.log(Level.INFO, "Starting");
	}
	
	public static void addInfo(String info){
		logger.log(Level.INFO, modId + info);
	}
	
	public static void addError(String error){
		logger.log(Level.ERROR, modId + error);
	}
	
	public static void addWarning(String warn){
		logger.log(Level.WARN, modId + warn);
	}
}
