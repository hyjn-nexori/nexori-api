package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Request to override the AFK detection policy for one active match runtime.
 */
public record NexoriSetMatchAfkDetectionPolicyRequest(
    String matchId,
    NexoriAfkDetectionPolicy policy
) {
}
