package com.example.instascaler.data.network;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.instascaler.data.database.Campaign;

import java.util.List;

public class CampaignNetworkDataSource {

    private static CampaignNetworkDataSource sCampaignNetworkDataSource;

    private static final Object LOCK = new Object();

    private Context sContext;

    private final MutableLiveData<List<Campaign>> mListMutableLiveData;

    private CampaignNetworkDataSource(Context context){
        this.sContext = context;
        mListMutableLiveData = new MutableLiveData<>();
    }

    public static CampaignNetworkDataSource getInstance(Context context){
        if (sCampaignNetworkDataSource == null){
            synchronized (LOCK){
                sCampaignNetworkDataSource = new CampaignNetworkDataSource(context);
            }
        }
        return sCampaignNetworkDataSource;
    }

    public LiveData<List<Campaign>> getListMutableLiveData() {
        return mListMutableLiveData;
    }

    public void fetchCampaigns(){
        CampaignsSyncTask.sync(sContext,mListMutableLiveData);
    }
}
