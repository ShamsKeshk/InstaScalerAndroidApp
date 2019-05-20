package com.example.instascaler;

import android.content.Context;

import com.example.instascaler.data.CampaignsRepository;
import com.example.instascaler.data.database.CampaignsDatabase;
import com.example.instascaler.data.network.CampaignNetworkDataSource;
import com.example.instascaler.ui.main.MainActivityViewModel;
import com.example.instascaler.ui.main.MainViewModelFactory;

public class InjectorUtils {

    public static CampaignsDatabase provideCampaignsDatabase(Context context){
        return CampaignsDatabase.getInstance(context);
    }

    public static CampaignNetworkDataSource provideCampaignNetworkDataSource(Context context){
        return CampaignNetworkDataSource.getInstance(context);
    }

    public static AppExecutors provideAppExecutor(){
        return AppExecutors.getInstance();
    }

    public static CampaignsRepository provideRepository(Context context){
        return CampaignsRepository.
                getInstance(provideCampaignsDatabase(context).
                        campaignDao(),provideCampaignNetworkDataSource(context),provideAppExecutor());
    }

    public static MainActivityViewModel provideMainActivityViewModel(Context context){
        return new MainActivityViewModel(provideRepository(context));
    }

    public static MainViewModelFactory provideMainViewModelFactory(Context context){
        return new MainViewModelFactory(provideRepository(context));
    }
}
