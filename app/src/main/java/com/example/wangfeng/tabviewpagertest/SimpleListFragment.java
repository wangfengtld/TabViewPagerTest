package com.example.wangfeng.tabviewpagertest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangfeng on 15/4/28.
 */
public class SimpleListFragment extends BaseFragment implements AbsListView.OnScrollListener {

  private static final String ARG_POSITION = "position";
  private ListView listView;

  private int mPosition;

  public static Fragment newInstance(int position) {
    SimpleListFragment f = new SimpleListFragment();
    Bundle b = new Bundle();
    b.putInt(ARG_POSITION, position);
    f.setArguments(b);
    return f;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      mPosition = getArguments().getInt(ARG_POSITION);
    } else {
      mPosition = savedInstanceState.getInt(ARG_POSITION);
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt(ARG_POSITION, mPosition);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_simple_list, container, false);
    listView = (ListView) view.findViewById(R.id.listview);
    View placeHolderView = inflater.inflate(R.layout.view_header_placeholder, listView, false);
    listView.addHeaderView(placeHolderView);
    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    List<String> data = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      data.add(i + "");
    }
    listView.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, data));
    listView.setOnScrollListener(this);
  }

  @Override
  public void adjustScroll(int scrollHeight) {
//    Log.d("message", scrollHeight + " -->" + listView.getFirstVisiblePosition());
//    if (scrollHeight == 0 && listView.getFirstVisiblePosition() >= 1) {
//      return;
//    }
//    listView.setSelectionFromTop(1, scrollHeight);

    if (scrollHeight != 0 && listView.getFirstVisiblePosition() == 0) {
      listView.setSelectionFromTop(1, scrollHeight);
    }
  }

  @Override
  public void onScrollStateChanged(AbsListView absListView, int i) {

  }

  @Override
  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    if (mScrollTabHolderListener != null)
      mScrollTabHolderListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount, mPosition);
  }
}
