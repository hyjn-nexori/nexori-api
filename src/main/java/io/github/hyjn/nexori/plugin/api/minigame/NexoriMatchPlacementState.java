package io.github.hyjn.nexori.plugin.api.minigame;

/**
 * Public snapshot of Nexori's initial placement and start-gate state for one active match.
 *
 * <p>{@code placementComplete} means every expected initial player was placed.
 * {@code startGateOpen} means gameplay may start; that can happen with a partial initial roster
 * after the configured placement window expires and {@code minimumInitialPlayers} has been met.</p>
 *
 * @param expectedPlayers expected initial players.
 * @param arrivedPlayers expected players that arrived at the arena runtime.
 * @param placedPlayers expected players with confirmed or fallback placement.
 * @param placementComplete whether every expected initial player has placement recorded.
 * @param minimumInitialPlayers minimum placed player count required to open the start gate.
 * @param initialPlacementWindowOpen whether the initial placement window is still open.
 * @param initialPlacementWindowStartedAtEpochMs placement window start timestamp, or {@code 0}.
 * @param initialPlacementWindowExpiresAtEpochMs placement window expiry timestamp, or {@code 0}.
 * @param initialPlacementWindowClosedAtEpochMs placement window close timestamp, or {@code 0}.
 * @param initialPlacementWindowCloseReason public reason the placement window closed.
 * @param startGateOpen whether rules mods may start normal gameplay.
 * @param startGateOpenedAtEpochMs start gate open timestamp, or {@code 0}.
 * @param startGateOpenReason public reason the start gate opened.
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
