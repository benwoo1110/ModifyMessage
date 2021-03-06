package dev.benergy10.modifymessage.nodes;

import java.util.HashMap;
import java.util.Map;

public class NodeMap {

    private final Map<String, Node> nodeMap;

    public NodeMap() {
        this.nodeMap = new HashMap<>();
    }

    public void add(Node node) {
        nodeMap.put(node.getWord(), node);
    }

    public Node getByWord(String word) {
        return nodeMap.get(word);
    }

    @Override
    public String toString() {
        return String.valueOf(nodeMap);
    }
}
