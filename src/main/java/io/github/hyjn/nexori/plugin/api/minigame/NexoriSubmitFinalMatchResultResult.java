package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Result returned after Nexori attempts final local match completion and optional backend reporting.
 *
 * <p>{@link #matchStatus()} describes whether Nexori accepted the result locally.
 * {@link #backendReportStatus()} describes what happened to the optional backend result report.</p>
 *
 * @param matchStatus local final result acceptance status.
 * @param backendReportStatus backend reporting status, separate from local completion.
 * @param resultId local result id when completion was accepted.
 * @param message human-readable result details.
 */
public record NexoriSubmitFinalMatchResultResult(
    NexoriMatchCompletionStatus matchStatus,
    NexoriBackendReportStatus backendReportStatus,
    String resultId,
    String message
) {
}
