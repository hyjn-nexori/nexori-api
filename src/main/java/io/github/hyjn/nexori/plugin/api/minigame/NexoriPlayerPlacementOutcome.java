package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Public outcome for one player's initial Nexori placement phase.
 */
public enum NexoriPlayerPlacementOutcome {
    /** The player was placed into the intended match runtime. */
    CONFIRMED,
    /** Nexori used a fallback placement path. */
    FALLBACK
}
