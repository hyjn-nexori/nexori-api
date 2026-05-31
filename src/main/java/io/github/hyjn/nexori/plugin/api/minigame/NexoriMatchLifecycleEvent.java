package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

/**
 * Immutable public snapshot for a Nexori match lifecycle event.
 */
public record NexoriMatchLifecycleEvent(
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
    List<UUID> spectatorPlayerUuids,
    List<UUID> requiredResultPlayerUuids,
    NexoriMatchPlacementState placementState,
    String reason,
    long createdAtEpochMs,
    long eventAtEpochMs
) {

    public NexoriMatchLifecycleEvent {
        matchId = normalize(matchId);
        queueId = normalize(queueId);
        arenaId = normalize(arenaId);
        assignmentId = normalize(assignmentId);
        externalMatchId = normalize(externalMatchId);
        rulesEngineId = normalize(rulesEngineId);
        matchResolutionTriggerId = normalize(matchResolutionTriggerId);
        expectedPlayerUuids = copyUuids(expectedPlayerUuids);
        arrivedPlayerUuids = copyUuids(arrivedPlayerUuids);
        activePlayerUuids = copyUuids(activePlayerUuids);
        spectatorPlayerUuids = copyUuids(spectatorPlayerUuids);
        requiredResultPlayerUuids = copyUuids(requiredResultPlayerUuids);
        reason = normalize(reason);
        createdAtEpochMs = Math.max(0L, createdAtEpochMs);
        eventAtEpochMs = Math.max(0L, eventAtEpochMs);
    }

    @Nonnull
    private static List<UUID> copyUuids(List<UUID> rawUuids) {
        if (rawUuids == null || rawUuids.isEmpty()) {
            return List.of();
        }
        return rawUuids.stream()
            .filter(uuid -> uuid != null)
            .toList();
    }

    @Nonnull
    private static String normalize(String rawValue) {
        if (rawValue == null) {
            return "";
        }
        String normalized = rawValue.trim();
        return normalized.isBlank() ? "" : normalized;
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
}
