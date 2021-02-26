package com.example.administrator.myapplication.tool;

/**
 * Created by 86188 on 2019/12/11.
 */

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceGridItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 距离
     */
    private int space;
    /**
     * 列数
     */
    private int spanNum;
    /**
     * 方向
     */
    private int direction;

    public SpaceGridItemDecoration(int space, int spanNum, int direction) {
        this.space = space;
        this.spanNum = spanNum;
        this.direction = direction;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if (space == 0) {
            return;
        }
        if (direction == LinearLayoutManager.VERTICAL) {
            if (parent.getChildLayoutPosition(view) < spanNum) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
//        if (parent.getChildLayoutPosition(view) % spanNum == 0) {
//            outRect.left = space;
//        } else {
            outRect.left = 0;
//        }

        } else {
            outRect.left = 0;
            outRect.top = 0;
        }

        outRect.right = space;
        outRect.bottom = space;

    }
}

