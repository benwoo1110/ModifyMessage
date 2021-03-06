package dev.benergy10.modifymessage.messages;

import dev.benergy10.modifymessage.ModifyMessage;
import dev.benergy10.modifymessage.nodes.Node;

public class MessageMap {

    private final ModifyMessage plugin;
    private final Node baseNode;

    public MessageMap(ModifyMessage plugin) {
        this.plugin = plugin;
        this.baseNode = new Node();
    }

    public void addMessage(Message message) {
        String[] wordArray = message.getMatchText().split(" ");
        Node currentNode = baseNode;
        for (String word : wordArray) {
            if (word.equals(wordArray[wordArray.length - 1])) {
                currentNode = currentNode.addLastChild(word, message);
                continue;
            }
            currentNode = currentNode.addChild(word);
        }
    }

    public Message findMessage(String messageText) {
        String[] wordArray = messageText.split(" ");
        Node currentNode = baseNode;
        for (String word : wordArray) {
            currentNode = currentNode.getChild(word);
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode.isEnd() ? currentNode.getMessage() : null;
    }

    @Override
    public String toString() {
        return "MessageMap{" +
                "baseNode=" + baseNode +
                '}';
    }
}
