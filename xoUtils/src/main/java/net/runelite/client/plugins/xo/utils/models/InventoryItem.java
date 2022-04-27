package net.runelite.client.plugins.xo.utils.models;

import com.google.common.base.Strings;
import lombok.Value;
import net.runelite.api.widgets.WidgetItem;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
public class InventoryItem {

    private static final Pattern pattern = Pattern.compile("(?<=<col=[a-zA-Z\\d]{6}>)(.*?)(?=<\\/col>)");

    private final WidgetItem widgetItem;
    private final List<Action> actions;
    private final String name;

    public InventoryItem(WidgetItem widgetItem) {
        this.widgetItem = widgetItem;

        String[] widgetActions = widgetItem.getWidget().getActions();
        actions = IntStream.range(0, widgetActions.length)
                           .boxed()
                           .filter(i -> !Strings.isNullOrEmpty(widgetActions[i]))
                           .map(i -> new Action(widgetActions[i], i + 1))
                           .collect(Collectors.toList());

        Matcher match = pattern.matcher(getFullName());
        name = match.find() ? match.group() : "";
    }

    public int getId() {
        return widgetItem.getId();
    }

    public String getFullName() {
        return widgetItem.getWidget().getName();
    }

    public int getQuantity() {
        return widgetItem.getQuantity();
    }

    public int getIndex() {
        return widgetItem.getIndex();
    }

    public Rectangle getBounds() {
        return widgetItem.getWidget().getBounds();
    }

    public boolean hasAction(List<String> actionNames) {
        List<String> currentActionNames = actions.stream().map(Action::getName).collect(Collectors.toList());
        return actionNames.stream().anyMatch(currentActionNames::contains);
    }

    public Optional<Action> getAction(List<String> actionNames) {
        return actions.stream().filter(a -> actionNames.contains(a.getName())).findFirst();
    }

}