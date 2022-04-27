package net.runelite.client.plugins.xo.utils;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.LocatableQueryResults;
import net.runelite.api.queries.ActorQuery;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Singleton
public abstract class ActorUtils<ReturnType, EntityType extends Actor, QueryType extends ActorQuery<EntityType, QueryType>> {

    @Inject
    protected ClientUtils clientUtils;

    protected List<ReturnType> get() {
        return getReturnModel(getQueryResults().stream().collect(Collectors.toList()));
    }

    protected List<ReturnType> get(String... actorNames) {
        return getReturnModel(getQueryResults(actorNames).stream().collect(Collectors.toList()));
    }

    protected ReturnType getNearest() {
        return getReturnModel(getQueryResults().nearestTo(clientUtils.getClient().getLocalPlayer()));
    }

    protected ReturnType getNearest(String... actorNames) {
        return getReturnModel(getQueryResults(actorNames).nearestTo(clientUtils.getClient().getLocalPlayer()));
    }

    protected LocatableQueryResults<EntityType> getQueryResults(String... actorNames) {
        if (!clientUtils.getClient().isClientThread()) {
            return clientUtils.getFromClientThread(() -> getQueryResults(actorNames));
        }

        ActorQuery<EntityType, QueryType> actorQuery = actorNames.length > 0 ? getActorQuery().nameContains(actorNames) : getActorQuery();
        return actorQuery.result(clientUtils.getClient());
    }

    protected abstract QueryType getActorQuery();

    protected abstract ReturnType getReturnModel(EntityType entityType);

    protected abstract List<ReturnType> getReturnModel(List<EntityType> entityTypes);

}
