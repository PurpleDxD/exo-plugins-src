package net.runelite.client.plugins.xo.utils.models;

import lombok.Value;
import net.runelite.api.widgets.WidgetItem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Value
public class InventoryItem {

    private static Pattern pattern = Pattern.compile("(?<=<col=[a-zA-Z\\d]{6}>)(.*?)(?=<\\/col>)");

    private WidgetItem widgetItem;

    public int getId() {
        return widgetItem.getId();
    }

    public String getFullName() {
        return widgetItem.getWidget().getName();
    }

    public String getName() {
        Matcher match = pattern.matcher(getFullName());

        if (match.find()) {
            return match.group();
        }

        return "";
    }

    public int getQuantity() {
        return widgetItem.getQuantity();
    }

    public int getIndex() {
        return widgetItem.getIndex();
    }

}
