package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * Immutable public event for a Nexori AFK state transition.
 *
 * <p>{@code idleMs} means the player idle duration at the transition point:
 * for {@code afk=true}, how long the player had been inactive before becoming AFK;
 * for {@code afk=false}, how long the player had been inactive before becoming active again.</p>
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
