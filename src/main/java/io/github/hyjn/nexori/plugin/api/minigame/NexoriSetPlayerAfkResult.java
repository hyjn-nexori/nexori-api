package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Public result returned by {@link NexoriMinigameApi#setPlayerAfk(NexoriSetPlayerAfkRequest)}.
 */
public record NexoriSetPlayerAfkResult(
    NexoriSetPlayerAfkStatus status,
    String matchId,
    @Nullable UUID playerUuid,
    boolean afk,
    String message
) {
}
