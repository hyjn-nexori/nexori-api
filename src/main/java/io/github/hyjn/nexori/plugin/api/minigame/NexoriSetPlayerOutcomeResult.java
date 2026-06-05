package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Public result returned by {@link NexoriMinigameApi#setPlayerOutcome(String, UUID, NexoriMatchResultPlayerOutcome, String)}.
 *
 * @param status outcome update status.
 * @param matchId Nexori local match id from the request.
 * @param playerUuid player whose outcome was requested.
 * @param outcome outcome stored or requested for the player.
 * @param message human-readable result details.
 */
public record NexoriSetPlayerOutcomeResult(
    NexoriSetPlayerOutcomeStatus status,
    String matchId,
    @Nullable UUID playerUuid,
    @Nullable NexoriMatchResultPlayerOutcome outcome,
    String message
) {
}
