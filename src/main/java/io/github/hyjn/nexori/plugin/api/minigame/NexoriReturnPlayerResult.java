package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Public result returned by {@link NexoriMinigameApi#returnPlayerToLobby(String, UUID, int, String)}.
 *
 * @param status scheduling outcome.
 * @param matchId Nexori local match id from the request.
 * @param playerUuid player scheduled for return, when available.
 * @param returnAtEpochMs timestamp when the return should run, or {@code 0} if not scheduled.
 * @param message human-readable result details.
 */
public record NexoriReturnPlayerResult(
    NexoriReturnPlayerStatus status,
    String matchId,
    @Nullable UUID playerUuid,
    long returnAtEpochMs,
    String message
) {
}
