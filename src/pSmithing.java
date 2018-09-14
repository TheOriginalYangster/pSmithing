
import java.awt.Graphics;

import pepsip77.pSmithing.data.Data;
import pepsip77.pSmithing.methods.Banking;
import pepsip77.pSmithing.methods.Smelting;
import pepsip77.pSmithing.methods.Smithing;
import pepsip77.pSmithing.utils.Gui;
import pepsip77.pSmithing.utils.PaintUtil;
import xobot.client.callback.listeners.PaintListener;
import xobot.script.ActiveScript;
import xobot.script.Manifest;
import xobot.script.methods.Players;
import xobot.script.methods.tabs.Skills;
import xobot.script.util.Time;
import xobot.script.util.Timer;

@Manifest(authors = { "pepsip77" }, name = "pSmithing", version = 0.2, description = "AIO smither")
public class pSmithing extends ActiveScript implements PaintListener {
    @Override
    public int loop() {
        if (Players.getMyPlayer().getAnimation() != -1)
            Data.animTimer = new Timer(0);

        if (Banking.needBank())
            Banking.doBank();

        if (Smelting.canSmelt() && Data.animTimer.getElapsed() > 1300)
            Smelting.doSmelt();

        if (Smithing.canSmith() && Data.animTimer.getElapsed() > 600)
            Smithing.doSmithing();

        return 100;
    }

    @Override
    public boolean onStart() {
        Data.runtime = new Timer(0);
        Gui gui = new Gui();

        while (gui.frmPsmithing.isVisible()) {
            Time.sleep(80);
        }

        Data.animTimer = new Timer(0);
        Data.runtime = new Timer(0);
        Data.startLevel = Skills.getCurrentLevel(Skills.SMITHING);
        Data.startXp = Skills.getCurrentExp(Skills.SMITHING);
        return true;
    }

    @Override
    public void repaint(Graphics g) {
        PaintUtil.draw(g);
    }
}
