package net.runelite.client.plugins.xo.utils.impl;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.InventoryID;
import net.runelite.api.Point;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.xo.utils.models.InventoryItem;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Singleton
public class InventoryUtils {

    @Inject
    private ClientUtils clientUtils;

    @Inject
    private ItemManager itemManager;

    public boolean isBankOpen() {
        return clientUtils.getClient().getItemContainer(InventoryID.BANK) != null;
    }

    public boolean isDepositBoxOpen() {
        return clientUtils.getClient().getWidget(WidgetInfo.DEPOSIT_BOX_INVENTORY_ITEMS_CONTAINER) != null;
    }

    public List<InventoryItem> getAllItems() {
        if (!clientUtils.getClient().isClientThread()) {
            return clientUtils.getFromClientThread(this::getAllItems);
        }

        Widget inventoryWidget = getInventoryWidget();
        if (inventoryWidget == null) {
            return Collections.emptyList();
        }

        Widget[] children = inventoryWidget.getDynamicChildren();
        if (children == null) {
            return Collections.emptyList();
        }

        List<InventoryItem> widgetItems = new ArrayList<>();
        for (Widget item : children) {
            if (item.getItemId() != 6512) {
                widgetItems.add(new InventoryItem(createWidgetItem(item)));
            }
        }

        return widgetItems;
    }

    private WidgetItem createWidgetItem(Widget item) {
        boolean isDragged = item.isWidgetItemDragged(item.getItemId());

        int dragOffsetX = 0;
        int dragOffsetY = 0;

        if (isDragged) {
            Point p = item.getWidgetItemDragOffsets();
            dragOffsetX = p.getX();
            dragOffsetY = p.getY();
        }
        // set bounds to same size as default inventory
        Rectangle bounds = item.getBounds();
        bounds.setBounds(bounds.x - 1, bounds.y - 1, 32, 32);
        Rectangle dragBounds = item.getBounds();
        dragBounds.setBounds(bounds.x + dragOffsetX, bounds.y + dragOffsetY, 32, 32);

        return new WidgetItem(item.getItemId(), item.getItemQuantity(), item.getIndex(), bounds, item, dragBounds);
    }

    private Widget getInventoryWidget() {
        Widget inventoryWidget;

        if (isBankOpen()) {
            inventoryWidget = clientUtils.getClient().getWidget(WidgetInfo.BANK_INVENTORY_ITEMS_CONTAINER);
        } else if (isDepositBoxOpen()) {
            inventoryWidget = clientUtils.getClient().getWidget(WidgetInfo.DEPOSIT_BOX_INVENTORY_ITEMS_CONTAINER);
        } else {
            inventoryWidget = clientUtils.getClient().getWidget(WidgetInfo.INVENTORY);
        }

        return inventoryWidget;
    }

}
