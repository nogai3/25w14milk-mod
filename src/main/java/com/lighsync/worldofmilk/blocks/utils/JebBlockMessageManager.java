package com.lighsync.worldofmilk.blocks.utils;

import com.google.common.collect.Lists;
import com.lighsync.worldofmilk.Worldofmilk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JebBlockMessageManager extends SimplePreparableReloadListener<List<String>> {
    public static final JebBlockMessageManager INSTANCE = new JebBlockMessageManager();
    private static final ResourceLocation JEB_BLOCK_SPLASHES_LOCATION = ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "texts/jeb_block.txt");

    private static final RandomSource RANDOM = RandomSource.create();
    private final List<String> messages = Lists.newArrayList();

    @Override
    protected List<String> prepare(ResourceManager rm, ProfilerFiller pf) {
        try {
            Optional<Resource> resOpt = rm.getResource(JEB_BLOCK_SPLASHES_LOCATION);
            if (resOpt.isEmpty()) {
                Worldofmilk.getLogger().warn("[JebBlockMessageManager]: Missing resource {}", JEB_BLOCK_SPLASHES_LOCATION);
                return List.of();
            }

            try (var reader = new BufferedReader(new InputStreamReader(
                    resOpt.get().open(), StandardCharsets.UTF_8))) {

                return reader.lines()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            Worldofmilk.getLogger().error("[JebBlockMessageManager]: Failed to load {}", JEB_BLOCK_SPLASHES_LOCATION, e);
            return List.of();
        }
    }

    @Override
    protected void apply(List<String> prepared, ResourceManager rm, ProfilerFiller pf) {
        this.messages.clear();
        this.messages.addAll(prepared);
        Worldofmilk.getLogger().info("[JebBlockMessageManager]: Loaded {} messages", prepared.size());
    }

    public String getRandomMessage() {
        if (messages.isEmpty()) return "jeb_ is silent today..";
        return messages.get(RANDOM.nextInt(messages.size()));
    }
}