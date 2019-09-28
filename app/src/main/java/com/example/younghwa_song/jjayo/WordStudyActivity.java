package com.example.younghwa_song.jjayo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import me.relex.circleindicator.CircleIndicator;

public class WordStudyActivity extends AppCompatActivity {

    FragmentStatePagerAdapter fragmentStatePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_study);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //뒤로가기 버튼 생성
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white); // 커스텀 뒤로가기
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //셋팅버튼 눌렀을 때

        toolbar.findViewById(R.id.setting_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

//홈버튼
        toolbar.findViewById(R.id.home_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final LinearLayout drawLayout = (LinearLayout) findViewById(R.id.draw_layout);
        final MyView m = new MyView(this);
        drawLayout.addView(m);

        fragmentStatePagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        ViewPager vPager = (ViewPager) findViewById(R.id.vpPager);
        vPager.setAdapter(fragmentStatePagerAdapter);


        //전체 지우기
        Button clear_Drawlayout = (Button) findViewById(R.id.clear_btn);
        clear_Drawlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.points.clear();
                m.invalidate();
            }
        });


        //next 버튼
        Button next_btn = (Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WordQuizActivity.class);
                startActivity(intent);
            }
        });


        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(vPager);

        ImageView word = (ImageView) findViewById(R.id.word_gif);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(word);
        Glide.with(this).load(R.drawable.pai).into(gifImage);

    }

    public static class MyPagerAdapter extends FragmentStatePagerAdapter {

        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new WordFragment1();
                case 1:
                    return new WordFragment2();
                case 2:
                    return new WordFragment3();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {

            return position + "";
        }
    }

}
