package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Result states returned by {@link NexoriMinigameApi#setPlayerAfk(NexoriSetPlayerAfkRequest)}.
 */
public enum NexoriSetPlayerAfkStatus {
    UPDATED,
    UNCHANGED,
    MATCH_MISSING,
    PLAYER_MISSING,
    MATCH_ALREADY_COMPLETED,
    INVALID_REQUEST,
    NOT_SUPPORTED
}
