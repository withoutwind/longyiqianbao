package com.example.android.xiaolv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
public class MainActivity extends BaseActivity implements HomeFragment.OnFragmentInteractionListener,Repayment.OnFragmentInteractionListener,MainFragment.OnFragmentInteractionListener,BorrowingFragment.OnFragmentInteractionListener {


    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.ivmain_homepage)
    ImageView ivmainHomepage;
    @BindView(R.id.tvmain_homepage)
    TextView tvmainHomepage;
    @BindView(R.id.main_homepage)
    LinearLayout mainHomepage;
    @BindView(R.id.ivmain_bookshelf)
    ImageView ivmainBookshelf;
    @BindView(R.id.tvmain_bookshelf)
    TextView tvmainBookshelf;
    @BindView(R.id.main_bookshelf)
    LinearLayout mainBookshelf;
    @BindView(R.id.ivmain_search)
    ImageView ivmainSearch;
    @BindView(R.id.tvmain_search)
    TextView tvmainSearch;
    @BindView(R.id.main_search)
    RelativeLayout mainSearch;
    @BindView(R.id.ivmain_mine)
    ImageView ivmainMine;
    @BindView(R.id.tvmain_mine)
    TextView tvmainMine;
    @BindView(R.id.main_mine)
    LinearLayout mainMine;
    @BindView(R.id.menu)
    LinearLayout menu;
    /**
     * 用于对Fragment进行管理
     * 1
     */
    public FragmentManager fragmentManager;
    private int id;
    public HomeFragment homeFragment;
    public Repayment bookshelfFragment;
    public BorrowingFragment messageFragment;
    public MainFragment mineFragment;
    private Intent inten;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();
        init();
    }
    private void init() {
        homeFragment = HomeFragment.newInstance("","");
        addFragment(fragmentManager.beginTransaction(), R.id.frameLayout, homeFragment, false);
        initBottomMenu();
    }


    private void initBottomMenu() {
        id = R.id.frameLayout;
        mainHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                hideFragments(transaction);
                tvmainHomepage.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_delet_main));
                ivmainHomepage.setImageResource(R.drawable.icon_menu_1_pressed);
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance("","");
                    addFragment(transaction, id, homeFragment, false);
                } else {
                    showFragment(transaction, id, homeFragment, false);
                }
            }
        });
        mainBookshelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                hideFragments(transaction);
                tvmainBookshelf.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_delet_main));
                ivmainBookshelf.setImageResource(R.drawable.icon_menu_2_pressed);
                if (bookshelfFragment == null) {
                    bookshelfFragment =  Repayment.newInstance("","");
                    addFragment(transaction, id, bookshelfFragment, false);
                } else {
                    showFragment(transaction, id, bookshelfFragment, false);
                }
            }
        });




        mainSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                hideFragments(transaction);
                tvmainSearch.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_delet_main));
                ivmainSearch.setImageResource(R.drawable.icon_menu_3_pressed);
                if (messageFragment == null) {
                    messageFragment = BorrowingFragment.newInstance("","");
                    addFragment(transaction, id, messageFragment, false);
                } else {
                    showFragment(transaction, id, messageFragment, false);
                }
            }
        });
        mainMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!UserManageBean.isLogin(MainActivity.this)){
                    return;
                }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                hideFragments(transaction);
                tvmainMine.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_delet_main));
                ivmainMine.setImageResource(R.drawable.icon_menu_4_pressed);
                if (mineFragment == null) {
                    mineFragment = MainFragment.newInstance("","");
                    addFragment(transaction, id, mineFragment, false);
                } else {
                    showFragment(transaction, id, mineFragment, false);
                }
            }
        });
    }
    public void hideFragments(FragmentTransaction transaction) {
        if (messageFragment != null) {
            messageFragment.onResume();
        }

        hideFragment(transaction, homeFragment);
        hideFragment(transaction, bookshelfFragment);
        hideFragment(transaction, messageFragment);
        //hideFragment(transaction, writerFragment);
        hideFragment(transaction, mineFragment);
        tvmainHomepage.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_main));
        tvmainBookshelf.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_main));
        tvmainSearch.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_main));
        tvmainMine.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_main));
        ivmainHomepage.setImageResource(R.drawable.icon_menu_1_normal);
        ivmainBookshelf.setImageResource(R.drawable.icon_menu_2_normal);
        ivmainSearch.setImageResource(R.drawable.icon_menu_3_normal);
        ivmainMine.setImageResource(R.drawable.icon_menu_4_normal);

    }
    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //退出方法
    private long time = 0;

    private void exit() {
        //如果在两秒大于2秒
        if (System.currentTimeMillis() - time > 2000) {

            //获得当前的时间

            time = System.currentTimeMillis();

            Toast.makeText(this,"再点击一次退出应用程序",Toast.LENGTH_LONG).show();

        } else {
            //点击在两秒以内

            removeALLActivity();//执行移除所以Activity方法

        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (UserManageBean.isLogin()){
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            hideFragments(transaction);
            tvmainMine.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_delet_main));
            ivmainMine.setImageResource(R.drawable.icon_menu_4_pressed);
            if (mineFragment == null) {
                mineFragment = MainFragment.newInstance("","");
                addFragment(transaction, id, mineFragment, false);
            } else {
                showFragment(transaction, id, mineFragment, false);
            }
        }
    }
}
