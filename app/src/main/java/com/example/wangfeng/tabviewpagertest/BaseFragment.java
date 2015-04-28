package com.example.wangfeng.tabviewpagertest;

import android.support.v4.app.Fragment;
import android.widget.AbsListView;

/**
 * Created by wangfeng on 15/4/28.
 */
public abstract class BaseFragment extends Fragment implements ScrollTabHolderListener {


  protected ScrollTabHolderListener mScrollTabHolderListener;

  public void setScrollTabHolderListener(ScrollTabHolderListener scrollTabHolderListener) {
    mScrollTabHolderListener = scrollTabHolderListener;
  }

  @Override
  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition) {

  }
}
