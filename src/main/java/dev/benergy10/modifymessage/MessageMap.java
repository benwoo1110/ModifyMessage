package dev.benergy10.modifymessage;

import java.util.ArrayList;
import java.util.List;

public class MessageMap {

    private final ModifyMessage plugin;
    private final List<Message> messages;

    public MessageMap(ModifyMessage plugin) {
        this.plugin = plugin;
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public Message findMessage(String messageText) {
        return messages.stream()
                .filter(msg -> msg.getMatchText().equals(messageText))
                .findFirst()
                .orElse(null);
    }
}
