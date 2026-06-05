package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;

/**
 * Public lifecycle callbacks for third-party minigame/rules-engine integrations.
 */
public interface NexoriMatchLifecycleListener {

    /**
     * Called after Nexori creates an active match runtime for the listener's rules engine id.
     *
     * @param event match snapshot at creation time.
     */
    default void onMatchCreated(@Nonnull NexoriMatchLifecycleEvent event) {
    }

    /**
     * Called when a player arrives at the arena server for the match.
     *
     * @param event player-scoped lifecycle snapshot.
     */
    default void onPlayerArrived(@Nonnull NexoriPlayerMatchLifecycleEvent event) {
    }

    /**
     * Called when Nexori confirms or falls back one player's initial placement.
     *
     * @param event player placement snapshot.
     */
    default void onPlayerPlacementConfirmed(@Nonnull NexoriPlayerPlacementLifecycleEvent event) {
    }

    /**
     * Called when Nexori closes the initial placement phase.
     *
     * <p>This callback describes placement completion. Start gameplay from
     * {@link #onMatchStartAllowed(NexoriMatchLifecycleEvent)} instead, because the start gate may
     * open after full placement or after a valid partial roster.</p>
     *
     * @param event match snapshot at placement completion time.
     */
    default void onMatchPlacementCompleted(@Nonnull NexoriMatchLifecycleEvent event) {
    }

    /**
     * Called when Nexori allows gameplay to start for the match.
     *
     * <p>This is the recommended signal for a rules mod to start normal gameplay. It may happen
     * after all expected initial players were placed, or after the initial placement window expires
     * with at least the configured minimum initial players placed.</p>
     *
     * @param event match snapshot at the moment gameplay is allowed.
     */
    default void onMatchStartAllowed(@Nonnull NexoriMatchLifecycleEvent event) {
    }

    /**
     * Called when Nexori requests local cancellation before normal gameplay starts.
     *
     * <p>This usually means the initial placement/start gate requirements were not satisfied.
     * Rules mods should use this as a cleanup signal for any local session state created before
     * gameplay began.</p>
     *
     * @param event match snapshot associated with the cancellation request.
     */
    default void onMatchCancellationRequested(@Nonnull NexoriMatchLifecycleEvent event) {
    }

    /**
     * Called after Nexori accepts the final match result locally.
     *
     * @param event match snapshot at completion time.
     */
    default void onMatchCompleted(@Nonnull NexoriMatchLifecycleEvent event) {
    }

    /**
     * Called when Nexori closes the local runtime for the match.
     *
     * @param event final public snapshot before runtime cleanup completes.
     */
    default void onMatchRuntimeClosed(@Nonnull NexoriMatchLifecycleEvent event) {
    }
}
