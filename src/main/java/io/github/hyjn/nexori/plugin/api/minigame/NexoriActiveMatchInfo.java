package io.github.hyjn.nexori.plugin.api.minigame;

import java.util.List;
import java.util.UUID;

/**
 * Public runtime snapshot for one Nexori-managed match.
 *
 * <p>Use this as a read-only view of the match state Nexori exposes to a rules mod. Lists are
 * normalized by Nexori and should not be mutated by callers.</p>
 *
 * @param matchId Nexori's local match id.
 * @param queueId queue id that produced the match, when known.
 * @param arenaId arena id that owns the match, when known.
 * @param assignmentId backend assignment id or local assignment id, when known.
 * @param externalMatchId backend match id, when this match came from a backend assignment.
 * @param rulesEngineId rules engine id that should control gameplay for this match.
 * @param matchResolutionTriggerId legacy compatibility field; use {@code rulesEngineId} instead.
 * @param expectedPlayerUuids players Nexori expected during the initial placement phase.
 * @param arrivedPlayerUuids players that reached the arena runtime.
 * @param activePlayerUuids players currently considered active in the match runtime.
 * @param eliminatedPlayerUuids players no longer active because Nexori recorded an outcome.
 * @param spectatorPlayerUuids players marked as logical spectators.
 * @param afkPlayerUuids players currently marked AFK by Nexori's local AFK runtime state.
 * @param requiredResultPlayerUuids official player set that must have outcomes before final result submission.
 * @param playerOutcomes outcome state currently stored by rules mod calls.
 * @param expectedPlayerCount expected initial player count.
 * @param completedAtEpochMs completion timestamp, or {@code 0} while incomplete.
 * @param resultSubmittedAtEpochMs final result submission timestamp, or {@code 0} before submission.
 */
public record NexoriActiveMatchInfo(
    String matchId,
    String queueId,
    String arenaId,
    String assignmentId,
    String externalMatchId,
    String rulesEngineId,
    /**
     * Legacy field kept only for compatibility.
     * <p>
     * Nexori no longer supports built-in match resolution triggers. The legacy value {@code "none"}
     * is kept only for older mods that treated it as manual/external resolution. Use
     * {@link #rulesEngineId()} to identify the external minigame/rules engine. External minigames
     * should resolve matches through the public API. This member will be removed in a future API
     * cleanup.
     */
    @Deprecated(forRemoval = true)
    String matchResolutionTriggerId,
    List<UUID> expectedPlayerUuids,
    List<UUID> arrivedPlayerUuids,
    List<UUID> activePlayerUuids,
    List<UUID> eliminatedPlayerUuids,
    List<UUID> spectatorPlayerUuids,
    List<UUID> afkPlayerUuids,
    List<UUID> requiredResultPlayerUuids,
    List<NexoriPlayerOutcomeState> playerOutcomes,
    int expectedPlayerCount,
    long completedAtEpochMs,
    long resultSubmittedAtEpochMs
) {

    public NexoriActiveMatchInfo(
        String matchId,
        String queueId,
        String arenaId,
        String assignmentId,
        String externalMatchId,
        String rulesEngineId,
        String matchResolutionTriggerId,
        List<UUID> expectedPlayerUuids,
        List<UUID> arrivedPlayerUuids,
        List<UUID> activePlayerUuids,
        List<UUID> eliminatedPlayerUuids,
        List<UUID> spectatorPlayerUuids,
        List<UUID> requiredResultPlayerUuids,
        List<NexoriPlayerOutcomeState> playerOutcomes,
        int expectedPlayerCount,
        long completedAtEpochMs,
        long resultSubmittedAtEpochMs
    ) {
        this(
            matchId,
            queueId,
            arenaId,
            assignmentId,
            externalMatchId,
            rulesEngineId,
            matchResolutionTriggerId,
            expectedPlayerUuids,
            arrivedPlayerUuids,
            activePlayerUuids,
            eliminatedPlayerUuids,
            spectatorPlayerUuids,
            List.of(),
            requiredResultPlayerUuids,
            playerOutcomes,
            expectedPlayerCount,
            completedAtEpochMs,
            resultSubmittedAtEpochMs
        );
    }

    public NexoriActiveMatchInfo {
        expectedPlayerUuids = copyUuids(expectedPlayerUuids);
        arrivedPlayerUuids = copyUuids(arrivedPlayerUuids);
        activePlayerUuids = copyUuids(activePlayerUuids);
        eliminatedPlayerUuids = copyUuids(eliminatedPlayerUuids);
        spectatorPlayerUuids = copyUuids(spectatorPlayerUuids);
        afkPlayerUuids = copyUuids(afkPlayerUuids);
        requiredResultPlayerUuids = copyUuids(requiredResultPlayerUuids);
        playerOutcomes = playerOutcomes == null || playerOutcomes.isEmpty()
            ? List.of()
            : List.copyOf(playerOutcomes);
    }

    /**
     * Legacy field kept only for compatibility.
     * <p>
     * Nexori no longer supports built-in match resolution triggers. The legacy value {@code "none"}
     * is kept only for older mods that treated it as manual/external resolution. Use
     * {@link #rulesEngineId()} to identify the external minigame/rules engine. External minigames
     * should resolve matches through the public API. This member will be removed in a future API
     * cleanup.
     *
     * @return the legacy trigger id, normally {@code "none"} for compatibility.
     */
    @Deprecated(forRemoval = true)
    public String matchResolutionTriggerId() {
        return matchResolutionTriggerId;
    }

    private static List<UUID> copyUuids(List<UUID> rawUuids) {
        if (rawUuids == null || rawUuids.isEmpty()) {
            return List.of();
        }
        return rawUuids.stream()
            .filter(uuid -> uuid != null)
            .distinct()
            .toList();
    }
}
