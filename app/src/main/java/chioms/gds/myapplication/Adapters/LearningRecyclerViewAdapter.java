package chioms.gds.myapplication.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import chioms.gds.myapplication.ListResponse.LearnersListResponse;
import chioms.gds.myapplication.R;

import java.util.List;

public class LearningRecyclerViewAdapter extends RecyclerView.Adapter<LearningRecyclerViewAdapter.LearnersViewHolder> {

    Context mContext;
     private List<LearnersListResponse> mValues;


    public LearningRecyclerViewAdapter(List<LearnersListResponse> items, Context Context) {
            mValues = items;
            mContext = Context;

    }

    @NonNull
    @Override
    public LearnersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.learners_item, parent, false);
        return new LearnersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LearnersViewHolder holder, int position) {
        LearnersListResponse learnersListResponse = mValues.get(position);
               String image_url  = learnersListResponse.getBadgeUrl();
                String mlearnHourstxt = learnersListResponse.getHour() + (R.string.learn_txt) + learnersListResponse.getCountry();

        Picasso.with(mContext)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.mTopLearnerImg);

                holder.mLearnerName.setText(learnersListResponse.getName());
                holder.mLearnHours.setText(mlearnHourstxt);

    }

    @Override
    public int getItemCount() {
              return 2;
    }

    public class LearnersViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mTopLearnerImg;
        public final TextView mLearnerName;
        public final TextView mLearnHours;

        public LearnersViewHolder(View view) {
            super(view);
            mTopLearnerImg = view.findViewById(R.id.topLearner_img);
            mLearnerName = view.findViewById(R.id.learner_name);
            mLearnHours = view.findViewById(R.id.learn_hours);
        }
    }
}
