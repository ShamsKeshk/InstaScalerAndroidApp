package com.example.instascaler.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.instascaler.data.CampaignsRepository;
import com.example.instascaler.data.database.Campaign;

import java.util.List;

public class MainActivityViewModel extends ViewModel {


    private final CampaignsRepository mCampaignsRepository;
    private final LiveData<List<Campaign>> mListLiveData;


    public MainActivityViewModel(CampaignsRepository campaignsRepository){
        this.mCampaignsRepository = campaignsRepository;
        mListLiveData = mCampaignsRepository.getCampaignsList();
    }

    public LiveData<List<Campaign>> getListLiveData() {
        return mListLiveData;
    }
}
