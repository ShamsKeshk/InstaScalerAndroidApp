package com.example.instascaler.data.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "campaigns")
public class Campaign {

    @PrimaryKey
    private int id;
    private String name;
    private String country;
    private String goal;
    private float budget;
    private String category;
    @Ignore
    private long createdAt;
    @Ignore
    private long updatedAt;

    public Campaign() {
    }

    public Campaign(int id, String name, String country, String goal, float budget, String category, long createdAt, long updatedAt) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.goal = goal;
        this.budget = budget;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Campaign(String name, String country, String goal, float budget, String category, long createdAt, long updatedAt) {
        this.name = name;
        this.country = country;
        this.goal = goal;
        this.budget = budget;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
