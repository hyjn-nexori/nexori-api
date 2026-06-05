package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Public result returned by runtime AFK detection policy override APIs.
 *
 * @param status policy update outcome.
 * @param matchId Nexori local match id from the request.
 * @param playerUuid player override target, or {@code null} for match-level overrides.
 * @param policy normalized policy stored by Nexori, or {@code null} when cleared or unavailable.
 * @param message human-readable result details.
 */
public record NexoriSetAfkDetectionPolicyResult(
    NexoriSetAfkDetectionPolicyStatus status,
    String matchId,
    @Nullable UUID playerUuid,
    @Nullable NexoriAfkDetectionPolicy policy,
    String message
) {
}
