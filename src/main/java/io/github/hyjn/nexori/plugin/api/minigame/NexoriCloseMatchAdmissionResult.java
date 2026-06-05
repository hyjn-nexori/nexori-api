package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Result returned by {@link NexoriMinigameApi#closeMatchAdmission(NexoriCloseMatchAdmissionRequest)}.
 *
 * @param status close request outcome.
 * @param matchId Nexori local match id from the request.
 * @param closedLocally whether Nexori changed local admission state during this call.
 * @param message human-readable result details.
 */
public record NexoriCloseMatchAdmissionResult(
    NexoriCloseMatchAdmissionStatus status,
    String matchId,
    boolean closedLocally,
    String message
) {
}
