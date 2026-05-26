package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Public result returned by runtime AFK detection policy override APIs.
 */
public record NexoriSetAfkDetectionPolicyResult(
    NexoriSetAfkDetectionPolicyStatus status,
    String matchId,
    @Nullable UUID playerUuid,
    @Nullable NexoriAfkDetectionPolicy policy,
    String message
) {
}
