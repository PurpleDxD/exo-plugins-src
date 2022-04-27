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
