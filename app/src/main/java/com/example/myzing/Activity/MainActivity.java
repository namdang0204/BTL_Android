package com.example.myzing.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myzing.Adapter.MainViewPagerAdapter;
import com.example.myzing.DAO.SongDAO;
import com.example.myzing.Fragment.Fragment_Home;
import com.example.myzing.Fragment.Fragment_More;
import com.example.myzing.Fragment.Fragment_MyMusic;
import com.example.myzing.Model.User;
import com.example.myzing.R;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    MainViewPagerAdapter mainViewPagerAdapter;
    SearchView searchView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();

        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        anhXa();
        addFragment();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
//                new SongDAO().getSongSearch("em");
//                String a = new SongDAO().getSongSearch("em").get(0).getNameSong();
//                Log.d("aa",a);
//                Toast.makeText(MainActivity.this, a , Toast.LENGTH_SHORT).show();
                return true;
            }
        });

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
        searchView = findViewById(R.id.search_view);
//        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
//
//        setSupportActionBar();
//        toolbar.setTitle("");


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        searchView = (SearchView) menuItem.getActionView();


//        searchView.setMaxWidth(Integer.MAX_VALUE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                Toast.makeText(MainActivity.this, s , Toast.LENGTH_SHORT).show();
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                Toast.makeText(MainActivity.this, s , Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
        return true;
    }
}
