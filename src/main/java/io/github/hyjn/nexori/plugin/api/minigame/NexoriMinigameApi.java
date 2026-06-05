package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;

/**
 * Public Nexori minigame integration surface intended for other mods.
 *
 * <p>Third-party minigames compile against this API and call it at runtime through the installed
 * Nexori plugin. The API exposes lifecycle callbacks, public match snapshots, player state
 * operations, local AFK detection controls, result requirements, final result submission, and
 * return-to-lobby commands.</p>
 */
public interface NexoriMinigameApi {

    /**
     * Registers a listener for Nexori match lifecycle callbacks for one rules engine id.
     *
     * <p>The listener receives only callbacks for matches owned by the supplied rules engine id.
     * Close the returned registration during mod shutdown or adapter cleanup.</p>
     *
     * @param rulesEngineId rules engine id owned by the caller.
     * @param listener callback object.
     * @return registration handle used to unregister the listener.
     */
    @Nonnull
    NexoriListenerRegistration registerMatchLifecycleListener(
        @Nonnull String rulesEngineId,
        @Nonnull NexoriMatchLifecycleListener listener
    );

    /**
     * Registers a listener for Nexori AFK activity callbacks for one rules engine id.
     *
     * @param rulesEngineId rules engine id owned by the caller.
     * @param listener callback object for local AFK transitions.
     * @return registration handle used to unregister the listener.
     */
    @Nonnull
    default NexoriListenerRegistration registerAfkActivityListener(
        @Nonnull String rulesEngineId,
        @Nonnull NexoriAfkActivityListener listener
    ) {
        return () -> {
        };
    }

    /**
     * Overrides the AFK detection policy for one active match runtime without changing persistent arena config.
     *
     * @param request match policy override request.
     * @return result describing whether the override was stored.
     */
    @Nonnull
    default NexoriSetAfkDetectionPolicyResult setMatchAfkDetectionPolicy(
        @Nonnull NexoriSetMatchAfkDetectionPolicyRequest request
    ) {
        return new NexoriSetAfkDetectionPolicyResult(
            NexoriSetAfkDetectionPolicyStatus.NOT_SUPPORTED,
            request == null ? "" : request.matchId(),
            null,
            request == null ? null : NexoriAfkDetectionPolicy.normalize(request.policy()),
            "Runtime AFK policy overrides are not supported by this NexoriMinigameApi implementation."
        );
    }

    /**
     * Clears the AFK detection policy override for one active match runtime.
     *
     * @param matchId Nexori local match id.
     * @return result describing whether the override was cleared.
     */
    @Nonnull
    default NexoriSetAfkDetectionPolicyResult clearMatchAfkDetectionPolicy(@Nonnull String matchId) {
        return new NexoriSetAfkDetectionPolicyResult(
            NexoriSetAfkDetectionPolicyStatus.NOT_SUPPORTED,
            matchId,
            null,
            null,
            "Runtime AFK policy overrides are not supported by this NexoriMinigameApi implementation."
        );
    }

    /**
     * Overrides the AFK detection policy for one player inside one active match runtime.
     *
     * @param request player policy override request.
     * @return result describing whether the override was stored.
     */
    @Nonnull
    default NexoriSetAfkDetectionPolicyResult setPlayerAfkDetectionPolicy(
        @Nonnull NexoriSetPlayerAfkDetectionPolicyRequest request
    ) {
        return new NexoriSetAfkDetectionPolicyResult(
            NexoriSetAfkDetectionPolicyStatus.NOT_SUPPORTED,
            request == null ? "" : request.matchId(),
            request == null ? null : request.playerUuid(),
            request == null ? null : NexoriAfkDetectionPolicy.normalize(request.policy()),
            "Runtime AFK policy overrides are not supported by this NexoriMinigameApi implementation."
        );
    }

