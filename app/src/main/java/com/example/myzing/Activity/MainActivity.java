package com.example.myzing.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myzing.Adapter.MainViewPagerAdapter;
import com.example.myzing.Fragment.Fragment_Home;
import com.example.myzing.Fragment.Fragment_More;
import com.example.myzing.Fragment.Fragment_MyMusic;
import com.example.myzing.Model.User;
import com.example.myzing.R;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    MainViewPagerAdapter mainViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();

        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        anhXa();
        addFragment();

        // Khong refresh lai fragment
        int limit = (mainViewPagerAdapter.getCount() > 1 ? mainViewPagerAdapter.getCount() - 1 : 1);
        viewPager.setOffscreenPageLimit(limit);

    }

    private void addFragment() {

        mainViewPagerAdapter.addFragment(new Fragment_Home(), "Home");
        mainViewPagerAdapter.addFragment(new Fragment_MyMusic(), "MyMusic");
        mainViewPagerAdapter.addFragment(new Fragment_More(), "More");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icon_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_user);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_menu);
    }

    private void anhXa() {
        viewPager = (ViewPager) findViewById(R.id.my_view_page);
        tabLayout = (TabLayout) findViewById(R.id.my_tablayout);
    }

    public void initPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                //Permisson don't granted
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Toast.makeText(MainActivity.this, "Permission isn't granted ", Toast.LENGTH_SHORT).show();
                }
                // Permisson don't granted and dont show dialog again.
                else {
                    Toast.makeText(MainActivity.this, "Permisson don't granted and dont show dialog again ", Toast.LENGTH_SHORT).show();
                }
                //Register permission
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

            }
        }
    }

}
