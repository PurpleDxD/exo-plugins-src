package net.runelite.client.plugins.xo.utils;

import net.runelite.client.config.*;

@ConfigGroup("xoUtils")
public interface UtilsConfiguration extends Config {

    @ConfigTitle(
            name = "Delays",
            description = "",
            position = 0
    )
    String delays = "Delays";

    @Units(Units.MILLISECONDS)
    @ConfigItem(
            keyName = "miniDelay",
            name = "Minimum delay",
            description = "Absolute minimum delay between actions",
            position = 1,
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
            position = 1,
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
            position = 1,
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
            position = 1,
            title = delays
    )
    default int deviation() {
        return 10;
    }

    @ConfigItem(
            keyName = "weightedDistribution",
            name = "Weighted Distribution",
            description = "Shifts the random distribution towards the lower end at the target, otherwise it will be an even distribution",
            position = 1,
            title = delays
    )
    default boolean weightedDistribution() {
        return false;
    }

}
