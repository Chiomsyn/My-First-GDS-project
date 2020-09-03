package chioms.gds.myapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import chioms.gds.myapplication.Adapters.LearningRecyclerViewAdapter;
import chioms.gds.myapplication.GetApi.LearnersApi_Get;
import chioms.gds.myapplication.ListResponse.LearnersListResponse;
import chioms.gds.myapplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningFragment extends Fragment {

    private static final String TAG = SkillsFragment.class.getSimpleName();
    List<LearnersListResponse> mListResponses;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learners_item_list, container, false);

        // Set the adapter
         recyclerView = view.findViewById(R.id.learnersList);
         getLearnersListData();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new LearningRecyclerViewAdapter(mListResponses, this));
        return view;
    }

    private void getLearnersListData(){
        (LearnersApi_Get.getClient().getLearnersList()).enqueue(new Callback<List<LearnersListResponse>>() {
            @Override
            public void onResponse(Call<List<LearnersListResponse>> call, Response<List<LearnersListResponse>> response) {
                mListResponses = response.body();
                Log.d(TAG,mListResponses.size()+"size of response");
                Toast.makeText(getActivity(), response.body().toString(), Toast.LENGTH_LONG).show();
            }


            @Override
            public void onFailure(Call<List<LearnersListResponse>> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(),Toast.LENGTH_LONG);
                Log.e(TAG,t.toString());
            }
        });
    }

}
