/*
 * Copyright (c) 2018, SomeoneWithAnInternetConnection
 * Copyright (c) 2018, oplosthee <https://github.com/oplosthee>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.xo.test;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.ConfigButtonClicked;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;
import net.runelite.client.input.MouseListener;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.xo.utils.*;
import net.runelite.client.plugins.xo.utils.models.EquippedItem;
import net.runelite.client.plugins.xo.utils.models.InventoryItem;
import org.pf4j.Extension;

import javax.inject.Inject;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;


@Extension
@PluginDependency(UtilsPlugin.class)
@PluginDescriptor(
        name = "xoTest",
        enabledByDefault = false,
        description = "Exo - Test Plugin",
        tags = {"xo"}
)
@Slf4j
public class TestPlugin extends Plugin implements MouseListener, KeyListener {

    @Inject
    private TestConfiguration config;

    @Inject
    private MouseManager mouseManager;

    @Inject
    private KeyManager keyManager;

    @Inject
    private ChatUtils chatUtils;

    @Inject
    private EquipmentUtils equipmentUtils;

    @Inject
    private InventoryUtils inventoryUtils;

    @Inject
    private AutomationUtils automationUtils;

    @Provides
    TestConfiguration provideConfig(ConfigManager configManager) {
        return configManager.getConfig(TestConfiguration.class);
    }

    @Override
    protected void startUp() {
        log.info("startUp()");
    }

    @Override
    protected void shutDown() {
        log.info("shutDown()");
    }

    @Subscribe
    private void onConfigButtonPressed(ConfigButtonClicked configButtonClicked) {
        if (!configButtonClicked.getGroup().equalsIgnoreCase("xoTest")) {
            return;
        }

        log.info("{} pressed", configButtonClicked.getKey());

        switch (configButtonClicked.getKey()) {
            case TestConfiguration.BUTTON_PRINT_EQUIP_NAME:
                printEquipment();
                break;
            case TestConfiguration.BUTTON_PRINT_INVENTORY_NAME:
                printInventory();
                break;
            case TestConfiguration.BUTTON_CONSUME_INVENTORY_ITEM:
                consumeInventoryItem();
                break;
        }
    }

    @Subscribe
    private void onConfigChanged(ConfigChanged event) {
        if (!event.getGroup().equalsIgnoreCase("xoTest")) {
            return;
        }

        log.info("{} changed", event.getKey());

        switch (event.getKey()) {
            case TestConfiguration.BOOL_CAPTURE_EVENTS_NAME:
                toggleEventCapture();
                break;
        }
    }

    private void toggleEventCapture() {
        if (config.shouldCaptureEvents()) {
            mouseManager.registerMouseListener(this);
            keyManager.registerKeyListener(this);
        } else {
            mouseManager.unregisterMouseListener(this);
            keyManager.unregisterKeyListener(this);
        }
    }

    private void printEquipment() {
        List<EquippedItem> equippedItems = equipmentUtils.getEquippedItems();

        if (equippedItems.isEmpty()) {
            chatUtils.sendGameMessage("No equipped items found");
            return;
        }

        for (EquippedItem item : equippedItems) {
            chatUtils.sendGameMessage(String.format("[%s] %s", item.getSlotName(), item.getName()));
        }
    }

    private void printInventory() {
        List<InventoryItem> inventoryItems = inventoryUtils.getAllItems();

        if (inventoryItems.isEmpty()) {
            chatUtils.sendGameMessage("No inventory items found");
            return;
        }

        for (InventoryItem item : inventoryItems) {
            chatUtils.sendGameMessage(String.format("[%d] %s x%d", item.getIndex(), item.getName(), item.getQuantity()));
        }
    }

    private void consumeInventoryItem() {
        List<InventoryItem> inventoryItems = inventoryUtils.getConsumableItems();

        if (inventoryItems.isEmpty()) {
            chatUtils.sendGameMessage("No consumable inventory items found");
            return;
        }

        automationUtils.click(inventoryItems.stream().findFirst().get().getBounds());
    }

    @Override
    public MouseEvent mouseClicked(MouseEvent mouseEvent) {
        log.info("Mouse clicked: {}", mouseEvent.getButton());
        log.info("Is shift pressed: {}", mouseEvent.isShiftDown());
        return mouseEvent;
    }

    @Override
    public MouseEvent mousePressed(MouseEvent mouseEvent) {
        log.info("Mouse pressed");
        return mouseEvent;
    }

    @Override
    public MouseEvent mouseReleased(MouseEvent mouseEvent) {
        log.info("Mouse released");
        return mouseEvent;
    }

    @Override
    public MouseEvent mouseEntered(MouseEvent mouseEvent) {
        log.info("Mouse entered");
        return mouseEvent;
    }

    @Override
    public MouseEvent mouseExited(MouseEvent mouseEvent) {
        log.info("Mouse exited");
        return mouseEvent;
    }

    @Override
    public MouseEvent mouseDragged(MouseEvent mouseEvent) {
        //log.info("Mouse dragged");
        return mouseEvent;
    }

    @Override
    public MouseEvent mouseMoved(MouseEvent mouseEvent) {
        //log.info("Mouse moved");
        return mouseEvent;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        log.info("Key typed: {}", keyEvent.getID());
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        log.info("Key pressed: {}", keyEvent.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        log.info("Key released: {}", keyEvent.getID());
        log.info("Key char: {}", keyEvent.getKeyChar());
    }

}
