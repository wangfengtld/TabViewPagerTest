package com.example.wangfeng.tabviewpagertest;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by wangfeng on 15/4/21.
 */
public class ViewHelper {
  public static void setScaleX(View view, float fromScale) {
    view.setScaleX(fromScale);
  }

  public static void setScaleY(View view, float fromScale) {
    view.setScaleY(fromScale);
  }

  public static void setTranslationX(View view, float fromTranslationX) {
    view.setTranslationX(fromTranslationX);
  }

  public static void setTranslationY(View view, float fromTranslationY) {
    view.setTranslationY(fromTranslationY);
  }

  public static void setAlpha(ImageView activeImageView, float v) {
    if (activeImageView != null) {
      activeImageView.setAlpha(v);
    }
  }

  public static float getTranslationY(View mHeader) {
    if (mHeader != null) {
      return mHeader.getTranslationY();
    }
    return 0;
  }
}
