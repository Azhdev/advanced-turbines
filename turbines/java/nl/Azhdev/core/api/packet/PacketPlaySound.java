package nl.Azhdev.core.api.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketPlaySound extends LocationDoublePacket<PacketPlaySound>{

	private String sound;
	private Float volume, pitch;
	private double x, y, z;
	private boolean bool;
	
	public PacketPlaySound(){}
	
	public PacketPlaySound(String sound, double x, double y, double z, float volume, float pitch, boolean bool){
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.sound = sound;
		this.volume = volume;
		this.pitch = pitch;
		this.bool = bool;
	}
	
	public PacketPlaySound(String sound, EntityPlayer player, float volume, float pitch, boolean bool){
		this.sound = sound;
		this.x = (float)player.posX;
		this.y = (float)player.posY;
		this.z = (float)player.posZ;
		this.volume = volume;
		this.pitch = pitch;
		this.bool = bool;
	}
	
	@Override
	public void toBytes(ByteBuf buf){
		ByteBufUtils.writeUTF8String(buf, sound);
		buf.writeFloat(volume);
		buf.writeFloat(pitch);
		buf.writeBoolean(bool);
	}
	
	@Override
	public void fromBytes(ByteBuf buf){
		sound = ByteBufUtils.readUTF8String(buf);
		volume = buf.readFloat();
		pitch = buf.readFloat();
		bool = buf.readBoolean();
	}

	@Override
	public void handleClientSide(PacketPlaySound message, EntityPlayer player) {
		player.playSound(message.sound, message.volume, message.pitch);		
	}

	@Override
	public void handleServerSide(PacketPlaySound message, EntityPlayer player) {
		// TODO Auto-generated method stub
				
	}

}
