package com.university.persistance.entities;

import java.time.LocalDateTime;


/**
 * Represents a previous run of the game.
 */
public class RunHistoryEntity extends BaseEntity {
    private int playerId;
    private String outcome;
    private LocalDateTime runDate;

    public RunHistoryEntity(int id, int playerId, String outcome, LocalDateTime runDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.playerId = playerId;
        this.outcome = outcome;
        this.runDate = runDate;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public LocalDateTime getRunDate() {
        return runDate;
    }

    public void setRunDate(LocalDateTime runDate) {
        this.runDate = runDate;
    }
}