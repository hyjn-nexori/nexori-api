package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Public reasons a rules mod can use to explicitly close backend admission reporting.
 */
public enum NexoriCloseMatchAdmissionReason {
    /** The rules mod explicitly closed admission. */
    MOD_REQUEST,
    /** Gameplay reached a phase where new players should no longer join. */
    GAME_PHASE_LOCKED,
    /** The rules mod locked the match roster. */
    ROSTER_LOCKED,
    /** An administrator or operator action forced admission closed. */
    ADMIN_FORCED
}
