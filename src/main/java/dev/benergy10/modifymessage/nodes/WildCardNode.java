package dev.benergy10.modifymessage.nodes;

public class WildCardNode extends Node {

    private String placeholder;
    private String prefix;
    private String suffix;
    private int index;

    public WildCardNode(String word) {
        super(word);
    }
}
