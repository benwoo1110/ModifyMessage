package dev.benergy10.modifymessage.messages;

import dev.benergy10.modifymessage.ModifyMessage;
import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message {

    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]){6}");

    private final ModifyMessage plugin;
    private final String key;
    private final String matchText;
    private final String replaceText;

    public Message(ModifyMessage plugin, String key, String matches, String replace) {
        this.plugin = plugin;
        this.key = key;
        this.matchText = matches;
        this.replaceText = colourise(replace);
    }

    private String colourise(String text) {
        Matcher matcher = HEX_PATTERN.matcher(text);
        while (matcher.find()) {
            ChatColor hexColor = ChatColor.of(matcher.group().substring(1));
            text = text.replace(matcher.group(), hexColor.toString());
            matcher.reset(text);
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public String getKey() {
        return key;
    }

    public String getMatchText() {
        return matchText;
    }

    public String getReplaceText() {
        return replaceText;
    }

    @Override
    public String toString() {
        return "Message{" +
                "key='" + key + '\'' +
                ", matchText='" + matchText + '\'' +
                ", replaceText='" + replaceText + '\'' +
                '}';
    }
}
