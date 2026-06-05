package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Public match completion states returned when submitting final match results.
 */
public enum NexoriMatchCompletionStatus {
    /** Nexori accepted the final result locally. */
    ACCEPTED,
    /** This match already has a submitted final result. */
    ALREADY_SUBMITTED,
    /** Nexori does not know the requested match id. */
    MATCH_MISSING,
    /** The submitted final result failed local validation. */
    INVALID_RESULT
}
