package pepsip77.pSmithing.methods;

import pepsip77.pSmithing.data.Data;
import xobot.script.methods.Bank;
import xobot.script.methods.GameObjects;
import xobot.script.methods.NPCs;
import xobot.script.methods.Packets;
import xobot.script.methods.tabs.Inventory;
import xobot.script.util.Time;
import xobot.script.wrappers.interactive.GameObject;
import xobot.script.wrappers.interactive.Item;
import xobot.script.wrappers.interactive.NPC;

public class Banking {
    public static boolean needBank() {
        return (Data.smeltOres && !Methods.inventoryContainsOres())
                || ((Data.forgeItems || Data.smeltAndForge) && (!Inventory.Contains(Data.HAMMER_ID)
                        || (!Methods.inventoryContainsOres() && !Inventory.Contains(Data.currentBar.getBarId()))));
    }

    public static void doBank() {
        Data.status = "Banking";

        if (!Bank.isOpen()) {
            openBank();
        }

        if (Bank.isOpen()) {
            bankAllExcept();
            bankWithdraw();
        }
    }

    private static void bankWithdraw() {
        if ((Data.forgeItems || Data.smeltAndForge) && !Inventory.Contains(Data.HAMMER_ID)) {
            Bank.withdraw(Data.HAMMER_ID, 1);
            Time.sleep(175, 200);
        }

        if ((Data.smeltOres || Data.smeltAndForge) && !Methods.inventoryContainsOres()) {
            if (Data.currentBar.getOreIds().length == 1) {
                Item item = Bank.getItem(Data.currentBar.getOreIds()[0]);
                if (item != null) {
                    Bank.withdraw(item.getID(), 28);
                    // item.interact("withdraw all");
                    Time.sleep(175, 200);
                }
            } else {
                for (int id : Data.currentBar.getOreIds()) {
                    Item item = Bank.getItem(id);

                    if (item != null) {
                        if (Data.smeltOres)
                            Bank.withdraw(item.getID(), 14);
                        else
                            Bank.withdraw(item.getID(), 13);

                        Time.sleep(175, 200);
                    }
                }
            }
        }

        if((Data.forgeItems || Data.smeltAndForge) && !Inventory.isFull()) {
            Item item = Bank.getItem(Data.currentBar.getBarId());

            if(item != null) {
                Bank.withdraw(item.getID(), 28);
                Time.sleep(175, 200);
            }
        }
    }

    private static void bankAllExcept() {
        for (Item item : Inventory.getItems()) {
            if (item != null && Inventory.Contains(item.getID())) {
                if (((Data.forgeItems || Data.smeltAndForge) && item.getID() != Data.HAMMER_ID
                        && !Methods.itemIsMaterial(item.getID()))
                        || (Data.smeltOres && !Methods.itemIsOre(item.getID()))) {
                    item.interact("store all");
                    Time.sleep(175, 200);
                }
            }
        }
    }

    private static void openBank() {
        if (Data.currentLocation.bankIsNpc()) {
            NPC npc = NPCs.getNearest(Data.currentLocation.getBankId());

            if (npc != null) {
                Packets.sendAction(225, npc.getIndex(), 0, 0);
                Methods.conditionalSleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Bank.isOpen();
                    }
                }, 2000);
            }
        } else {
            GameObject bank = GameObjects.getNearest(Data.currentLocation.getBankId());

            if (bank != null) {
                bank.interact("bank");
                Methods.conditionalSleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Bank.isOpen();
                    }
                }, 2000);
            }
        }
    }
}
