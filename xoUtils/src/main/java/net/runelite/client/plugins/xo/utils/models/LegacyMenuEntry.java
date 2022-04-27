package net.runelite.client.plugins.xo.utils.models;

import lombok.Value;

@Value
public class LegacyMenuEntry {

    private final String option;
    private final String target;
    private final int identifier;
    private final int menuAction;
    private final int param0;
    private final int param1;
    private final boolean forceLeftClick;

}
