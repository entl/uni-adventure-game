package com.university.persistance.entities;

import java.time.LocalDateTime;

/**
 * Represents a player in the game.
 * Contains details like name, power points, current room, dungeon level, and status.
 */
public class PlayerEntity extends BaseEntity {
    private String name;
    private int powerPoints;
    private String room;
    private int dungeonLevel;
    private boolean isTrapped;
    private boolean isAsleep;


    public PlayerEntity(int id, String name, int powerPoints, String room, int dungeonLevel, boolean isTrapped, boolean isAsleep, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.powerPoints = powerPoints;
        this.room = room;
        this.dungeonLevel = dungeonLevel;
        this.isTrapped = isTrapped;
        this.isAsleep = isAsleep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPowerPoints() {
        return powerPoints;
    }

    public void setPowerPoints(int powerPoints) {
        this.powerPoints = powerPoints;
    }

    public int getDungeonLevel() {
        return dungeonLevel;
    }

    public void setDungeonLevel(int dungeonLevel) {
        this.dungeonLevel = dungeonLevel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean isTrapped() {
        return isTrapped;
    }

    public void setTrapped(boolean trapped) {
        isTrapped = trapped;
    }

    public boolean isAsleep() {
        return isAsleep;
    }

    public void setAsleep(boolean asleep) {
        isAsleep = asleep;
    }
}