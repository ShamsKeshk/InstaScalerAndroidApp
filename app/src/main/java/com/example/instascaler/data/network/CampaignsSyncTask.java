package com.example.instascaler.data.network;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.instascaler.InjectorUtils;
import com.example.instascaler.data.database.Campaign;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampaignsSyncTask {


    public static void  sync(final Context context, MutableLiveData<List<Campaign>> listMutableLiveData){

        Call<List<Campaign>> campaignApi = NetworkUtils.getInstance().initRetrofit().create(CampaignApi.class).getCampaigns();

        campaignApi.enqueue(new Callback<List<Campaign>>() {
            @Override
            public void onResponse(Call<List<Campaign>> call, Response<List<Campaign>> response) {
                if (response.isSuccessful()){
                    listMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Campaign>> call, Throwable t) {

            }
        });

    }
}
