package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Player outcomes accepted by Nexori's modern minigame result commands.
 */
public enum NexoriMatchResultPlayerOutcome {
    /** Player won the match. */
    WIN,
    /** Player lost the match. */
    LOSS,
    /** Player left or disconnected before normal completion. */
    DISCONNECTED,
    /** Player should receive no win/loss result for this match. */
    NO_CONTEST
}
