package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Request to override the AFK detection policy for one active match runtime.
 *
 * @param matchId Nexori local match id.
 * @param policy policy to store for the match.
 */
public record NexoriSetMatchAfkDetectionPolicyRequest(
    String matchId,
    NexoriAfkDetectionPolicy policy
) {
}
