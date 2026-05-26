package io.github.hyjn.nexori.plugin.api.minigame;

import java.util.UUID;

/**
 * Request to set the AFK state of one player inside one active match.
 *
 * <p>Nexori maintains a single public AFK state per player. If Nexori's built-in detection is
 * still enabled for the match, player input can continue to change that same state. Minigames
 * that want full control over AFK detection should disable the built-in policy first via the
 * runtime AFK policy APIs, then use this request to report AFK state from their own logic.</p>
 */
public record NexoriSetPlayerAfkRequest(
    String matchId,
    UUID playerUuid,
    boolean afk,
    String reason
) {
}
