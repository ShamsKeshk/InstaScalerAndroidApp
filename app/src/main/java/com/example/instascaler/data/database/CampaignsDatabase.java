package com.example.instascaler.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Campaign.class},version = 1 , exportSchema = false)
@TypeConverters(DateConvertor.class)
public abstract class CampaignsDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "campaigns";

    private static final Object LOCK = new Object();

    private static CampaignsDatabase sCampaignsDatabase;

    public static CampaignsDatabase getInstance(Context context){
        if (sCampaignsDatabase == null){
            synchronized (LOCK){
                sCampaignsDatabase =
                        Room.databaseBuilder(context,CampaignsDatabase.class,CampaignsDatabase.DATABASE_NAME).build();
            }
        }
        return sCampaignsDatabase;
    }

    public abstract CampaignDao campaignDao();

}
