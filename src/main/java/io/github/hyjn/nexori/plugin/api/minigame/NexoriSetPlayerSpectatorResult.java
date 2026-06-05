package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Public result returned by {@link NexoriMinigameApi#setPlayerSpectator(String, UUID, boolean, String)}.
 *
 * @param status spectator update status.
 * @param matchId Nexori local match id from the request.
 * @param playerUuid player whose spectator state was requested.
 * @param spectator requested logical spectator state.
 * @param message human-readable result details.
 */
public record NexoriSetPlayerSpectatorResult(
    NexoriSetPlayerSpectatorStatus status,
    String matchId,
    @Nullable UUID playerUuid,
    boolean spectator,
    String message
) {
}
