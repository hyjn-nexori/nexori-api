package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * The type of a backend AFK continuation decision for one active match.
 *
 * <p>{@link #PENDING} and {@link #UNAVAILABLE} are local Nexori states.
 * The backend only ever replies with {@link #CONTINUE} or {@link #CANCEL}.</p>
 */
public enum NexoriAfkContinuationDecisionType {

    /** The backend has decided the match may continue despite the AFK player. */
    CONTINUE,

    /**
     * The backend has decided the match should be cancelled.
     *
     * <p>Sticky: once set for a match, subsequent {@link #CONTINUE} responses do not overwrite it.</p>
     */
    CANCEL,

    /** A backend check is currently in flight; no decision has arrived yet. */
    PENDING,

    /**
     * AFK continuation check is disabled, not configured, or the most recent request failed.
     *
     * <p>A future AFK event may trigger a new check and replace this state.</p>
     */
    UNAVAILABLE
}
