package com.module.sayem.foodculture.utils;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.module.sayem.foodculture.R;
import com.module.sayem.foodculture.storage.roomDB.AppDatabase;
import com.module.sayem.foodculture.ui.adapters.InfoListAdapter;

import es.dmoral.toasty.Toasty;


/**
 * Created by A.S.M Sayem on 2/20/2018.
 */

public class Utility {

    public boolean swipe_right2Left = false;

    public static void FontViewBold(Context cxt, TextView textView){
        Typeface typeface = Typeface.createFromAsset(cxt.getAssets(), "fonts/Ubuntu-Bold.ttf");
        textView.setTypeface(typeface);
    }
    public static void FontViewRegular(Context cxt, TextView textView){
        Typeface typeface = Typeface.createFromAsset(cxt.getAssets(), "fonts/Ubuntu-Regular.ttf");
        textView.setTypeface(typeface);
    }
    public static void FontViewMedium(Context cxt, TextView textView){
        Typeface typeface = Typeface.createFromAsset(cxt.getAssets(), "fonts/Ubuntu-Medium.ttf");
        textView.setTypeface(typeface);
    }

    public static void ToastyConfig() {
        Toasty.Config.getInstance().setErrorColor(hexToInt("ff3333"))
                .setInfoColor(hexToInt("22313F"))
                .setSuccessColor(hexToInt("1E824C"))
                .apply();
    }

    private static int hexToInt(String hex) {
        return (Integer.parseInt( hex.substring( 0,2 ), 16) << 24) + Integer.parseInt( hex.substring( 2 ), 16);
    }

    public AppDatabase AppDB(Context cxt) {
        return Room.databaseBuilder(cxt,
                AppDatabase.class, "app-database")
                .allowMainThreadQueries()
                .build();
    }

    public void setUpItemTouchHelper(Context context, RecyclerView mRecyclerView) {

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

            // we want to cache these and not allocate anything repeatedly in the onChildDraw method
            Drawable background;
            Drawable xMark;
            int xMarkMargin;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(Color.RED);
                xMark = ContextCompat.getDrawable(context, R.drawable.ic_delete_24dp);
                xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                xMarkMargin = (int) context.getResources().getDimension(R.dimen.ic_clear_margin);
                initiated = true;
            }

            // not important, we don't want drag & drop
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                InfoListAdapter testAdapter = (InfoListAdapter) recyclerView.getAdapter();
                if (testAdapter.isUndoOn() && testAdapter.isPendingRemoval(position)) {
                    return 0;
                }
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int swipedPosition = viewHolder.getAdapterPosition();
                InfoListAdapter adapter = (InfoListAdapter) mRecyclerView.getAdapter();

                if (swipeDir == ItemTouchHelper.LEFT) {
                    swipe_right2Left = true;
                    Log.d("swiped", "onSwipedL: " + swipeDir);
                    boolean undoOn = adapter.isUndoOn();
                    if (undoOn) {
                        adapter.pendingRemoval(swipedPosition);
                    } else {
                        adapter.remove(swipedPosition);
                    }
                } else if (swipeDir == ItemTouchHelper.RIGHT) {
                    Log.d("swiped", "onSwipedR: " + swipeDir);
                    adapter.shortListed(swipedPosition);
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;

                // not sure why, but this method get's called for viewholder that are already swiped away
                if (viewHolder.getAdapterPosition() == -1) {
                    // not interested in those
                    return;
                }

                if (!initiated) {
                    init();
                }

                // draw red background
                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(c);

                // draw x mark
                int itemHeight = itemView.getBottom() - itemView.getTop();
                int intrinsicWidth = xMark.getIntrinsicWidth();
                int intrinsicHeight = xMark.getIntrinsicWidth();

                int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
                int xMarkRight = itemView.getRight() - xMarkMargin;
                int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
                int xMarkBottom = xMarkTop + intrinsicHeight;
                xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

                xMark.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * We're gonna setup another ItemDecorator that will draw the red background in the empty space while the items are animating to thier new positions
     * after an item is removed.
     */
    public void setUpAnimationDecoratorHelper(RecyclerView mRecyclerView) {
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

            // we want to cache this and not allocate anything repeatedly in the onDraw method
            Drawable background;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(Color.WHITE);
                initiated = true;
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

                if (!initiated) {
                    init();
                }

                // only if animation is in progress
                if (parent.getItemAnimator().isRunning()) {

                    // some items might be animating down and some items might be animating up to close the gap left by the removed item
                    // this is not exclusive, both movement can be happening at the same time
                    // to reproduce this leave just enough items so the first one and the last one would be just a little off screen
                    // then remove one from the middle

                    // find first child with translationY > 0
                    // and last one with translationY < 0
                    // we're after a rect that is not covered in recycler-view views at this point in time
                    View lastViewComingDown = null;
                    View firstViewComingUp = null;

                    // this is fixed
                    int left = 0;
                    int right = parent.getWidth();

                    // this we need to find out
                    int top = 0;
                    int bottom = 0;

                    // find relevant translating views
                    int childCount = parent.getLayoutManager().getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View child = parent.getLayoutManager().getChildAt(i);
                        if (child.getTranslationY() < 0) {
                            // view is coming down
                            lastViewComingDown = child;
                        } else if (child.getTranslationY() > 0) {
                            // view is coming up
                            if (firstViewComingUp == null) {
                                firstViewComingUp = child;
                            }
                        }
                    }

                    if (lastViewComingDown != null && firstViewComingUp != null) {
                        // views are coming down AND going up to fill the void
                        top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
                        bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
                    } else if (lastViewComingDown != null) {
                        // views are going down to fill the void
                        top = lastViewComingDown.getBottom() + (int) lastViewComingDown.getTranslationY();
                        bottom = lastViewComingDown.getBottom();
                    } else if (firstViewComingUp != null) {
                        // views are coming up to fill the void
                        top = firstViewComingUp.getTop();
                        bottom = firstViewComingUp.getTop() + (int) firstViewComingUp.getTranslationY();
                    }

                    //background.setBounds(left, top, right, bottom);
                    //background.draw(c);

                }
                super.onDraw(c, parent, state);
            }

        });
    }
}
