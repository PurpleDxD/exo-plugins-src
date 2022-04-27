package net.runelite.client.plugins.xo.utils.models;

import com.google.common.base.Strings;
import lombok.Value;
import net.runelite.api.NPC;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value
public class GameNPC {

    private final NPC npc;

    // TODO: Abstract out actions (duplicate in InventoryItem)
    private final List<GameAction> actions;

    public GameNPC(NPC npc) {
        this.npc = npc;

        String[] widgetActions = npc.getComposition().getActions();
        actions = IntStream.range(0, widgetActions.length)
                           .boxed()
                           .filter(i -> !Strings.isNullOrEmpty(widgetActions[i]))
                           .map(i -> new GameAction(widgetActions[i], i + 1))
                           .collect(Collectors.toList());
    }

    public String getName() {
        return npc.getName();
    }

    public Integer getId() {
        return npc.getId();
    }

    public boolean hasAction(List<String> actionNames) {
        List<String> currentActionNames = actions.stream().map(GameAction::getName).collect(Collectors.toList());
        return actionNames.stream().anyMatch(currentActionNames::contains);
    }

    public Optional<GameAction> getAction(List<String> actionNames) {
        return actions.stream().filter(a -> actionNames.contains(a.getName())).findFirst();
    }
}
