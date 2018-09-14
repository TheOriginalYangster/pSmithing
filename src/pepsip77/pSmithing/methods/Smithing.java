package pepsip77.pSmithing.methods;

import pepsip77.pSmithing.data.Data;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Packets;
import xobot.script.methods.Widgets;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;

public class Smithing {
    public static boolean canSmith() {
        return (Data.forgeItems || Data.smeltAndForge) 
                && !Methods.inventoryContainsOres()
                && Inventory.Contains(Data.HAMMER_ID)
                && Inventory.Contains(Data.currentBar.getBarId());
    }

    public static void doSmithing() {
        Data.status = "Smithing";

        if (Widgets.getOpenInterface() != Data.SMITHING_INTERFACE_ID) {
            GameObject anvil = GameObjects.getNearest(Data.currentLocation.anvilId());

            if (anvil != null) {
                Item bar = Inventory.getItem(Data.currentBar.getBarId());

                if (bar != null) {
                    Packets.sendAction(447, bar.getID(), bar.getSlot(), 3214);
                    Time.sleep(400, 500);
                    Packets.sendAction(62, anvil.uid, anvil.getX(), anvil.getY(), anvil.getId(), 1);
                    Methods.conditionalSleep(new SleepCondition() {
                        @Override
                        public boolean isValid() {
                            return Widgets.getOpenInterface() == Data.SMITHING_INTERFACE_ID;
                        }
                    }, 2500);
                }
            }
        } else {
            //quantity, itemid, row, col, 0
            int count = Inventory.getCount(Data.currentBar.getBarId());
            Packets.sendAction(867, Data.currentItem.getItemId(), 
                    Data.currentItem.getAction2Id(), Data.currentItem.getAction3Id(), 0);
            Methods.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Widgets.getOpenInterface() != Data.SMITHING_INTERFACE_ID 
                            && Inventory.getCount(Data.currentBar.getBarId()) != count;
                }
            }, 2500);
        }
    }
}
