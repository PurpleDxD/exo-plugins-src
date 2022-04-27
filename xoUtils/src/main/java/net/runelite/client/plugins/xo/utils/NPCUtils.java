package net.runelite.client.plugins.xo.utils;

import net.runelite.api.LocatableQueryResults;
import net.runelite.api.NPC;
import net.runelite.api.queries.ActorQuery;
import net.runelite.api.queries.NPCQuery;
import net.runelite.client.plugins.xo.utils.models.GameNPC;

import java.util.List;
import java.util.stream.Collectors;

public class NPCUtils extends ActorUtils<GameNPC, NPC, NPCQuery> {

    public List<GameNPC> get(int... actorIds) {
        return getQueryResults(actorIds).stream().map(n -> new GameNPC(n)).collect(Collectors.toList());
    }

    public GameNPC getNearest(int... actorIds) {
        return new GameNPC(getQueryResults(actorIds).nearestTo(clientUtils.getClient().getLocalPlayer()));
    }

    protected LocatableQueryResults<NPC> getQueryResults(int... actorIds) {
        if (!clientUtils.getClient().isClientThread()) {
            return clientUtils.getFromClientThread(() -> getQueryResults(actorIds));
        }

        ActorQuery<NPC, NPCQuery> actorQuery = actorIds.length > 0 ? getActorQuery().idEquals(actorIds) : getActorQuery();
        return actorQuery.result(clientUtils.getClient());
    }

    @Override
    protected NPCQuery getActorQuery() {
        return new NPCQuery();
    }

    @Override
    protected GameNPC getReturnModel(NPC npc) {
        return new GameNPC(npc);
    }

    @Override
    protected List<GameNPC> getReturnModel(List<NPC> npcs) {
        return npcs.stream().map(n -> new GameNPC(n)).collect(Collectors.toList());
    }
}
