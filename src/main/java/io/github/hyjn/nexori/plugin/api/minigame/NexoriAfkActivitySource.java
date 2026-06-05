package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Public source for a Nexori AFK state transition.
 */
public enum NexoriAfkActivitySource {
    /** The transition source was not specified. */
    UNKNOWN,
    /** Nexori's local inactivity timer marked the player AFK. */
    IDLE_TIMEOUT,
    /** Player gameplay input marked the player active. */
    PLAYER_INPUT,
    /** Player inventory activity marked the player active. */
    INVENTORY_PACKET,
    /** A runtime AFK policy change recalculated the player state. */
    POLICY_CHANGE,
    /** A public API call set or cleared the player AFK state. */
    EXTERNAL_API
}
