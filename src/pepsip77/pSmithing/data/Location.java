package pepsip77.pSmithing.data;

import xobot.script.wrappers.Tile;

public enum Location {
    HOME(new Tile(3097, 3496), 26972, false, 26814, true, 2783, true),
    DONATOR_ZONE(new Tile(2539, 3891), 494, true, 3044, true, 2783, true),
    DUNGEONEERING(new Tile(3225, 3253), 494, true, 2781, true, -1, false ),
    SKILLING(new Tile(2339, 3802), 21301, false, 21303, true, 2783, true);

    private Tile tile;
    private int bankId;
    private boolean bankIsNpc;
    private int furnanceId;
    private boolean hasFurnance;
    private int anvilId;
    private boolean hasAnvil;

    Location(Tile tile, int bankId, boolean bankIsNpc, int furnanceId, 
            boolean hasFurnance, int anvilId, boolean hasAnvil){
        this.tile = tile;
        this.bankId = bankId;
        this.bankIsNpc = bankIsNpc;
        this.furnanceId = furnanceId;
        this.hasFurnance = hasFurnance;
        this.anvilId = anvilId;
        this.hasAnvil = hasAnvil;
    }

    public Tile getTile() {
        return tile;
    }

    public int getBankId() {
        return bankId;
    }

    public boolean bankIsNpc() {
        return bankIsNpc;
    }

    public int furnanceId() {
        return furnanceId;
    }

    public boolean hasFurnance() {
        return hasFurnance;
    }

    public int anvilId() {
        return anvilId;
    }

    public boolean hasAnvil() {
        return hasAnvil;
    }
}
