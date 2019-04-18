package com.example.myzing.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myzing.Adapter.MainViewPagerAdapter;
import com.example.myzing.Fragment.Fragment_Home;
import com.example.myzing.Fragment.Fragment_More;
import com.example.myzing.Fragment.Fragment_MyMusic;
import com.example.myzing.R;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addFragment();
    }

    private void addFragment() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new Fragment_Home(), "Home");
        mainViewPagerAdapter.addFragment(new Fragment_MyMusic(), "MyMusic");
        mainViewPagerAdapter.addFragment(new Fragment_More(), "More");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_user);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_menu);
    }

    private void addControls() {
        viewPager = (ViewPager) findViewById(R.id.my_view_page);
        tabLayout = (TabLayout) findViewById(R.id.my_tablayout);
    }
}
