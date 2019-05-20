package com.example.instascaler.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instascaler.R;
import com.example.instascaler.data.database.Campaign;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CampaignsAdapter  extends RecyclerView.Adapter<CampaignsAdapter.CampaignsViewHolder>{

    private List<Campaign> mCampaigns;

    public CampaignsAdapter(List<Campaign> campaignList){
        mCampaigns = campaignList;
    }


    @NonNull
    @Override
    public CampaignsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutResource = R.layout.campaign_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutResource,parent,false);
        CampaignsViewHolder campaignsViewHolder = new CampaignsViewHolder(view);
        return campaignsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CampaignsViewHolder holder, int position) {
        Campaign campaign = mCampaigns.get(position);
        holder.mTextViewCampaignName.setText(campaign.getName());
        holder.mTextViewCampaignCountry.setText(campaign.getCountry());
        holder.mTextViewCampaignGoal.setText(campaign.getGoal());
        holder.mTextViewCampaignCategory.setText(campaign.getCategory());
        holder.mTextViewCampaignBudget.setText(String.valueOf(campaign.getBudget()) + "$");

    }

    @Override
    public int getItemCount() {
        if (mCampaigns != null && mCampaigns.size() > 0) return mCampaigns.size();
        return 0;
    }

    public List<Campaign> getCampaigns() {
        return mCampaigns;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        mCampaigns = campaigns;
        notifyDataSetChanged();
    }

    class CampaignsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_campaign_name)
        TextView mTextViewCampaignName;

        @BindView(R.id.tv_campaign_country)
        TextView mTextViewCampaignCountry;

        @BindView(R.id.tv_campaign_goal)
        TextView mTextViewCampaignGoal;

        @BindView(R.id.tv_campaign_category)
        TextView mTextViewCampaignCategory;

        @BindView(R.id.tv_campaign_budget)
        TextView mTextViewCampaignBudget;

        public CampaignsViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }


    }
}
