package com.lighsync.worldofmilk.effects.utils;

public class ColorGen {
    private ColorGen() {
    }

    public static int toIntFormat(int r, int g, int b, int a) { return (a << 24) | (r << 16) | (g << 8) | b; }
    public static int getRFromInt(int color) { return (color >> 16) & 0xFF; }
    public static int getGFromInt(int color) { return (color >> 8) & 0xFF; }
    public static int getBFromInt(int color) { return color & 0xFF; }
    public static int getAFromInt(int color) { return (color >> 24) & 0xFF; }
}