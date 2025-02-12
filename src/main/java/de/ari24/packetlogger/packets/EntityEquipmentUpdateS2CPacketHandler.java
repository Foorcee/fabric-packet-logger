package de.ari24.packetlogger.packets;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityEquipmentUpdateS2CPacket;

import java.util.List;

public class EntityEquipmentUpdateS2CPacketHandler implements BasePacketHandler<EntityEquipmentUpdateS2CPacket> {
    @Override
    public String name() {
        return "SetEquipment";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Equipment";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Updates the equipment of an entity's inventory");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity's ID");
        jsonObject.addProperty("equipment", "The updated equipment of the entity");
        return jsonObject;
    }

    private JsonObject serializeEquipment(List<Pair<EquipmentSlot, ItemStack>> equipment) {
        JsonObject jsonObject = new JsonObject();

        equipment.forEach(pair -> {
            jsonObject.addProperty(pair.getFirst().getName(), pair.getSecond().toString());
        });

        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityEquipmentUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.getId());
        jsonObject.add("equipment", serializeEquipment(packet.getEquipmentList()));

        return jsonObject;
    }
}
