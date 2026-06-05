package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Result states returned when a rules mod stores one player's runtime outcome.
 */
public enum NexoriSetPlayerOutcomeStatus {
    /** Nexori stored or replaced the player's outcome. */
    UPDATED,
    /** Nexori does not know the requested match id. */
    MATCH_MISSING,
    /** The requested player is not in the official result requirement set for the match. */
    PLAYER_MISSING,
    /** The match already completed and no longer accepts outcome changes. */
    MATCH_ALREADY_COMPLETED,
    /** The request did not provide a usable outcome. */
    INVALID_OUTCOME,
    /** The request did not provide a usable reason. */
    INVALID_REASON
}
