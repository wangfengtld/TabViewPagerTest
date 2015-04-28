package com.example.wangfeng.tabviewpagertest;

import android.widget.AbsListView;

public interface ScrollTabHolderListener {

  void adjustScroll(int scrollHeight);

  void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount, int pagePosition);
}
