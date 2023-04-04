package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import net.minecraft.network.packet.s2c.play.ExperienceBarUpdateS2CPacket;

public class ExperienceBarUpdateS2CPacketHandler implements BasePacketHandler<ExperienceBarUpdateS2CPacket> {
    @Override
    public String id() {
        return "SetExperience";
    }

    @Override
    public JsonObject serialize(ExperienceBarUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("barProgress", packet.getBarProgress());
        jsonObject.addProperty("totalExperience", packet.getExperience());
        jsonObject.addProperty("level", packet.getExperienceLevel());
        return jsonObject;
    }
}
