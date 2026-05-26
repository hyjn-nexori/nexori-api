package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Result states returned when a rules mod updates runtime AFK detection policies.
 */
public enum NexoriSetAfkDetectionPolicyStatus {
    UPDATED,
    CLEARED,
    MATCH_MISSING,
    PLAYER_MISSING,
    MATCH_ALREADY_COMPLETED,
    INVALID_POLICY,
    NOT_SUPPORTED
}
