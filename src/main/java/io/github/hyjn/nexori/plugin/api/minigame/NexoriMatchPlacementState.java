package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Public snapshot of Nexori's initial placement and start-gate state for one active match.
 * {@code placementComplete} means every expected initial player was placed. {@code startGateOpen}
 * means gameplay may start; that can happen with a partial initial roster after the configured
 * placement window expires and {@code minimumInitialPlayers} has been met.
 */
public record NexoriMatchPlacementState(
    int expectedPlayers,
    int arrivedPlayers,
    int placedPlayers,
    boolean placementComplete,
    int minimumInitialPlayers,
    boolean initialPlacementWindowOpen,
    long initialPlacementWindowStartedAtEpochMs,
    long initialPlacementWindowExpiresAtEpochMs,
    long initialPlacementWindowClosedAtEpochMs,
    String initialPlacementWindowCloseReason,
    boolean startGateOpen,
    long startGateOpenedAtEpochMs,
    String startGateOpenReason
) {

    public NexoriMatchPlacementState(
        int expectedPlayers,
        int arrivedPlayers,
        int placedPlayers,
        boolean placementComplete
    ) {
        this(
            expectedPlayers,
            arrivedPlayers,
            placedPlayers,
            placementComplete,
            0,
            false,
            0L,
            0L,
            0L,
            "",
            false,
            0L,
            ""
        );
    }

    public NexoriMatchPlacementState {
        expectedPlayers = Math.max(0, expectedPlayers);
        arrivedPlayers = Math.max(0, arrivedPlayers);
        placedPlayers = Math.max(0, placedPlayers);
        minimumInitialPlayers = Math.max(0, minimumInitialPlayers);
        initialPlacementWindowStartedAtEpochMs = Math.max(0L, initialPlacementWindowStartedAtEpochMs);
        initialPlacementWindowExpiresAtEpochMs = Math.max(0L, initialPlacementWindowExpiresAtEpochMs);
        initialPlacementWindowClosedAtEpochMs = Math.max(0L, initialPlacementWindowClosedAtEpochMs);
        initialPlacementWindowCloseReason = normalize(initialPlacementWindowCloseReason);
        startGateOpenedAtEpochMs = Math.max(0L, startGateOpenedAtEpochMs);
        startGateOpenReason = normalize(startGateOpenReason);
    }

    private static String normalize(String rawValue) {
        if (rawValue == null) {
            return "";
        }
        String normalized = rawValue.trim();
        return normalized.isBlank() ? "" : normalized;
    }
}
