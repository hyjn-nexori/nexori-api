package io.github.hyjn.nexori.plugin.api.minigame;

import java.util.UUID;

/**
 * Request to set the AFK state of one player inside one active match.
 *
 * <p>Nexori maintains a single public AFK state per player. If Nexori's built-in detection is
 * still enabled for the match, player input can continue to change that same state. Minigames
 * that want full control over AFK detection should disable the built-in policy first via the
 * runtime AFK policy APIs, then use this request to report AFK state from their own logic.</p>
 *
 * <p>{@code showHud} controls whether the Nexori AFK HUD is shown to the player when
 * {@code afk=true}. Set to {@code false} when the minigame handles its own AFK feedback
 * and the Nexori overlay would be redundant or unwanted. Defaults to {@code true}.</p>
 *
 * @param matchId Nexori local match id.
 * @param playerUuid player whose AFK state should change.
 * @param afk {@code true} to mark AFK; {@code false} to mark active.
 * @param reason short public reason for diagnostics.
 * @param showHud whether Nexori should show its AFK HUD when marking the player AFK.
 */
public record NexoriSetPlayerAfkRequest(
    String matchId,
    UUID playerUuid,
    boolean afk,
    String reason,
    boolean showHud
) {
    /** Convenience constructor — {@code showHud} defaults to {@code true}. */
    public NexoriSetPlayerAfkRequest(String matchId, UUID playerUuid, boolean afk, String reason) {
        this(matchId, playerUuid, afk, reason, true);
    }
}
