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

import chioms.gds.myapplication.ListResponse.SkillsListResponse;
import chioms.gds.myapplication.R;

import java.util.List;

public class SkillsRecyclerViewAdapter extends RecyclerView.Adapter<SkillsRecyclerViewAdapter.SkillsViewHolder> {

    Fragment mFragment;
    private final List<SkillsListResponse> mValues;
    private SkillsListResponse mResponse;

    public SkillsRecyclerViewAdapter(List<SkillsListResponse> values, Fragment fragment) {
        mValues = values;
        mFragment = fragment;
    }

    @NonNull
    @Override
    public SkillsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skills_item, parent, false);
        return new SkillsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SkillsViewHolder holder, int position) {
        mResponse =  mValues.get(position);
        String image_url =mResponse.getBadgeUrl();
        String mSkillsScoretxt = mResponse.getScore() + " skill IQ Score, " + mResponse.getCountry();

        Context context = mFragment.getContext();

        Picasso.with(context)
                .load(image_url)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(holder.mTopSkillsImg);

        holder.mSkillsName.setText(mResponse.getName());
        holder.miqScore.setText(mSkillsScoretxt);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class SkillsViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mTopSkillsImg;
        public final TextView mSkillsName;
        public final TextView miqScore;


        public SkillsViewHolder(View view) {
            super(view);
            mTopSkillsImg = view.findViewById(R.id.skill_iq_img);
            mSkillsName = view.findViewById(R.id.top_skills_name);
            miqScore = view.findViewById(R.id.iq_score);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + miqScore.getText() + "'";
        }
    }
}
