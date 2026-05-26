package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;

/**
 * Public AFK detection policy used by runtime match/player overrides.
 */
public record NexoriAfkDetectionPolicy(
    boolean enabled,
    int inactivityTimeoutSeconds
) {

    public static final int DEFAULT_INACTIVITY_TIMEOUT_SECONDS = 30;
    public static final int MIN_INACTIVITY_TIMEOUT_SECONDS = 5;
    public static final int MAX_INACTIVITY_TIMEOUT_SECONDS = 3600;

    @Nonnull
    public static NexoriAfkDetectionPolicy defaults() {
        return new NexoriAfkDetectionPolicy(false, DEFAULT_INACTIVITY_TIMEOUT_SECONDS);
    }

    @Nonnull
    public static NexoriAfkDetectionPolicy normalize(NexoriAfkDetectionPolicy policy) {
        return policy == null ? defaults() : policy.normalized();
    }

    @Nonnull
    public NexoriAfkDetectionPolicy normalized() {
        return new NexoriAfkDetectionPolicy(enabled, normalizeTimeoutSeconds(inactivityTimeoutSeconds));
    }

    private static int normalizeTimeoutSeconds(int rawSeconds) {
        if (rawSeconds <= 0) {
            return DEFAULT_INACTIVITY_TIMEOUT_SECONDS;
        }
        if (rawSeconds < MIN_INACTIVITY_TIMEOUT_SECONDS) {
            return MIN_INACTIVITY_TIMEOUT_SECONDS;
        }
        return Math.min(rawSeconds, MAX_INACTIVITY_TIMEOUT_SECONDS);
    }
}
