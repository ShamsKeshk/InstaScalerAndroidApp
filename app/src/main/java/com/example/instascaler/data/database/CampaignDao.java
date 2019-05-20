package com.example.instascaler.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CampaignDao {

    @Query("SELECT id, name, country, category, goal , budget FROM campaigns ")
    LiveData<List<Campaign>> getCampaignsList();

    @Query("SELECT id, name, country, category, goal , budget FROM campaigns WHERE id = :id")
    Campaign getCampaignById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<Campaign>  campaign);

}
