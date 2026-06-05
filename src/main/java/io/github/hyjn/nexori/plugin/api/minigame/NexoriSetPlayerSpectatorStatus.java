package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Result states returned when a rules mod updates logical spectator state.
 */
public enum NexoriSetPlayerSpectatorStatus {
    /** Nexori stored the player's logical spectator state. */
    UPDATED,
    /** Nexori does not know the requested match id. */
    MATCH_MISSING,
    /** The requested player is not part of the active match runtime. */
    PLAYER_MISSING,
    /** The match already completed and no longer accepts spectator changes. */
    MATCH_ALREADY_COMPLETED,
    /** The request did not provide a usable reason. */
    INVALID_REASON
}
