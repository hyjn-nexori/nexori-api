package io.github.hyjn.nexori.plugin.api.minigame;

import java.util.UUID;

/**
 * Request to override the AFK detection policy for one player inside one active match runtime.
 */
public record NexoriSetPlayerAfkDetectionPolicyRequest(
    String matchId,
    UUID playerUuid,
    NexoriAfkDetectionPolicy policy
) {
}
