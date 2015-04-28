package com.example.wangfeng.tabviewpagertest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements ScrollTabHolderListener, ViewPager.OnPageChangeListener {


  private ViewPager            viewPager;
  private PagerSlidingTabStrip pagerSlidingTabStrip;
  private KenBurnsSupportView  kenBurnsSupportView;
  private MyPagerAdapter       myPagerAdapter;
  private View                 headerView;

  private int headerHeight;
  private int minHeaderTranslation;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    headerHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
    minHeaderTranslation = -getResources().getDimensionPixelSize(R.dimen.min_header_height);

    setContentView(R.layout.activity_main);
    viewPager = (ViewPager) findViewById(R.id.pager);
    pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
    kenBurnsSupportView = (KenBurnsSupportView) findViewById(R.id.header_picture);
    headerView = findViewById(R.id.header);
    myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
    List<SimpleListFragment> listFragments = new ArrayList<>();
    listFragments.add(new SimpleListFragment());
    listFragments.add(new SimpleListFragment());
    myPagerAdapter.setFragments(listFragments);
    pagerSlidingTabStrip.setShouldExpand(true);
    viewPager.setAdapter(myPagerAdapter);
    pagerSlidingTabStrip.setViewPager(viewPager);
    kenBurnsSupportView.setResourceIds(R.mipmap.ic_launcher, R.mipmap.pic0);
    myPagerAdapter.setScrollTabHolderListener(this);
    pagerSlidingTabStrip.setOnPageChangeListener(this);
  }

  @Override
  public void onPageScrolled(int i, float v, int i2) {
    //nothing
  }

  @Override
  public void onPageSelected(int i) {
    SparseArrayCompat<ScrollTabHolderListener> scrollTabHolders = myPagerAdapter.getScrollTabHolderListenerSparseArrayCompat();
    ScrollTabHolderListener currentHolder = scrollTabHolders.valueAt(i);
    currentHolder.adjustScroll((int) (headerView.getHeight() + ViewHelper.getTranslationY(headerView)));
  }

  @Override
  public void onPageScrollStateChanged(int i) {
    //nothing
  }

  @Override
  public void adjustScroll(int scrollHeight) {
    //nothing
  }

  @Override
  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {
    if (viewPager.getCurrentItem() == pagePosition) {
      int scrollY = getScrollY(view);
      ViewHelper.setTranslationY(headerView, Math.max(-scrollY, minHeaderTranslation));
    }
  }

  private int getScrollY(AbsListView view) {
    View c = view.getChildAt(0);
    if (c == null) {
      return 0;
    }

    int firstVisiblePosition = view.getFirstVisiblePosition();
    int top = c.getTop();

    int headerHeight = 0;
    if (firstVisiblePosition >= 1) {
      headerHeight = this.headerHeight;
    }

    return -top + firstVisiblePosition * c.getHeight() + headerHeight;
  }


  public class MyPagerAdapter extends FragmentPagerAdapter {
    private SparseArrayCompat<ScrollTabHolderListener> scrollTabHolderListenerSparseArrayCompat;
    private List<SimpleListFragment>                   listFragments;
    private ScrollTabHolderListener                    scrollTabHolderListener;
    String[] ss = {"nihao", "hello"};

    public MyPagerAdapter(FragmentManager fm) {
      super(fm);
      scrollTabHolderListenerSparseArrayCompat = new SparseArrayCompat<ScrollTabHolderListener>();
    }

    @Override
    public Fragment getItem(int i) {
      BaseFragment fragment = (BaseFragment) SimpleListFragment.newInstance(i);
      scrollTabHolderListenerSparseArrayCompat.put(i, fragment);
      if (this.scrollTabHolderListener != null) {
        fragment.setScrollTabHolderListener(this.scrollTabHolderListener);
      }
      return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return ss[position];
    }

    @Override
    public int getCount() {
      return listFragments.size();
    }

    public void setFragments(List<SimpleListFragment> listFragments) {
      this.listFragments = listFragments;
    }

    public void setScrollTabHolderListener(ScrollTabHolderListener scrollTabHolderListener) {
      this.scrollTabHolderListener = scrollTabHolderListener;
    }

    public SparseArrayCompat<ScrollTabHolderListener> getScrollTabHolderListenerSparseArrayCompat() {
      return scrollTabHolderListenerSparseArrayCompat;
    }
  }

}