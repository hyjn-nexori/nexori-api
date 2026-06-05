package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Result states returned after one explicit admission close request.
 */
public enum NexoriCloseMatchAdmissionStatus {
    /** Admission was open and is now closed locally. */
    CLOSED,
    /** Admission was already closed before this request. */
    ALREADY_CLOSED,
    /** Nexori does not know the requested match id. */
    MATCH_MISSING,
    /** The match is not backed by backend admission reporting. */
    MATCH_NOT_BACKEND_DRIVEN,
    /** The request did not provide a usable close reason. */
    INVALID_REASON,
    /** Match state/admission reporting is disabled on this server. */
    REPORTING_DISABLED
}
