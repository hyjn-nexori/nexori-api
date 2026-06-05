package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Public backend reporting states returned separately from local match completion.
 */
public enum NexoriBackendReportStatus {
    /** Backend result reporting was enabled and the report was queued locally. */
    QUEUED,
    /** Backend result reporting is disabled for this server. */
    DISABLED,
    /** Nexori accepted local completion, but the match has no backend match id to report against. */
    EXTERNAL_MATCH_MISSING,
    /** Nexori accepted local completion, but failed to store the pending backend report. */
    STORE_FAILED,
    /** A backend report was already recorded for this match. */
    ALREADY_SUBMITTED,
    /** Nexori detected conflicting duplicate backend report data. */
    DUPLICATE_CONFLICT,
    /** Backend reporting was not attempted because local completion did not proceed. */
    NOT_ATTEMPTED
}