    /**
     * Clears the AFK detection policy override for one player inside one active match runtime.
     *
     * @param matchId Nexori local match id.
     * @param playerUuid player whose override should be cleared.
     * @return result describing whether the override was cleared.
     */
    @Nonnull
    default NexoriSetAfkDetectionPolicyResult clearPlayerAfkDetectionPolicy(
        @Nonnull String matchId,
        @Nonnull UUID playerUuid
    ) {
        return new NexoriSetAfkDetectionPolicyResult(
            NexoriSetAfkDetectionPolicyStatus.NOT_SUPPORTED,
            matchId,
            playerUuid,
            null,
            "Runtime AFK policy overrides are not supported by this NexoriMinigameApi implementation."
        );
    }

    /**
     * Sets or clears the AFK state of one player inside one active match.
     *
     * <p>Nexori maintains a single public AFK state per player. Calling with {@code afk=true}
     * marks the player as AFK; calling with {@code afk=false} marks the player as active and
     * resets the idle timer so the built-in automatic detector does not re-trigger immediately.</p>
     *
     * <p>If Nexori's built-in AFK detection policy is still enabled for the match, player input
     * and inventory activity can change this same AFK state normally. Minigames that want to own
     * AFK detection completely should first disable the built-in policy via
     * {@link #setMatchAfkDetectionPolicy(NexoriSetMatchAfkDetectionPolicyRequest)} or
     * {@link #setPlayerAfkDetectionPolicy(NexoriSetPlayerAfkDetectionPolicyRequest)}, then use
     * this method to report AFK state from their own game logic.</p>
     *
     * <p>Returns {@link NexoriSetPlayerAfkStatus#UNCHANGED} and dispatches no callback if the
     * player was already in the requested state.</p>
     */
    @Nonnull
    default NexoriSetPlayerAfkResult setPlayerAfk(@Nonnull NexoriSetPlayerAfkRequest request) {
        return new NexoriSetPlayerAfkResult(
            NexoriSetPlayerAfkStatus.NOT_SUPPORTED,
            request == null ? "" : request.matchId(),
            request == null ? null : request.playerUuid(),
            request != null && request.afk(),
            "External AFK control is not supported by this NexoriMinigameApi implementation."
        );
    }

    /**
     * Finds the currently active Nexori match id for one player UUID.
     *
     * @param playerUuid player to look up.
     * @return active match id, or empty when the player is not in a Nexori active match.
     */
    @Nonnull
    Optional<String> findActiveMatchId(@Nonnull UUID playerUuid);

    /**
     * Finds an active player in one match by UUID string or by current username.
     *
     * @param matchId Nexori local match id.
     * @param playerToken player UUID string or current username.
     * @return matching active player UUID, or empty when no active player matches.
     */
    @Nonnull
    Optional<UUID> findActivePlayerUuid(@Nonnull String matchId, @Nonnull String playerToken);

    /**
     * Returns the public runtime snapshot for one active match.
     *
     * @param matchId Nexori local match id.
     * @return public active match snapshot, or empty when the match is missing.
     */
    @Nonnull
    Optional<NexoriActiveMatchInfo> findActiveMatchInfo(@Nonnull String matchId);

    /**
     * Returns the rules engine id that should control one active manual/custom match.
     *
     * @param matchId Nexori local match id.
     * @return controlling rules engine id, or empty when unavailable.
     */
    @Nonnull
    Optional<String> findRulesEngineId(@Nonnull String matchId);

    /**
     * Stores or replaces one player's accumulated outcome inside the active match runtime.
     *
     * <p>Call this before submitting the final result. All players from
     * {@link #findMatchResultRequirements(String)} {@code requiredPlayerUuids} must have outcomes
     * before local match completion can be accepted.</p>
     *
     * @param matchId Nexori local match id.
     * @param playerUuid player receiving the outcome.
     * @param outcome final outcome to store for the player.
     * @param reason short public reason for diagnostics.
     * @return result describing whether the outcome was stored.
     */
    @Nonnull
    NexoriSetPlayerOutcomeResult setPlayerOutcome(
        @Nonnull String matchId,
        @Nonnull UUID playerUuid,
        @Nonnull NexoriMatchResultPlayerOutcome outcome,
        @Nonnull String reason
    );

