package io.github.hyjn.nexori.plugin.api.minigame;

import java.util.List;
import java.util.UUID;

/**
 * Public runtime snapshot for one active Nexori match.
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
