package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Result states returned when a rules mod updates runtime AFK detection policies.
 */
public enum NexoriSetAfkDetectionPolicyStatus {
    /** The runtime AFK policy override was stored. */
    UPDATED,
    /** The runtime AFK policy override was cleared. */
    CLEARED,
    /** Nexori does not know the requested match id. */
    MATCH_MISSING,
    /** The requested player is not part of the active match runtime. */
    PLAYER_MISSING,
    /** The match already completed and no longer accepts policy changes. */
    MATCH_ALREADY_COMPLETED,
    /** The request did not provide a usable policy. */
    INVALID_POLICY,
    /** This implementation does not support runtime AFK policy overrides. */
    NOT_SUPPORTED
}
