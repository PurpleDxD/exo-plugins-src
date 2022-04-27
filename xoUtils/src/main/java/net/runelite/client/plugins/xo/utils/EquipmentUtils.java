package net.runelite.client.plugins.xo.utils;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.xo.utils.models.EquippedItem;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Singleton
public class EquipmentUtils {

    @Inject
    private ClientUtils clientUtils;

    @Inject
    private ItemManager itemManager;

    public List<EquippedItem> getEquippedItems() {
        if (!clientUtils.getClient().isClientThread()) {
            return clientUtils.getFromClientThread(this::getEquippedItems);
        }

        ItemContainer equipmentContainer = clientUtils.getClient().getItemContainer(InventoryID.EQUIPMENT);

        List<EquippedItem> equipped = new ArrayList<>();
        if (equipmentContainer != null) {
            Item[] items = equipmentContainer.getItems();
            for (Item item : items) {
                if (item.getId() == -1 || item.getId() == 0) {
                    continue;
                }

                equipped.add(new EquippedItem(itemManager.getItemComposition(item.getId()),
                                              itemManager.getItemStats(item.getId(), false)
                ));
            }

        }

        return equipped;
    }

}
