package com.example.instascaler.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.instascaler.data.CampaignsRepository;

public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private CampaignsRepository mCampaignsRepository;

    public MainViewModelFactory(CampaignsRepository campaignsRepository){
        this.mCampaignsRepository = campaignsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(mCampaignsRepository);
    }
}
