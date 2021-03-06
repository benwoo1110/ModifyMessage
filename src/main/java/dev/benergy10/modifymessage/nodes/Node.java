package dev.benergy10.modifymessage.nodes;

import dev.benergy10.modifymessage.messages.Message;

public class Node {

    private final String word;
    private boolean end;
    private Message message;
    private final NodeMap childNodeMap;

    public Node() {
        this(null);
    }

    public Node(String word) {
        this.word = word;
        this.childNodeMap = new NodeMap();
    }

    public void setEnd(Message targetMessage) {
        if (end) {
            throw new IllegalStateException("Node is already has an end.");
        }

        this.end = true;
        this.message = targetMessage;
    }

    public Node addChild(String nextWord) {
        Node node = getChild(nextWord);
        if (node == null) {
            node = new Node(nextWord);
            childNodeMap.add(node);
        }
        return node;
    }

    public Node addLastChild(String nextWord, Message targetMessage) {
        Node lastNode = getChild(nextWord);
        if (lastNode == null) {
            lastNode = new Node(nextWord);
            childNodeMap.add(lastNode);
        }
        lastNode.setEnd(targetMessage);
        return lastNode;
    }

    public boolean hasChild(String nextWord) {
        return childNodeMap.getByWord(nextWord) != null;
    }

    public Node getChild(String nextWord) {
        return childNodeMap.getByWord(nextWord);
    }

    public String getWord() {
        return word;
    }

    public Message getMessage() {
        return message;
    }

    public boolean isEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Node{" +
                "word='" + word + '\'' +
                ", end=" + end +
                ", childNodeMap=" + childNodeMap +
                '}';
    }
}
