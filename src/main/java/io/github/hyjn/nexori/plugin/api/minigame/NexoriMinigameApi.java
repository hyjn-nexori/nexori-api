package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.UUID;

/**
 * Public Nexori minigame integration surface intended for other mods.
 * This keeps external integrations away from internal runtime services while still exposing the supported hooks.
 */
public interface NexoriMinigameApi {

    /**
     * Registers a listener for Nexori match lifecycle callbacks for one rules engine id.
     */
    @Nonnull
    NexoriListenerRegistration registerMatchLifecycleListener(
        @Nonnull String rulesEngineId,
        @Nonnull NexoriMatchLifecycleListener listener
    );

    /**
     * Registers a listener for Nexori AFK activity callbacks for one rules engine id.
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
     */
    @Nonnull
    Optional<String> findActiveMatchId(@Nonnull UUID playerUuid);

    /**
     * Finds an active player in one match by UUID string or by current username.
     */
    @Nonnull
    Optional<UUID> findActivePlayerUuid(@Nonnull String matchId, @Nonnull String playerToken);

    /**
     * Returns the public runtime snapshot for one active match.
     */
    @Nonnull
    Optional<NexoriActiveMatchInfo> findActiveMatchInfo(@Nonnull String matchId);

    /**
     * Returns the rules engine id that should control one active manual/custom match.
     */
    @Nonnull
    Optional<String> findRulesEngineId(@Nonnull String matchId);

    /**
     * Stores or replaces one player's accumulated outcome inside the active match runtime.
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
     */
    @Nonnull
    Optional<NexoriMatchResultRequirements> findMatchResultRequirements(@Nonnull String matchId);

    /**
     * Completes one match using accumulated player outcomes and optionally queues a backend result report when configured.
     */
    @Nonnull
    NexoriSubmitFinalMatchResultResult submitFinalMatchResult(@Nonnull NexoriSubmitFinalMatchResultRequest request);

    /**
     * Explicitly closes backend admission reporting for one active backend-driven match.
     */
    @Nonnull
    NexoriCloseMatchAdmissionResult closeMatchAdmission(@Nonnull NexoriCloseMatchAdmissionRequest request);

    /**
     * Returns the state of Nexori's initial player placement phase for one active match.
     */
    @Nonnull
    Optional<NexoriMatchPlacementState> findMatchPlacementState(@Nonnull String matchId);

    /**
     * Returns the active match resolution trigger id for one active match.
     * When this returns "none", the match expects manual resolution from the third-party mod.
     */
    @Nonnull
    Optional<String> findMatchResolutionTriggerId(@Nonnull String matchId);
}
