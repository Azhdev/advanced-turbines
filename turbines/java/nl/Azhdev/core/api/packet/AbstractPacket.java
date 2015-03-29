package nl.Azhdev.core.api.packet;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public abstract class AbstractPacket<REQ extends IMessage> implements IMessage, IMessageHandler<REQ, REQ>{

	@Override
	public REQ onMessage(REQ message, MessageContext ctx) {
		
		if(ctx.side == Side.SERVER){
			handleServerSide(message, ctx.getServerHandler().playerEntity);
		}else{
			handleClientSide(message, FMLClientHandler.instance().getClient().thePlayer);
		}
		
		return null;
	}

	/**
	 * handle a packet on the clientside
	 * 
	 * @param message
	 * @param player for reference
	 */
	public abstract void handleClientSide(REQ message, EntityPlayer player);
	
	/**
	 * handle a packet on the server side
	 * 
	 * @param message
	 * @param player for reference
	 */
	public abstract void handleServerSide(REQ message, EntityPlayer player);
}
