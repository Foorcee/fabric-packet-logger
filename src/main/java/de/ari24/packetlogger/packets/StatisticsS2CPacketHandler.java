package de.ari24.packetlogger.packets;

import com.google.gson.JsonObject;
import de.ari24.packetlogger.utils.ConvertUtils;
import net.minecraft.network.packet.s2c.play.StatisticsS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class StatisticsS2CPacketHandler implements BasePacketHandler<StatisticsS2CPacket> {
    @Override
    public String name() {
        return "AwardStatistics";
    }

    @Override
    public String url() {
        return "https://wiki.vg/Protocol#Award_Statistics";
    }

    @Override
    public JsonObject description() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("general", "Sent as a response to Client Command (id 1). Will only send the changed values if previously requested. ");
        jsonObject.addProperty("wikiVgNotes", "List of all statistics are here: https://wiki.vg/Protocol#Award_Statistics");
        jsonObject.addProperty("count", "Number of statistics in the array.");
        jsonObject.addProperty("stats.id", "Id of the statistic.");
        jsonObject.addProperty("stats.value", "Value of the statistic.");
        return jsonObject;
    }

    @Override
    public JsonObject serialize(StatisticsS2CPacket packet) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("count", packet.getStatMap().size());

        List<JsonObject> serializedStats = new ArrayList<>();

        packet.getStatMap().forEach((key, value) -> {
            JsonObject child = new JsonObject();
            Identifier identifier = Registries.STAT_TYPE.getId(key.getType());

            if (identifier == null) {
                child.addProperty("id", key.toString());
            } else {
                child.addProperty("id", identifier.toString());
            }

            child.addProperty("value", value);
            serializedStats.add(child);
        });

        jsonObject.add("stats", ConvertUtils.GSON_INSTANCE.toJsonTree(serializedStats));
        return jsonObject;
    }
}
