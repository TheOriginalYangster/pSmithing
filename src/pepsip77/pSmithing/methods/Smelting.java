package pepsip77.pSmithing.methods;

import pepsip77.pSmithing.data.Data;
import xobot.script.methods.GameObjects;
import xobot.script.methods.Packets;
import xobot.script.methods.Players;
import xobot.script.methods.Widgets;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.GameObject;

public class Smelting {
    public static boolean canSmelt() {
        return (Data.smeltOres || Data.smeltAndForge) 
                && Methods.inventoryContainsOres();
    }

    public static void doSmelt() {
        Data.status = "Smelting";
        GameObject furnance = GameObjects.getNearest(Data.currentLocation.furnanceId());

        if (furnance != null 
                && !Players.getMyPlayer().isMoving() 
                && Widgets.getBackDialogId() != Data.SMELTING_WIDGET_ID) {
            Packets.sendAction(0, 0, 0, 0);
            Time.sleep(175, 200);
            Packets.sendAction(900, furnance.uid, furnance.getX(), furnance.getY(), 1);
            Methods.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Widgets.getBackDialogId() == Data.SMELTING_WIDGET_ID;
                }
            }, 2000);
        }

        if (Widgets.getBackDialogId() == Data.SMELTING_WIDGET_ID) {
            Packets.sendAction(315, -1, -1, Data.currentBar.getSmeltingAction(), 1);
            Methods.conditionalSleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Players.getMyPlayer().getAnimation() != -1;
                }
            }, 2000);
        }
    }
}
