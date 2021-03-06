package dev.benergy10.modifymessage;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import dev.benergy10.modifymessage.messages.Message;
import dev.benergy10.modifymessage.messages.MessageMap;
import dev.benergy10.modifymessage.utils.Logging;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ModifyMessage extends JavaPlugin {

    private ProtocolManager protocolManager;
    private MessageMap messageMap;

    @Override
    public void onEnable() {
        Logging.setup(this);
        Logging.doDebugLog(false);

        this.messageMap = new MessageMap(this);
        loadConfig();

        this.protocolManager = ProtocolLibrary.getProtocolManager();
        this.protocolManager.addPacketListener(new MessagePacketListener(this));
    }

    public void loadConfig() {
        File configFile = new File(this.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            this.saveResource("config.yml", false);
        }

        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configFile);
        ConfigurationSection messageSection = configuration.getConfigurationSection("messages");
        if (messageSection == null) {
            Logging.severe("Error getting messages from config.yml.");
            return;
        }

        for (String messageKey : messageSection.getKeys(false)) {
            Message newMessage = new Message(
                    this,
                    messageKey,
                    messageSection.getString(messageKey + ".matches"),
                    messageSection.getString(messageKey + ".replace")
            );
            this.messageMap.addMessage(newMessage);
            Logging.debug(String.valueOf(newMessage));
        }

        Logging.debug(String.valueOf(this.messageMap));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    public MessageMap getMessageMap() {
        return messageMap;
    }
}
