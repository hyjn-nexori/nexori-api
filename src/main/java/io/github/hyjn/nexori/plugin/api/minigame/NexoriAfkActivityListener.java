package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;

/**
 * Public AFK activity callbacks for third-party minigame/rules-engine integrations.
 */
public interface NexoriAfkActivityListener {

    default void onPlayerAfkChanged(@Nonnull NexoriPlayerAfkChangedEvent event) {
    }
}
