package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

/**
 * Immutable public snapshot for a Nexori match lifecycle event.
 *
 * <p>The event describes the match from Nexori's public point of view at the time the callback
 * was dispatched. Rules mods should use {@link #rulesEngineId()} to confirm ownership and
 * {@link #requiredResultPlayerUuids()} as part of final result preparation.</p>
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
 * @param spectatorPlayerUuids players marked as logical spectators.
 * @param requiredResultPlayerUuids official player set that must have outcomes before final result submission.
 * @param placementState current initial placement/start gate snapshot.
 * @param reason public reason associated with this lifecycle event.
 * @param createdAtEpochMs match creation timestamp.
 * @param eventAtEpochMs callback/event timestamp.
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
