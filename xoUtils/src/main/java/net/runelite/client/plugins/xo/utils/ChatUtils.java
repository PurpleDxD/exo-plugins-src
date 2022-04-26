package net.runelite.client.plugins.xo.utils;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;

import javax.inject.Inject;
import javax.inject.Singleton;

@Slf4j
@Singleton
public class ChatUtils {

    @Inject
    private ChatMessageManager chatMessageManager;

    public void sendGameMessage(String message) {
        String chatMessage = new ChatMessageBuilder().append(ChatColorType.HIGHLIGHT).append(message).build();

        chatMessageManager.queue(QueuedMessage.builder()
                .type(ChatMessageType.CONSOLE)
                .runeLiteFormattedMessage(chatMessage)
                .build());
    }

}
