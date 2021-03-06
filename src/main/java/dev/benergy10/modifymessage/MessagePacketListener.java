package dev.benergy10.modifymessage;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import dev.benergy10.modifymessage.messages.Message;
import dev.benergy10.modifymessage.utils.Logging;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;

import java.util.List;

public class MessagePacketListener extends PacketAdapter {

    private final ModifyMessage plugin;

    public MessagePacketListener(ModifyMessage plugin) {
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Server.CHAT);
        this.plugin = plugin;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        if (event.isCancelled() || event.getPacketType() != PacketType.Play.Server.CHAT) {
            return;
        }

        StructureModifier<WrappedChatComponent> components = event.getPacket().getChatComponents();
        int index = 0;
        for (WrappedChatComponent chatComponent : components.getValues()) {
            if (chatComponent == null) {
                continue;
            }
            BaseComponent[] baseComponents = ComponentSerializer.parse(chatComponent.getJson());
            TextComponent textComponent = new TextComponent(baseComponents);
            Logging.debug(chatComponent.getJson());
            Logging.debug(textComponent.toPlainText());

            final Message message = this.plugin.getMessageMap().findMessage(textComponent.toPlainText());
            if (message == null) {
                return;
            }

            TextComponent newTextComponent = new TextComponent(TextComponent.fromLegacyText(message.getReplaceText()));
            String newTextJson = ComponentSerializer.toString(newTextComponent);

            Logging.debug(newTextJson);
            Logging.debug(newTextComponent.toPlainText());
            Logging.debug(newTextComponent.toLegacyText());

            components.write(index++, WrappedChatComponent.fromJson(newTextJson));
        }
    }
}
