package io.github.hyjn.nexori.plugin.api.minigame;

import java.util.UUID;

/**
 * Accumulated player outcome currently stored inside an active match.
 *
 * @param playerUuid player the outcome belongs to.
 * @param outcome outcome currently stored for the player.
 * @param reason public reason supplied when the outcome was stored.
 * @param updatedAtEpochMs timestamp of the latest outcome update.
 */
public record NexoriPlayerOutcomeState(
    UUID playerUuid,
    NexoriMatchResultPlayerOutcome outcome,
    String reason,
    long updatedAtEpochMs
) {
}
