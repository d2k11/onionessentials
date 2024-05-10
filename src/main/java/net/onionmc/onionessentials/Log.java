package net.onionmc.onionessentials;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;

import java.util.HashMap;

public class Log {
    private static final String PREFIX = "&8&o[lobbyessentials]&7&o ";
    public static HashMap<String, String> legacyColors = new HashMap<String, String>() {{
        put("&0", "<#000000>");
        put("&1", "<#0000AA>");
        put("&2", "<#00AA00>");
        put("&3", "<#00AAAA>");
        put("&4", "<#AA0000>");
        put("&5", "<#AA00AA>");
        put("&6", "<#FFAA00>");
        put("&7", "<#AAAAAA>");
        put("&8", "<#555555>");
        put("&9", "<#5555FF>");
        put("&a", "<#55FF55>");
        put("&b", "<#55FFFF>");
        put("&c", "<#FF5555>");
        put("&d", "<#FF55FF>");
        put("&e", "<#FFFF55>");
        put("&f", "<#FFFFFF>");
        put("&k", "<obf>");
        put("&l", "<b>");
        put("&m", "<st>");
        put("&n", "<u>");
        put("&o", "<i>");
        put("&r", "<reset>");
    }};

    static Tag greentext() {
        return Tag.styling(TextColor.color(0x8d9c4f));
    }

    /**
     * @param message The message to translate.
     * @param donator Whether to enable donator formatting.
     * @return The translated Component.
     */
    public static Component formatChat(String message, boolean donator) {
        if (donator) {
            message = convertLegacy(message);
        }

        MiniMessage DONATOR = MiniMessage.builder()
                .tags(TagResolver.builder()
                        .resolver(StandardTags.color())
                        .resolver(StandardTags.gradient())
                        .resolver(StandardTags.decorations())
                        .resolver(StandardTags.rainbow())
                        .resolver(StandardTags.reset())
                        .build()
                )
                .build();

        // the greentext variable has a weird name so people dont use it on its own to get green text without the indicator
        MiniMessage NORMAL = MiniMessage.builder()
                .tags(TagResolver.resolver("4changreen", greentext()))
                .build();

        // Returns the message formatted at the specified level.
        if (donator) {
            return DONATOR.deserialize(message);
        } else {
            return NORMAL.deserialize(message);
        }
    }

    /**
     * @param message The message to translate.
     * @return The translated Component.
     */
    public static Component format(String message) {
        // Convert legacy color codes to kyori color codes
        message = convertLegacy(message);

        MiniMessage msg = MiniMessage.builder()
                .tags(TagResolver.builder()
                        .resolver(StandardTags.color())
                        .resolver(StandardTags.gradient())
                        .resolver(StandardTags.decorations())
                        .resolver(StandardTags.rainbow())
                        .resolver(StandardTags.clickEvent())
                        .resolver(StandardTags.hoverEvent())
                        .resolver(StandardTags.insertion())
                        .resolver(StandardTags.font())
                        .resolvers(StandardTags.reset())
                        .build()
                )
                .build();
        return msg.deserialize(message);
    }

    public static Component blank() {
        return Component.text(" ");
    }

    /**
     * @param message The message to convert to Kyori.
     * @return The translated Component.
     */
    public static String convertLegacy(String message) {
        for (String key : legacyColors.keySet()) {
            if (message.contains(key)) {
                message = message.replace(key, legacyColors.get(key));
            }
        }
        return message;
    }

    /**
     * @param msg1 The string or Component to combine with msg2
     * @param msg2 The string or Component to combine with msg1
     * @return The combined Component
     */
    public static Component combine(Object msg1, Object msg2) {
        if(msg1 instanceof Component) {
            if(msg2 instanceof Component) {
                return ((Component) msg1).append((Component) msg2);
            }
            else if(msg2 instanceof String) {
                return ((Component) msg1).append(format((String) msg2));
            }
        }
        else if(msg1 instanceof String) {
            if(msg2 instanceof Component) {
                return format((String) msg1).append((Component) msg2);
            }
            else if(msg2 instanceof String) {
                return format((String) msg1).append(format((String) msg2));
            }
        }
        // should never be hit
        return null;
    }
}
