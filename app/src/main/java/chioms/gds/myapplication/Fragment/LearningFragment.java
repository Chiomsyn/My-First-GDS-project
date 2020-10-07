package chioms.gds.myapplication.Fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import chioms.gds.myapplication.Activities.MainActivity;
import chioms.gds.myapplication.Adapters.LearningRecyclerViewAdapter;
import chioms.gds.myapplication.Adapters.SkillsRecyclerViewAdapter;
import chioms.gds.myapplication.GetApi.LearnersApi_Get;
import chioms.gds.myapplication.GetApi.SkillsApi_Get;
import chioms.gds.myapplication.ListResponse.LearnersListResponse;
import chioms.gds.myapplication.ListResponse.SkillsListResponse;
import chioms.gds.myapplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningFragment extends Fragment {

    private static final String TAG = SkillsFragment.class.getSimpleName();
    List<SkillsListResponse> mListResponses;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getLearnersListData();
     View view = inflater.inflate(R.layout.learners_item_list, container, false);

        // Set the adapter

        recyclerView = view.findViewById(R.id.learnersList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SkillsRecyclerViewAdapter(mListResponses, this));

        return view;

    }


    private void getLearnersListData(){
        (SkillsApi_Get.getClient().getSkillsList()).enqueue(new Callback<List<SkillsListResponse>>() {
            @Override
            public void onResponse(Call<List<SkillsListResponse>> call, Response<List<SkillsListResponse>> response) {
                mListResponses = response.body();

                    Log.d(TAG, mListResponses.size() + "size of response");
                    Toast.makeText(getActivity(), response.body().toString(), Toast.LENGTH_LONG).show();
                }

            @Override
            public void onFailure(Call<List<SkillsListResponse>> call, Throwable t) {
                Toast.makeText(getActivity(), "An error happened",Toast.LENGTH_LONG).show();
                Log.e(TAG,t.toString());
            }
        });
    }

}
