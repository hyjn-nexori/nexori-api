package io.github.hyjn.nexori.plugin.api.minigame;

import com.google.gson.JsonObject;

/**
 * Final match result request.
 *
 * <p>Player outcomes are read from the active match runtime. Rules mods should store outcomes for
 * every player in {@link NexoriMatchResultRequirements#requiredPlayerUuids()} before submitting
 * this request.</p>
 *
 * @param matchId Nexori local match id.
 * @param reason short public reason for diagnostics.
 * @param customData optional result payload supplied by the rules mod.
 */
public record NexoriSubmitFinalMatchResultRequest(
    String matchId,
    String reason,
    JsonObject customData
) {
}
