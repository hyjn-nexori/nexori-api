package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;

/**
 * Public lifecycle callbacks for third-party minigame/rules-engine integrations.
 */
public interface NexoriMatchLifecycleListener {

    default void onMatchCreated(@Nonnull NexoriMatchLifecycleEvent event) {
    }

    default void onPlayerArrived(@Nonnull NexoriPlayerMatchLifecycleEvent event) {
    }

    default void onPlayerPlacementConfirmed(@Nonnull NexoriPlayerPlacementLifecycleEvent event) {
    }

    default void onMatchPlacementCompleted(@Nonnull NexoriMatchLifecycleEvent event) {
    }

    /**
     * Called when Nexori allows gameplay to start for the match.
     * This may happen after all expected initial players were placed, or after the initial
     * placement window expires with at least the configured minimum initial players placed.
     */
    default void onMatchStartAllowed(@Nonnull NexoriMatchLifecycleEvent event) {
    }

    default void onMatchCancellationRequested(@Nonnull NexoriMatchLifecycleEvent event) {
    }

    default void onMatchCompleted(@Nonnull NexoriMatchLifecycleEvent event) {
    }

    default void onMatchRuntimeClosed(@Nonnull NexoriMatchLifecycleEvent event) {
    }
}
