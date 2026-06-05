package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;

/**
 * Public AFK detection policy used by runtime match/player overrides.
 *
 * <p>{@link #defaults()} returns local AFK detection disabled with a 30 second inactivity
 * timeout. Runtime policies may be enabled at match or player scope. Timeout values are
 * normalized to the supported 5-3600 second range.</p>
 *
 * @param enabled whether Nexori's local lightweight AFK detector should run.
 * @param inactivityTimeoutSeconds inactivity duration required before Nexori marks a player AFK.
 */
public record NexoriAfkDetectionPolicy(
    boolean enabled,
    int inactivityTimeoutSeconds
) {

    public static final int DEFAULT_INACTIVITY_TIMEOUT_SECONDS = 30;
    public static final int MIN_INACTIVITY_TIMEOUT_SECONDS = 5;
    public static final int MAX_INACTIVITY_TIMEOUT_SECONDS = 3600;

    /**
     * Returns the default disabled policy used when no runtime override is configured.
     *
     * @return disabled policy with a 30 second normalized timeout.
     */
    @Nonnull
    public static NexoriAfkDetectionPolicy defaults() {
        return new NexoriAfkDetectionPolicy(false, DEFAULT_INACTIVITY_TIMEOUT_SECONDS);
    }

    /**
     * Normalizes a nullable policy.
     *
     * @param policy policy to normalize, or {@code null} to use {@link #defaults()}.
     * @return a non-null policy with a valid timeout.
     */
    @Nonnull
    public static NexoriAfkDetectionPolicy normalize(NexoriAfkDetectionPolicy policy) {
        return policy == null ? defaults() : policy.normalized();
    }

    /**
     * Returns this policy with its timeout clamped into the supported range.
     *
     * @return normalized policy.
     */
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
