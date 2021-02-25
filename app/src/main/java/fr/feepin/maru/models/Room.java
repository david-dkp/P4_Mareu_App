package fr.feepin.maru.models;

import fr.feepin.maru.R;

public enum Room {
    MARIO(R.string.room_mario, R.color.red_dark),
    LUIGI(R.string.room_luigi, R.color.green_dark),
    PEACH(R.string.room_peach, R.color.pink),
    TOAD(R.string.room_toad, R.color.white),
    YOSHI(R.string.room_toad, R.color.green),
    DAISY(R.string.room_daisy, R.color.violet_darker),
    DK(R.string.room_dk, R.color.brown),
    ROSALINA(R.string.room_rosalina, R.color.cyan),
    BROWSER(R.string.room_browser, R.color.yellow),
    WARIO(R.string.room_wario, R.color.violet_darker),
    WALUIGI(R.string.room_waluigi, R.color.violet);

    private int roomName;
    private int roomColor;

    Room(int roomName, int roomColor) {
        this.roomName = roomName;
        this.roomColor = roomColor;
    }

    public int getRoomName() {
        return roomName;
    }

    public int getRoomColor() {
        return roomColor;
    }
}
