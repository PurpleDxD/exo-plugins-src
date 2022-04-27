package net.runelite.client.plugins.xo.utils.constants;

public enum InteractionType {

    SHADOW("Invoke + Click"),
    CLICK("Click Only"),
    INVOKE("Invoke Only");

    public final String name;

    InteractionType(String name) {
        this.name = name;
    }

}
