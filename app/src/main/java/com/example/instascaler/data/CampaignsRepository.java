package com.example.instascaler.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.instascaler.AppExecutors;
import com.example.instascaler.data.database.Campaign;
import com.example.instascaler.data.database.CampaignDao;
import com.example.instascaler.data.network.CampaignNetworkDataSource;

import java.util.List;

public class CampaignsRepository {

    private static final Object LOCK = new Object();

    private static CampaignsRepository sCampaignsRepository;

    private CampaignDao mCampaignDao;
    private CampaignNetworkDataSource mCampaignNetworkDataSource;
    private AppExecutors mAppExecutors;

    private CampaignsRepository(CampaignDao campaignDao,
                                CampaignNetworkDataSource campaignNetworkDataSource ,
                                AppExecutors appExecutors){
        this.mCampaignDao = campaignDao;
        this.mCampaignNetworkDataSource = campaignNetworkDataSource;
        this.mAppExecutors = appExecutors;

        LiveData<List<Campaign>> listLiveData =
                mCampaignNetworkDataSource.getListMutableLiveData();

        listLiveData.observeForever(new Observer<List<Campaign>>() {
            @Override
            public void onChanged(List<Campaign> campaigns) {
                mAppExecutors.getDiskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        mCampaignDao.bulkInsert(campaigns);
                    }
                });
            }
        });
    }

    public static CampaignsRepository getInstance(CampaignDao campaignDao,
                                                               CampaignNetworkDataSource campaignNetworkDataSource ,
                                                               AppExecutors appExecutors){

        if (sCampaignsRepository == null){
            synchronized (LOCK){
                sCampaignsRepository = new CampaignsRepository(campaignDao,campaignNetworkDataSource,appExecutors);
            }
        }
        return sCampaignsRepository;

    }

    public LiveData<List<Campaign>> getCampaignsList(){
        mCampaignNetworkDataSource.fetchCampaigns();
        return mCampaignDao.getCampaignsList();
    }


}
