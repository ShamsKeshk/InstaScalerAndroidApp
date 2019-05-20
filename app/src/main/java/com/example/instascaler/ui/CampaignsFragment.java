package com.example.instascaler.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CampaignsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CampaignsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CampaignsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CampaignsFragment() {
        // Required empty public constructor
    }


    public static CampaignsFragment newInstance() {

        return new CampaignsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @BindView(R.id.rv_campaigns_list)
    RecyclerView mRecyclerView;

    private CampaignsAdapter mCampaignsAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_campaigns, container, false);
        ButterKnife.bind(this,view);

        MainViewModelFactory mainViewModelFactory = InjectorUtils.provideMainViewModelFactory(getContext());
        MainActivityViewModel mainActivityViewModel =
                ViewModelProviders.of(this,mainViewModelFactory).get(MainActivityViewModel.class);

        mCampaignsAdapter = new CampaignsAdapter(null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mCampaignsAdapter);


        mainActivityViewModel.getListLiveData().observe(this, new Observer<List<Campaign>>() {
            @Override
            public void onChanged(List<Campaign> campaigns) {
                mCampaignsAdapter.setCampaigns(campaigns);
                Toast.makeText(getContext(),campaigns.get(0).getName(),Toast.LENGTH_LONG).show();

            }
        });

        return view;
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
