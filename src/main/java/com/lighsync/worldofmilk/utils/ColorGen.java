package com.lighsync.worldofmilk.utils;

public class ColorGen {
    public static int toIntFormatFromRGBA(int r, int g, int b, int a) { return (a << 24) | (r << 16) | (g << 8) | b; }
    public static int toIntFormatFromRGB(int r, int g, int b) { return toIntFormatFromRGBA(r, g, b, 1); }
    public static int toIntFormatFromHEX(String hex) {
        int i = getDecodedInteger(hex);
        int r = (i >> 16) & 0xFF, g = (i >> 8) & 0xFF, b = i & 0xFF;
        return toIntFormatFromRGB(r, g, b);
    }
    public static int toIntFormatFromString(String color) {
        color = color.toUpperCase();
        int r = 0, g = 0, b = 0;
        switch (color) {
            case "CYAN" -> { r = 0; g = 255; b = 255; }
            case "GREEN" -> { r = 0; g = 128; b = 0; }
            case "LIGHT_BLUE" -> { r = 173; g = 216; b = 230; }
            case "LIGHT_GRAY"-> { r = 192; g = 192; b = 192; }
            case "MAGENTA" -> { r = 255; g = 0; b = 255; }
            case "ORANGE" -> { r = 255; g = 165; b = 0; }
            case "PINK" -> { r = 255; g = 192; b = 203; }
            case "PURPLE" -> { r = 128; g = 0; b = 128; }
            case "RED" -> { r = 255; g = 0; b = 0; }
            case "WHITE" -> { r = 255; g = 255; b = 255; }
            case "YELLOW" -> { r = 255; g = 255; b = 0; }
            case "BLACK" -> { r = 0; g = 0; b = 0; }
            case "BROWN" -> { r = 165; g = 42; b = 42; }
            case "LIME" -> { r = 0; g = 255; b = 0; }
            case "BLUE" -> { r = 0; g = 0; b = 255; }
            case "GRAY" -> { r = 128; g = 128; b = 128; }
            default -> { r = 0; g = 0; b = 0; }
        }
        return toIntFormatFromRGB(r, g, b);
    }

    public static int getRFromInt(int color) { return (color >> 16) & 0xFF; }
    public static int getGFromInt(int color) { return (color >> 8) & 0xFF; }
    public static int getBFromInt(int color) { return color & 0xFF; }
    public static int getAFromInt(int color) { return (color >> 24) & 0xFF; }

    public static int getRFromHex(String hex) { return getRFromInt(getDecodedInteger(hex)); }
    public static int getGFromHex(String hex) { return getGFromInt(getDecodedInteger(hex)); }
    public static int getBFromHex(String hex) { return getBFromInt(getDecodedInteger(hex)); }
    public static int getAFromHex(String hex) { return getAFromInt(getDecodedInteger(hex)); }

    private static int getDecodedInteger(String hex) {
        Integer integer = Integer.decode(hex);
        return integer.intValue();
    }
}