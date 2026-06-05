package io.github.hyjn.nexori.plugin.api.minigame;

import java.util.UUID;

/**
 * Request to override the AFK detection policy for one player inside one active match runtime.
 *
 * @param matchId Nexori local match id.
 * @param playerUuid player receiving the policy override.
 * @param policy policy to store for the player.
 */
public record NexoriSetPlayerAfkDetectionPolicyRequest(
    String matchId,
    UUID playerUuid,
    NexoriAfkDetectionPolicy policy
) {
}
