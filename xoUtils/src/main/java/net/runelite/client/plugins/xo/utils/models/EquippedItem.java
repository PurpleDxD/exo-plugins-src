package net.runelite.client.plugins.xo.utils.models;

import lombok.Value;
import net.runelite.api.EquipmentInventorySlot;
import net.runelite.api.ItemComposition;
import net.runelite.http.api.item.ItemStats;

@Value
public class EquippedItem {

    private final ItemComposition composition;
    private final ItemStats stats;

    public int getId() {
        return composition.getId();
    }

    public String getName() {
        return composition.getName();
    }

    public int getSlotId() {
        return stats.getEquipment().getSlot();
    }

    public String getSlotName() {
        for (EquipmentInventorySlot slot : EquipmentInventorySlot.values()) {
            if (slot.getSlotIdx() == getSlotId()) {
                return slot.name();
            }
        }

        return "";
    }

}
