package com.withertech.mixin;

import com.google.gson.JsonElement;
import com.withertech.MineFlux;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.stream.Collectors;

@Mixin(ServerAdvancementManager.class)
public class ServerAdvancementManagerMixin
{
	@Inject(method = "apply*", at = @At("HEAD"))
	public void interceptApply(Map<ResourceLocation, JsonElement> map, ResourceManager resourceManager, ProfilerFiller profilerFiller, CallbackInfo info)
	{
		if (!MineFlux.RECIPES.isEmpty())
		{
			map.putAll(MineFlux.RECIPES.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().getValue(), e -> e.getValue().getAdvancement())));
		}
	}
}
