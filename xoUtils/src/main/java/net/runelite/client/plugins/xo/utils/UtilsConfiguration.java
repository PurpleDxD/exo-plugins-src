package net.runelite.client.plugins.xo.utils;

import net.runelite.client.config.*;
import net.runelite.client.plugins.xo.utils.constants.InteractionType;

@ConfigGroup("xoUtils")
public interface UtilsConfiguration extends Config {

    @ConfigItem(
            keyName = "interfactionType",
            name = "Interfaction Type",
            description = "How the plugin will interfact with game objects",
            position = 0
    )
    default InteractionType interactionType() {
        return InteractionType.SHADOW;
    }

    @ConfigTitle(
            name = "Delays",
            description = "",
            position = 50
    )
    String delays = "Delays";

    @Units(Units.MILLISECONDS)
    @ConfigItem(
            keyName = "miniDelay",
            name = "Minimum delay",
            description = "Absolute minimum delay between actions",
            position = 51,
            title = delays
    )
    default int minimumDelay() {
        return 120;
    }

    @Units(Units.MILLISECONDS)
    @ConfigItem(
            keyName = "maxiDelay",
            name = "Maximum delay",
            description = "Absolute maximum delay between actions",
            position = 51,
            title = delays
    )
    default int maximumDelay() {
        return 240;
    }

    @Units(Units.MILLISECONDS)
    @ConfigItem(
            keyName = "target",
            name = "Delay Target",
            description = "",
            position = 51,
            title = delays
    )
    default int target() {
        return 180;
    }

    @Units(Units.MILLISECONDS)
    @ConfigItem(
            keyName = "deviation",
            name = "Delay Deviation",
            description = "",
            position = 51,
            title = delays
    )
    default int deviation() {
        return 10;
    }

    @ConfigItem(
            keyName = "weightedDistribution",
            name = "Weighted Distribution",
            description = "Shifts the random distribution towards the lower end at the target, otherwise it will be an even distribution",
            position = 51,
            title = delays
    )
    default boolean weightedDistribution() {
        return false;
    }

}
