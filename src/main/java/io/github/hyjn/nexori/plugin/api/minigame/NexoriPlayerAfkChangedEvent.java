package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * Immutable public event for a Nexori local AFK state transition.
 *
 * <p>{@code idleMs} means the player idle duration at the transition point:
 * for {@code afk=true}, how long the player had been inactive before becoming AFK;
 * for {@code afk=false}, how long the player had been inactive before becoming active again.</p>
 *
 * @param matchId Nexori local match id.
 * @param queueId queue id that produced the match, when known.
 * @param arenaId arena id that owns the match, when known.
 * @param rulesEngineId rules engine id that owns the match.
 * @param playerUuid player whose AFK state changed.
 * @param playerName player name known by Nexori at the transition point.
 * @param afk {@code true} when the player became AFK; {@code false} when they became active.
 * @param changedAtEpochMs transition timestamp.
 * @param idleMs local idle duration at the transition point.
 * @param source public source of the transition.
 */
public record NexoriPlayerAfkChangedEvent(
    String matchId,
    String queueId,
    String arenaId,
    String rulesEngineId,
    UUID playerUuid,
    String playerName,
    boolean afk,
    long changedAtEpochMs,
    long idleMs,
    NexoriAfkActivitySource source
) {

    public NexoriPlayerAfkChangedEvent {
        matchId = normalize(matchId);
        queueId = normalize(queueId);
        arenaId = normalize(arenaId);
        rulesEngineId = normalize(rulesEngineId);
        if (playerUuid == null) {
            throw new IllegalArgumentException("Player UUID cannot be null.");
        }
        playerName = normalize(playerName);
        changedAtEpochMs = Math.max(0L, changedAtEpochMs);
        idleMs = Math.max(0L, idleMs);
        source = source == null ? NexoriAfkActivitySource.UNKNOWN : source;
    }

    @Nonnull
    private static String normalize(String rawValue) {
        if (rawValue == null) {
            return "";
        }
        String normalized = rawValue.trim();
        return normalized.isBlank() ? "" : normalized;
    }
}
