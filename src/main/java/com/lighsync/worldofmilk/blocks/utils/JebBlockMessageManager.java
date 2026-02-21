package com.lighsync.worldofmilk.blocks.utils;

import com.google.common.collect.Lists;
import com.lighsync.worldofmilk.Worldofmilk;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class JebBlockMessageManager extends SimplePreparableReloadListener<List<String>> {
    public static final JebBlockMessageManager INSTANCE = new JebBlockMessageManager();
    private static final ResourceLocation JEB_BLOCK_SPLASHES_LOCATION = ResourceLocation.fromNamespaceAndPath(Worldofmilk.MODID, "texts/jeb_block.txt");
    private static final RandomSource RANDOM = RandomSource.create();
    private final List<String> messages = Lists.newArrayList();

    @Override
    protected List<String> prepare(ResourceManager rm, ProfilerFiller pf) {
        try (InputStream in = rm.open(JEB_BLOCK_SPLASHES_LOCATION)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                return reader.lines()
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void apply(List<String> prepared, ResourceManager rm, ProfilerFiller pf) {
        this.messages.clear();
        this.messages.addAll(prepared);
        Worldofmilk.getLogger().info("[JebBlockMessageManager]: Loaded" + prepared.size() + " messages");
    }

    public String getRandomMessage() {
        if (messages.isEmpty()) return "jeb_ is silent today..";
        return messages.get(RANDOM.nextInt(messages.size()));
    }
}