package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Public source for a Nexori AFK state transition.
 */
public enum NexoriAfkActivitySource {
    UNKNOWN,
    IDLE_TIMEOUT,
    PLAYER_INPUT,
    INVENTORY_PACKET,
    POLICY_CHANGE
}
