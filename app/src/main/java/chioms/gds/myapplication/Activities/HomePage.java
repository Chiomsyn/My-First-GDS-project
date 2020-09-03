package chioms.gds.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

import chioms.gds.myapplication.Fragment.LearningFragment;
import chioms.gds.myapplication.R;
import chioms.gds.myapplication.Fragment.SkillsFragment;
import chioms.gds.myapplication.Adapters.ViewPagerAdapter;

public class HomePage extends AppCompatActivity {


    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private Button mButton;
    private LearningFragment mLearningFragment;
    private SkillsFragment mSkillIQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tabWidget);
        mButton = findViewById(R.id.submit_btn);

        addFragments();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePage.this, SubmissionPage.class));
            }
        });

    }

    private void addFragments() {
        mLearningFragment = new LearningFragment();
        mSkillIQ = new SkillsFragment();

        mTabLayout.setupWithViewPager(mViewPager);
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        mViewPagerAdapter.addFragment(mLearningFragment);
        mViewPagerAdapter.addFragment(mSkillIQ);
        mViewPager.setAdapter(mViewPagerAdapter);
    }
}
