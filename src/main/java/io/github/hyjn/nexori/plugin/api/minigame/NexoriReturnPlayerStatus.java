package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Result states returned when a rules mod schedules a player return.
 */
public enum NexoriReturnPlayerStatus {
    /** The player was scheduled for return-to-lobby. */
    SCHEDULED,
    /** Nexori does not know the requested match id. */
    MATCH_MISSING,
    /** The player is not part of the active match runtime. */
    PLAYER_MISSING,
    /** The requested delay is outside the supported range. */
    INVALID_DELAY,
    /** The request did not provide a usable reason. */
    INVALID_REASON
}
