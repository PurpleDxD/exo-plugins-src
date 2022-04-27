package net.runelite.client.plugins.xo.utils;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.GameState;
import net.runelite.api.MenuAction;
import net.runelite.api.Point;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.plugins.xo.utils.models.Action;
import net.runelite.client.plugins.xo.utils.models.InventoryItem;
import net.runelite.client.plugins.xo.utils.models.LegacyMenuEntry;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ExecutorService;

@Slf4j
@Singleton
public class AutomationUtils {

    @Inject
    private Random random;

    @Inject
    private ExecutorService executorService;

    @Inject
    private UtilsConfiguration config;

    @Inject
    private ClientUtils clientUtils;

    @Inject
    private Container container;

    public void Interact(InventoryItem item, List<String> actionNames) {
        Optional<Action> oAction = item.getAction(actionNames);
        if (!oAction.isPresent()) {
            return;
        }

        executorService.submit(() -> {
            try {
                Thread.sleep(randomDelay());

                LegacyMenuEntry menuEntry = getLegacyMenuEntry(item, oAction.get());

                switch (config.interactionType()) {
                    case SHADOW:
                        // TODO: Invoke + Click ?
                        break;
                    case CLICK:
                        Click(item.getBounds());
                        break;
                    case INVOKE:
                        Invoke(menuEntry);
                        break;
                }

                container.put(UtilsPlugin.class, LegacyMenuEntry.class, menuEntry);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void Invoke(LegacyMenuEntry menuEntry) {
        // TODO
    }

    private void Click(Rectangle bounds) {
        Point clickPoint = getClickPoint(bounds);

        if (clientUtils.getClient().isStretchedEnabled()) {
            final Dimension stretched = clientUtils.getClient().getStretchedDimensions();
            final Dimension real = clientUtils.getClient().getRealDimensions();
            final double width = (stretched.width / real.getWidth());
            final double height = (stretched.height / real.getHeight());

            clickPoint = new Point((int) (clickPoint.getX() * width), (int) (clickPoint.getY() * height));
        }

        mouseEvent(MouseEvent.MOUSE_PRESSED, clickPoint, false);
        mouseEvent(MouseEvent.MOUSE_RELEASED, clickPoint, false);
        mouseEvent(MouseEvent.MOUSE_CLICKED, clickPoint, false);
    }

    private LegacyMenuEntry getLegacyMenuEntry(InventoryItem item, Action action) {
        int opCode = action.getOpCode();
        MenuAction menuAction = opCode < 6 ? MenuAction.CC_OP : MenuAction.CC_OP_LOW_PRIORITY;

        return new LegacyMenuEntry(action.getName(),
                                   item.getFullName(),
                                   opCode,
                                   menuAction.getId(),
                                   item.getIndex(),
                                   WidgetInfo.INVENTORY.getId(),
                                   false
        );
    }

    private long randomDelay() {
        int min = config.minimumDelay();
        int max = config.maximumDelay();
        int target = config.target();
        int deviation = config.deviation();

        if (config.weightedDistribution()) {
            return (long) clamp((-Math.log(Math.abs(random.nextGaussian()))) * deviation + target, min, max);
        } else {
            return (long) clamp(Math.round(random.nextGaussian() * deviation + target), min, max);
        }
    }

    private Point getClickPoint(Rectangle rect) {
        final int x = (int) (rect.getX() + getRandomIntBetweenRange((int) rect.getWidth() / 6 * -1,
                                                                    (int) rect.getWidth() / 6
        ) + rect.getWidth() / 2);
        final int y = (int) (rect.getY() + getRandomIntBetweenRange((int) rect.getHeight() / 6 * -1,
                                                                    (int) rect.getHeight() / 6
        ) + rect.getHeight() / 2);

        return new Point(x, y);
    }

    private void mouseEvent(int id, Point point, Boolean move) {
        MouseEvent e = new MouseEvent(clientUtils.getClient().getCanvas(),
                                      id,
                                      System.currentTimeMillis(),
                                      0,
                                      point.getX(),
                                      point.getY(),
                                      move ? 0 : 1,
                                      false,
                                      1
        );

        if (clientUtils.getClient().getGameState() != GameState.LOGGED_IN) {
            return;
        }

        clientUtils.getClient().getCanvas().dispatchEvent(e);
    }

    private int getRandomIntBetweenRange(int min, int max) {
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }

    private double clamp(double val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

}
