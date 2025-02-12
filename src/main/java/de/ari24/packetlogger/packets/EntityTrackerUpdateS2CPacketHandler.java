package de.ari24.packetlogger.packets;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.network.packet.s2c.play.EntityTrackerUpdateS2CPacket;

public class EntityTrackerUpdateS2CPacketHandler implements BasePacketHandler<EntityTrackerUpdateS2CPacket> {
    @Override
    public String name() {
        return "SetEntityMetadata";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Set_Entity_Metadata";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Updates one or more metadata properties for an existing entity. Any properties not included in the Metadata field are left unchanged.");
        jsonObject.add("wikiVgNotes", JsonNull.INSTANCE);
        jsonObject.addProperty("entityId", "The entity's ID");
        jsonObject.addProperty("trackedValues", "The entity's metadata. See https://wiki.vg/Entity_metadata#Entity_Metadata_Format");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(EntityTrackerUpdateS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        ConvertUtils.appendEntity(jsonObject, packet.id());

        JsonArray list = new JsonArray(packet.trackedValues().size());
        for (DataTracker.SerializedEntry<?> entry : packet.trackedValues()) {
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("id", entry.id());
            jsonObject1.addProperty("value", entry.value().toString());
            list.add(jsonObject1);
        }

        jsonObject.add("trackedValues", ConvertUtils.GSON_INSTANCE.toJsonTree(list));
        return jsonObject;
    }
}