    /**
     * Stores logical spectator state for one player inside the active match runtime.
     *
     * @param matchId Nexori local match id.
     * @param playerUuid player whose spectator state should change.
     * @param spectator whether the player should be treated as a spectator.
     * @param reason short public reason for diagnostics.
     * @return result describing whether the spectator state was stored.
     */
    @Nonnull
    NexoriSetPlayerSpectatorResult setPlayerSpectator(
        @Nonnull String matchId,
        @Nonnull UUID playerUuid,
        boolean spectator,
        @Nonnull String reason
    );

    /**
     * Stores logical spectator state and optionally applies a temporary spectator model while the player is online.
     * Blank or unknown model ids do not change the logical spectator outcome.
     *
     * @param matchId Nexori local match id.
     * @param playerUuid player whose spectator state should change.
     * @param spectator whether the player should be treated as a spectator.
     * @param reason short public reason for diagnostics.
     * @param spectatorModelId optional runtime model id to apply while the player is online.
     * @return result describing whether the spectator state was stored.
     */
    @Nonnull
    default NexoriSetPlayerSpectatorResult setPlayerSpectator(
        @Nonnull String matchId,
        @Nonnull UUID playerUuid,
        boolean spectator,
        @Nonnull String reason,
        String spectatorModelId
    ) {
        return setPlayerSpectator(matchId, playerUuid, spectator, reason);
    }

    /**
     * Schedules one player for Nexori's return-to-lobby flow without changing their outcome.
     *
     * @param matchId Nexori local match id.
     * @param playerUuid player to return.
     * @param delaySeconds delay before return; implementations validate the supported range.
     * @param reason short public reason for diagnostics.
     * @return result describing whether the return was scheduled.
     */
    @Nonnull
    NexoriReturnPlayerResult returnPlayerToLobby(
        @Nonnull String matchId,
        @Nonnull UUID playerUuid,
        int delaySeconds,
        @Nonnull String reason
    );

    /**
     * Returns the complete player set a rules mod must include when submitting a match result.
     *
     * <p>{@link NexoriMatchResultRequirements#requiredPlayerUuids()} is the official result
     * requirement set for the match. Use it as the source of truth for final outcomes.</p>
     *
     * @param matchId Nexori local match id.
     * @return result requirements, or empty when the match is missing.
     */
    @Nonnull
    Optional<NexoriMatchResultRequirements> findMatchResultRequirements(@Nonnull String matchId);

    /**
     * Completes one match using accumulated player outcomes and optionally queues a backend result report when configured.
     *
     * <p>{@code matchStatus} in the returned result describes local acceptance by Nexori.
     * {@code backendReportStatus} describes only backend result transport.</p>
     *
     * @param request final result request.
     * @return local completion and backend reporting status.
     */
    @Nonnull
    NexoriSubmitFinalMatchResultResult submitFinalMatchResult(@Nonnull NexoriSubmitFinalMatchResultRequest request);

    /**
     * Explicitly closes backend admission reporting for one active backend-driven match.
     *
     * @param request close admission request.
     * @return result describing whether admission changed locally.
     */
    @Nonnull
    NexoriCloseMatchAdmissionResult closeMatchAdmission(@Nonnull NexoriCloseMatchAdmissionRequest request);

    /**
     * Returns the state of Nexori's initial player placement phase for one active match.
     *
     * @param matchId Nexori local match id.
     * @return placement/start gate state, or empty when the match is missing.
     */
    @Nonnull
    Optional<NexoriMatchPlacementState> findMatchPlacementState(@Nonnull String matchId);

    /**
     * Legacy field kept only for compatibility.
     * <p>
     * Nexori no longer supports built-in match resolution triggers. Active matches return the
     * legacy value {@code "none"} only for older mods that treated it as manual/external
     * resolution. Use {@code rulesEngineId} from match info/lifecycle events to identify the
     * external minigame/rules engine. External minigames should resolve matches through the public
     * API. This member will be removed in a future API cleanup.
     *
     * @param matchId Nexori local match id.
     * @return legacy resolution trigger id, normally {@code "none"}, or empty when unavailable.
     */
    @Nonnull
    @Deprecated(forRemoval = true)
    Optional<String> findMatchResolutionTriggerId(@Nonnull String matchId);
}
