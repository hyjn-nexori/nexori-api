package io.github.hyjn.nexori.plugin.api.minigame;

import java.util.List;
import java.util.UUID;

/**
 * Official result requirement set for one active match.
 *
 * <p>{@link #requiredPlayerUuids()} is the source of truth for final outcomes. A rules mod may
 * keep its own gameplay participant list, but before submitting the final result it should build
 * outcomes from this set or validate its local list against it.</p>
 *
 * @param matchId Nexori's local match id.
 * @param queueId queue id that produced the match, when known.
 * @param arenaId arena id that owns the match, when known.
 * @param requiredPlayerUuids official players that require a final outcome.
 * @param expectedPlayerUuids players Nexori expected during initial placement.
 * @param arrivedPlayerUuids players that reached the arena runtime.
 * @param activePlayerUuids players currently considered active in the match runtime.
 * @param eliminatedPlayerUuids players with a stored non-active outcome.
 */
public record NexoriMatchResultRequirements(
    String matchId,
    String queueId,
    String arenaId,
    List<UUID> requiredPlayerUuids,
    List<UUID> expectedPlayerUuids,
    List<UUID> arrivedPlayerUuids,
    List<UUID> activePlayerUuids,
    List<UUID> eliminatedPlayerUuids
) {
}
