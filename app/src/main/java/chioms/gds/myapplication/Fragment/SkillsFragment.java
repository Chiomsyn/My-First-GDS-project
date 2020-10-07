package chioms.gds.myapplication.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import chioms.gds.myapplication.Adapters.SkillsRecyclerViewAdapter;
import chioms.gds.myapplication.GetApi.LearnersApi_Get;
import chioms.gds.myapplication.GetApi.SkillsApi_Get;
import chioms.gds.myapplication.ListResponse.SkillsListResponse;
import chioms.gds.myapplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillsFragment extends Fragment {

    private static final String TAG = SkillsFragment.class.getSimpleName();
    List<SkillsListResponse> mListResponses;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.skills_item_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set the adapter
        RecyclerView recyclerView = view.findViewById(R.id.skills_list);
        getSkillsListData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new SkillsRecyclerViewAdapter(mListResponses, this));
    }

    private void getSkillsListData() {
        (SkillsApi_Get.getClient().getSkillsList()).enqueue(new Callback<List<SkillsListResponse>>() {
            @Override
            public void onResponse(Call<List<SkillsListResponse>> call, Response<List<SkillsListResponse>> response) {
                mListResponses =response.body();
                Log.d(TAG,mListResponses.size()+"size of response");
                Toast.makeText(getActivity(), response.body().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<SkillsListResponse>> call, Throwable t) {
                Log.e(TAG,t.toString());

            }
        });
    }


}
