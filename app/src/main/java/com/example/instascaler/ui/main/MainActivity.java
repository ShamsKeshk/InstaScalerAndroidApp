package com.example.instascaler.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instascaler.R;
import com.example.instascaler.ui.CampaignsFragment;
import com.example.instascaler.ui.ChartFragment;
import com.example.instascaler.ui.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements
        CampaignsFragment.OnFragmentInteractionListener ,ChartFragment.OnFragmentInteractionListener{

    private static final String BASE_URL = "https://ngkc0vhbrl.execute-api.eu-west-1.amazonaws.com/";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        mViewPagerAdapter.addFragment(ChartFragment.newInstance(),"Charts");
        mViewPagerAdapter.addFragment(CampaignsFragment.newInstance(),"Campaigns");


        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
