package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;

/**
 * Public AFK activity callbacks for third-party minigame/rules-engine integrations.
 */
public interface NexoriAfkActivityListener {

    /**
     * Called whenever Nexori's local AFK runtime state changes for one player.
     *
     * <p>Rules mods can use this callback for gameplay penalties, HUD mirroring, analytics, or
     * custom result data. The callback describes local AFK detection/reporting state only.</p>
     *
     * @param event immutable AFK transition event.
     */
    default void onPlayerAfkChanged(@Nonnull NexoriPlayerAfkChangedEvent event) {
    }
}
