package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Result states returned by {@link NexoriMinigameApi#setPlayerAfk(NexoriSetPlayerAfkRequest)}.
 */
public enum NexoriSetPlayerAfkStatus {
    /** Nexori changed the player's public AFK state. */
    UPDATED,
    /** The player was already in the requested AFK state. */
    UNCHANGED,
    /** Nexori does not know the requested match id. */
    MATCH_MISSING,
    /** The requested player is not part of the active match runtime. */
    PLAYER_MISSING,
    /** The match already completed and no longer accepts AFK state updates. */
    MATCH_ALREADY_COMPLETED,
    /** The request was missing required data. */
    INVALID_REQUEST,
    /** This implementation does not support external AFK state updates. */
    NOT_SUPPORTED
}
