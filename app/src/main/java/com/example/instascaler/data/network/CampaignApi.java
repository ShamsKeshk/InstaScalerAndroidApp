package com.example.instascaler.data.network;

import com.example.instascaler.data.database.Campaign;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface CampaignApi {

    @GET("/home")
    Call<String> getRoot();

    @GET("/campaigns")
    Call<List<Campaign>> getCampaigns();

    @GET("campaigns/{campaignId}")
    Call<Campaign> getCampaignById(@Path("campaignId") int postId);

    @POST("/campaigns/search")
    Call<List<Campaign>> searchCampaigns(@FieldMap Map<String,String> searchBody);

    @POST("/campaigns")
    Call<Campaign> createCampaign(@Body Campaign campaign);

    @PUT("/campaigns/{campaignId}")
    Call<Campaign> updateCampaign(@Path("campaignId") int postId, @Body Campaign campaign);

    @DELETE("/campaigns/{campaignId}")
    Call<Boolean> deleteCampaign(@Path("campaignId") int postId);


}
