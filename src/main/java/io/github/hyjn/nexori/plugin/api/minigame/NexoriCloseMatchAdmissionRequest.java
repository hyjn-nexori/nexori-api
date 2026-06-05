package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nullable;

/**
 * Request to close match admission/backfill visibility for one active backend-driven match.
 *
 * <p>Use this when a rules mod reaches a point where additional players should no longer be
 * admitted to the match. The request affects admission reporting; it does not submit the final
 * result or return players to lobby.</p>
 *
 * @param matchId Nexori local match id.
 * @param reason public reason for closing admission.
 * @param message optional human-readable context for logs and diagnostics.
 */
public record NexoriCloseMatchAdmissionRequest(
    String matchId,
    NexoriCloseMatchAdmissionReason reason,
    @Nullable String message
) {
}
