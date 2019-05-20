package com.example.instascaler.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.instascaler.InjectorUtils;
import com.example.instascaler.data.database.Campaign;
import com.example.instascaler.data.network.CampaignApi;
import com.example.instascaler.data.network.NetworkUtils;
import com.example.instascaler.R;
import com.example.instascaler.ui.main.MainActivityViewModel;
import com.example.instascaler.ui.main.MainViewModelFactory;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChartFragment() {
        // Required empty public constructor
    }

    public static ChartFragment newInstance() {
        return new ChartFragment();
    }

    @BindView(R.id.bar_chart_fragment_chart_id)
    BarChart mBarChart;

    @BindView(R.id.pie_chart_fragment_chart_id)
    PieChart mPieChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private CampaignApi mCampaignApi ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chart, container, false);
        ButterKnife.bind(this,view);

        MainViewModelFactory mainViewModelFactory = InjectorUtils.provideMainViewModelFactory(getContext());

        MainActivityViewModel mainActivityViewModel =
                ViewModelProviders.of(this,mainViewModelFactory).get(MainActivityViewModel.class);

        mainActivityViewModel.getListLiveData().observe(this, new Observer<List<Campaign>>() {
            @Override
            public void onChanged(List<Campaign> campaigns) {
                displayReportBarChart(campaigns);

            }
        });

        return view;
    }

   /* private void displayCharts(String methodName,List<Campaign> campaignList){
        if (methodName.equalsIgnoreCase("bar_chart")){
            displayReportBarChart(campaignList);
        }else {
            displayReportPieChart(campaignList);
        }
    } */

    private void displayReportBarChart(List<Campaign> campaignList){
        List<BarEntry> barEntryList = new ArrayList<>();
        for (Campaign campaign : campaignList){
            if (campaign.getBudget()<80){
                campaign.setBudget(campaign.getBudget() + 300);
            }
            barEntryList.add(new BarEntry(campaign.getBudget(),campaign.getBudget()));
        }

        BarDataSet barDataSet = new BarDataSet(barEntryList,"Budget");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData barData = new BarData(barDataSet);

        barData.setBarWidth(28.0f);

        mBarChart.setVisibility(View.VISIBLE);

        mBarChart.animateY(5000);
        mBarChart.setData(barData);
      //  mBarChart.setFitBars(true);

        Description description = new Description();

        description.setText("Budget Of Each Campaign");

        mBarChart.setDescription(description);
       // mBarChart.invalidate();
    }

    private void displayReportPieChart(List<Campaign> campaignList){

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
