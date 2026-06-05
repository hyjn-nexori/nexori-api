package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Public result returned by {@link NexoriMinigameApi#setPlayerAfk(NexoriSetPlayerAfkRequest)}.
 *
 * @param status AFK state update outcome.
 * @param matchId Nexori local match id from the request.
 * @param playerUuid player whose AFK state was requested.
 * @param afk resulting requested AFK value.
 * @param message human-readable result details.
 */
public record NexoriSetPlayerAfkResult(
    NexoriSetPlayerAfkStatus status,
    String matchId,
    @Nullable UUID playerUuid,
    boolean afk,
    String message
) {
}
