package io.github.eikefab.libs.commandcreator.adapter;

public final class NumberAdapter {

    public static double adaptDouble(Object value) {
        return Double.parseDouble(value.toString());
    }

    public static int adaptInteger(Object value) {
        return Integer.parseInt(value.toString());
    }

    public static float adaptFloat(Object value) {
        return Float.parseFloat(value.toString());
    }

    public static short adaptShort(Object value) {
        return Short.parseShort(value.toString());
    }

    public static long adaptLong(Object value) {
        return Long.parseLong(value.toString());
    }

    public static byte adaptByte(Object value) {
        return Byte.parseByte(value.toString());
    }

}
