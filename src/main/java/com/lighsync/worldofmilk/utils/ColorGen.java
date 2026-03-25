package com.lighsync.worldofmilk.utils;

public class ColorGen {
    public static int toIntFormatFromRGBA(int r, int g, int b, int a) { return (a << 24) | (r << 16) | (g << 8) | b; }
    public static int toIntFormatFromRGB(int r, int g, int b) { return (1 << 24) | (r << 16) | (g << 8) | b; }
    public static int toIntFormatFromHEX(String hex) {
        Integer integer = Integer.decode(hex);
        int i = integer.intValue();
        int r = (i >> 16) & 0xFF, g = (i >> 8) & 0xFF, b = i & 0xFF;
        return toIntFormatFromRGB(r, g, b);
    }
    public static int getRFromInt(int color) { return (color >> 16) & 0xFF; }
    public static int getGFromInt(int color) { return (color >> 8) & 0xFF; }
    public static int getBFromInt(int color) { return color & 0xFF; }
    public static int getAFromInt(int color) { return (color >> 24) & 0xFF; }
    public static int getRFromHex(String hex) {
        Integer integer = Integer.decode(hex);
        int i = integer.intValue();
        return (i >> 16) & 0xFF;
    }
    public static int getGFromHex(String hex) {
        Integer integer = Integer.decode(hex);
        int i = integer.intValue();
        return (i >> 8) & 0xFF;
    }
    public static int getBFromHex(String hex) {
        Integer integer = Integer.decode(hex);
        int i = integer.intValue();
        return i & 0xFF;
    }
    public static int getAFromHex(String hex) {
        Integer integer = Integer.decode(hex);
        int i = integer.intValue();
        return (i >> 24) & 0xFF;
    }
}