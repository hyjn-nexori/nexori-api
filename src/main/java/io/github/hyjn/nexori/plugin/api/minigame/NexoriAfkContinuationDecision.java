package io.github.hyjn.nexori.plugin.api.minigame;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

/**
 * A backend AFK continuation decision for one active match.
 *
 * <p>The decision is match-level: a single player going AFK triggers a backend check,
 * but the resulting {@link NexoriAfkContinuationDecisionType#CANCEL} or
 * {@link NexoriAfkContinuationDecisionType#CONTINUE} applies to the match as a whole.
 * Nexori does NOT cancel the match automatically — the calling code decides how to
 * translate a {@link NexoriAfkContinuationDecisionType#CANCEL} into gameplay consequences.</p>
 *
 * <p>A {@link NexoriAfkContinuationDecisionType#CANCEL} decision is sticky for the match
 * lifetime: subsequent backend {@code CONTINUE} responses do not overwrite it.</p>
 *
 * @param matchId             the match this decision belongs to
 * @param decision            the decision type
 * @param triggeringPlayerUuid the player whose AFK event triggered the backend check, or
 *                            {@code null} for {@link NexoriAfkContinuationDecisionType#UNAVAILABLE}
 *                            and {@link NexoriAfkContinuationDecisionType#PENDING} states
 * @param reasonCode          optional reason code from the backend response; blank when unavailable
 * @param message             optional human-readable message from the backend; blank when unavailable
 * @param decidedAtEpochMs    epoch-ms timestamp when the decision was stored, or {@code 0} for
 *                            {@link NexoriAfkContinuationDecisionType#UNAVAILABLE} and
 *                            {@link NexoriAfkContinuationDecisionType#PENDING} states
 */
public record NexoriAfkContinuationDecision(
    @Nonnull String matchId,
    @Nonnull NexoriAfkContinuationDecisionType decision,
    @Nullable UUID triggeringPlayerUuid,
    @Nonnull String reasonCode,
    @Nonnull String message,
    long decidedAtEpochMs
) {

    /**
     * Returns an {@link NexoriAfkContinuationDecisionType#UNAVAILABLE} decision for the given match id.
     */
    @Nonnull
    public static NexoriAfkContinuationDecision unavailable(@Nonnull String matchId) {
        return new NexoriAfkContinuationDecision(
            matchId == null ? "" : matchId.trim(),
            NexoriAfkContinuationDecisionType.UNAVAILABLE,
            null,
            "",
            "",
            0L
        );
    }
}
