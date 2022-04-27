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

import net.runelite.client.config.*;

@ConfigGroup("xoTest")
public interface TestConfiguration extends Config {

    String BOOL_CAPTURE_EVENTS_NAME = "shouldCaptureEvents";
    String BUTTON_PRINT_EQUIP_NAME = "btnPrintEquip";
    String BUTTON_PRINT_INVENTORY_NAME = "btnPrintInventory";
    String BUTTON_PRINT_INVENTORY_OPCODE = "btnPrintInventoryOpCode";
    String BUTTON_PRINT_NPC_OPCODE = "btnPrintNPCOpCode";
    String BUTTON_PRINT_NEAREST_NPC = "btnPrintNearestNPC";
    String BUTTON_CONSUME_INVENTORY_ITEM = "btnConsumeInventoryItem";
    String BUTTON_EQUIP_INVENTORY_ITEM = "btnEquipInventoryItem";

    @ConfigItem(
            keyName = BOOL_CAPTURE_EVENTS_NAME,
            name = "Event Capture",
            description = "Toggles the capture of events (mouse, keyboard)",
            position = 0
    )
    default boolean shouldCaptureEvents() {
        return false;
    }

    @ConfigTitle(
            name = "Print",
            description = "",
            position = 50
    )
    String print = "Print";

    @ConfigItem(
            keyName = BUTTON_PRINT_EQUIP_NAME,
            name = "Print Equipment",
            description = "Prints your equipped item names to the chat box",
            position = 51,
            title = print
    )
    default Button printEquipment() {
        return new Button();
    }

    @ConfigItem(
            keyName = BUTTON_PRINT_INVENTORY_NAME,
            name = "Print Inventory",
            description = "Prints your inventory item names to the chat box",
            position = 52,
            title = print
    )
    default Button printInventory() {
        return new Button();
    }

    @ConfigItem(
            keyName = BUTTON_PRINT_INVENTORY_OPCODE,
            name = "Print Inventory OpCodes",
            description = "Prints the first item in your inventory's OpCodes to the chat box",
            position = 53,
            title = print
    )
    default Button printInventoryOpCode() {
        return new Button();
    }

    @ConfigItem(
            keyName = BUTTON_PRINT_NEAREST_NPC,
            name = "Print Nearest NPC",
            description = "Prints the local player's nearest NPC",
            position = 54,
            title = print
    )
    default Button printNearestNPC() {
        return new Button();
    }

    @ConfigItem(
            keyName = BUTTON_PRINT_NPC_OPCODE,
            name = "Print NPC OpCodes",
            description = "Prints the nearest NPC's OpCodes",
            position = 55,
            title = print
    )
    default Button printNPCOpCode() {
        return new Button();
    }

    @ConfigTitle(
            name = "Actions",
            description = "",
            position = 100
    )
    String actions = "Actions";

    @ConfigItem(
            keyName = BUTTON_CONSUME_INVENTORY_ITEM,
            name = "Consume Inventory Item",
            description = "Consumes the first item in your inventory (drink or eat)",
            position = 101,
            title = actions
    )
    default Button consumeInventoryItem() {
        return new Button();
    }

    @ConfigItem(
            keyName = BUTTON_EQUIP_INVENTORY_ITEM,
            name = "Equip Inventory Item",
            description = "Equips the first item in your inventory",
            position = 102,
            title = actions
    )
    default Button equipInventoryItem() {
        return new Button();
    }
}
