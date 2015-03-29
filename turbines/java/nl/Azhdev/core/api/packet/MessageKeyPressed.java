package nl.Azhdev.core.api.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import nl.Azhdev.core.api.util;
import nl.Azhdev.core.api.keybindings.KeyBindings;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MessageKeyPressed implements IMessage, IMessageHandler<MessageKeyPressed, IMessage>{

	private byte keyPressed;
	
	public MessageKeyPressed(){}
	
	@SideOnly(Side.CLIENT)
	public MessageKeyPressed(KeyBinding key){
		if(Loader.isModLoaded("Taunts!")){
			if(key == KeyBindings.activate){
				this.keyPressed = (byte)KeyBindings.activate.getKeyCode();
			}
		}
		
	}
	
	@Override
	public IMessage onMessage(MessageKeyPressed message, MessageContext ctx) {
		
		EntityPlayer player = ctx.getServerHandler().playerEntity;
		
		if(player != null){
			if(!player.worldObj.isRemote){
				String n = util.getRandomSound();
				NetworkHandler.sendToAll(new PacketPlaySound(n, player, 0.1F, 1, true));
			}
		}
		
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.keyPressed = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(keyPressed);
	}

}
