package pepsip77.pSmithing.data;

import xobot.script.util.Timer;

public class Data {
    public final static int SMELTING_WIDGET_ID = 2400;
    public final static int HAMMER_ID = 2347;
    public final static int SMITHING_INTERFACE_ID = 994;
    public final static int COAL_ID = 453;

    public static Location currentLocation;
    public static Bar currentBar;
    public static Item_ currentItem;

    public static boolean smeltOres;
    public static boolean forgeItems;
    public static boolean smeltAndForge;

    public static Timer animTimer;
    public static Timer runtime;

    public static String status = "";

    public static int startXp;
    public static int startLevel;
}
