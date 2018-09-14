package pepsip77.pSmithing.methods;

import pepsip77.pSmithing.data.Data;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.Item;

public class Methods {
    /*
     * conditional sleep from Parabot
     */
    public static boolean conditionalSleep(SleepCondition conn, int timeout) {
        long start = System.currentTimeMillis();

        while (!conn.isValid()) {
            if (start + timeout < System.currentTimeMillis()) {
                return false;
            }

            Time.sleep(50);
        }

        return true;
    }

    public static boolean itemIsMaterial(int id) {
        return itemIsOre(id) || Data.currentBar.getBarId() == id;
    }

    public static boolean itemIsOre(int id) {
        for(int i : Data.currentBar.getOreIds()) {
            if(i == id)
                return true;
        }

        return false;
    }

    public static boolean inventoryContainsOres() {
        for(int i : Data.currentBar.getOreIds()) {
            if (!Inventory.Contains(i))
                return false;
        }

        return true;
    }

    public static boolean inventoryContainsMaterials() {
        for(Item item : Inventory.getItems()) {
            if(item != null && itemIsMaterial(item.getID()))
                return true;
        }

        return false;
    }
}
