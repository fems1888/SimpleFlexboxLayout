package com.example.flexlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.MeasureSpec.AT_MOST;
import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @author Created by jackieyao on 2018/6/15 下午4:59.
 */

public class FlexboxLayout extends ViewGroup implements FlexContainer {
    private int[] childLeft;
    private int[] childRight;
    private int[] childTop;
    private int[] childBottom;
    private int marginLeft;
    private int marginTop;
    /**
     * 最小的宽度  如果比这个小就要换行
     */
    private int smallestWidth;
    /**
     * 临时变量 记录最边界的高度异常的值
     */
    private int usedHeightSpec;
    private int mJustifyContent;

    public FlexboxLayout(Context context) {
        this(context, null);

    }

    public FlexboxLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.FlexboxLayout, defStyleAttr, 0);
        marginLeft = (int) ta.getDimension(R.styleable.FlexboxLayout_Flex_Margin_Left, 30);
        marginTop = (int) ta.getDimension(R.styleable.FlexboxLayout_Flex_Margin_Top, 30);
        mJustifyContent = ta.getInt(R.styleable.FlexboxLayout_JustifyContent, JustifyContent.FLEX_START);
        smallestWidth = 2 * marginLeft;
        ta.recycle();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (childLeft == null) {
            childLeft = new int[getChildCount()];
            childTop = new int[getChildCount()];
            childRight = new int[getChildCount()];
            childBottom = new int[getChildCount()];
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int childWidthSpec;
        int childHeightSpec;
        int maxUsedWidth = 0;
        int usedWidth = 0;
        int usedHeight = 0;
        //child的顶部坐标
        int locationHeight = 0;
        int selfWidthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int selfWidthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int selfHeightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        for (int i = 0; i < childCount; i++) {
            View chileView = getChildAt(i);
            LayoutParams lp = chileView.getLayoutParams();

            switch (lp.width) {
                case MATCH_PARENT:
                    if (selfWidthSpecMode == EXACTLY || selfWidthSpecMode == AT_MOST) {
                        childWidthSpec = MeasureSpec.makeMeasureSpec(selfWidthSpecSize - usedWidth <= smallestWidth ? selfWidthSpecSize : selfWidthSpecSize - usedWidth, EXACTLY);
                    } else {
                        childWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                    }
                    break;
                case WRAP_CONTENT:
                    if (selfWidthSpecMode == EXACTLY || selfWidthSpecMode == AT_MOST) {
                        childWidthSpec = MeasureSpec.makeMeasureSpec(selfWidthSpecSize - usedWidth <= smallestWidth ? selfWidthSpecSize : selfWidthSpecSize - usedWidth, AT_MOST);
                    } else {
                        childWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                    }
                    break;
                default:
                    childWidthSpec = MeasureSpec.makeMeasureSpec(lp.width, EXACTLY);
                    break;
            }

            switch (lp.height) {
                case MATCH_PARENT:
                    if (selfHeightSpecMode == EXACTLY || selfHeightSpecMode == AT_MOST) {
                        childHeightSpec = MeasureSpec.makeMeasureSpec(selfHeightSpecSize - usedHeight, EXACTLY);
                    } else {
                        childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                    }
                    break;
                case WRAP_CONTENT:
                    if (selfHeightSpecMode == EXACTLY || selfHeightSpecMode == AT_MOST) {
                        childHeightSpec = MeasureSpec.makeMeasureSpec(selfHeightSpecSize - usedHeight, AT_MOST);
                    } else {
                        childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                    }
                    break;
                default:
                    childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, EXACTLY);
                    break;
            }

            chileView.measure(childWidthSpec, childHeightSpec);
            usedWidth = usedWidth + chileView.getMeasuredWidth() + marginLeft;
            if (usedWidth>maxUsedWidth){

                maxUsedWidth = usedWidth>getScreenWidth(getContext())?getScreenWidth(getContext()):usedWidth;
            }
            if (selfWidthSpecSize - usedWidth <= smallestWidth) {
                locationHeight = locationHeight + chileView.getMeasuredHeight();
                usedHeightSpec = chileView.getMeasuredHeight();
                usedHeight = usedHeight + chileView.getMeasuredHeight();

                usedWidth = 0;
                //最后一个会使高度发生异变 这时要重新测量
                switch (lp.width) {
                    case MATCH_PARENT:
                        if (selfWidthSpecMode == EXACTLY || selfWidthSpecMode == AT_MOST) {
                            childWidthSpec = MeasureSpec.makeMeasureSpec(selfWidthSpecSize - usedWidth <= smallestWidth ? selfWidthSpecSize : selfWidthSpecSize - usedWidth, EXACTLY);
                        } else {
                            childWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    case WRAP_CONTENT:
                        if (selfWidthSpecMode == EXACTLY || selfWidthSpecMode == AT_MOST) {
                            childWidthSpec = MeasureSpec.makeMeasureSpec(selfWidthSpecSize - usedWidth <= smallestWidth ? selfWidthSpecSize : selfWidthSpecSize - usedWidth, AT_MOST);
                        } else {
                            childWidthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    default:
                        childWidthSpec = MeasureSpec.makeMeasureSpec(lp.width, EXACTLY);
                        break;
                }
                switch (lp.height) {
                    case MATCH_PARENT:
                        if (selfHeightSpecMode == EXACTLY || selfHeightSpecMode == AT_MOST) {
                            childHeightSpec = MeasureSpec.makeMeasureSpec(selfHeightSpecSize - usedHeight, EXACTLY);
                        } else {
                            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    case WRAP_CONTENT:
                        if (selfHeightSpecMode == EXACTLY || selfHeightSpecMode == AT_MOST) {
                            childHeightSpec = MeasureSpec.makeMeasureSpec(selfHeightSpecSize - usedHeight, AT_MOST);
                        } else {
                            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                        }
                        break;
                    default:
                        childHeightSpec = MeasureSpec.makeMeasureSpec(lp.height, EXACTLY);
                        break;
                }
                //重新测量
                chileView.measure(childWidthSpec, childHeightSpec);
                if (chileView.getMeasuredWidth()+marginLeft>maxUsedWidth){
                    maxUsedWidth = chileView.getMeasuredWidth();
                }
                //减掉异变的高度 再加上重新测量获取的正确的高度
                usedHeight = usedHeight - usedHeightSpec + chileView.getMeasuredHeight() + marginTop;
                locationHeight = locationHeight - usedHeightSpec + chileView.getMeasuredHeight() + marginTop;
            }
            if (usedHeight == 0) {

                usedHeight = usedHeight > chileView.getMeasuredHeight() ? usedHeight : chileView.getMeasuredHeight() + marginTop;
            }

            if (i == 0) {
                childLeft[i] = marginLeft;
                childTop[i] = marginTop;
                childRight[i] = usedWidth;
                childBottom[i] = usedHeight;

            } else {
                childLeft[i] = usedWidth == 0 ? marginLeft : usedWidth - chileView.getMeasuredWidth();
                childTop[i] = locationHeight + marginTop;
                childRight[i] = usedWidth == 0 ? chileView.getMeasuredWidth() + marginLeft : usedWidth;
                childBottom[i] = usedHeight;
                if (usedWidth == 0) {
                     usedWidth = chileView.getMeasuredWidth() + marginLeft;
                }
            }
        }
        setMeasuredDimension(maxUsedWidth+marginLeft, usedHeight + marginTop);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int cCount = getChildCount();
        /**
         * 遍历所有childView根据其宽和高，以及margin进行布局
         */
        for (int i = 0; i < cCount; i++) {
            View childView = getChildAt(i);
            childView.layout(childLeft[i], childTop[i], childRight[i], childBottom[i]);
        }
        if (changed) {
            this.l = l;
            this.t = t;
            this.r = r;
            this.b = b;
            switch (mJustifyContent) {
                case JustifyContent.FLEX_START:
                    layout(0, t, getWidth(), b);
                    break;
                case JustifyContent.FLEX_END:
                    layout(getScreenWidth(getContext()) - getWidth(), t, getScreenWidth(getContext()), b);
                    break;
                case JustifyContent.CENTER:
                    layout((getScreenWidth(getContext()) - getWidth())/2, t, (getScreenWidth(getContext()) - getWidth())/2+getWidth(), b);
                    break;
                default:
                    layout(0, t, getWidth(), b);
                    break;
            }

        }
        if (change&&changed) {
            switch (mJustifyContent) {
                case JustifyContent.FLEX_START:
                    layout(0, t, getWidth(), b);
                    break;
                case JustifyContent.FLEX_END:
                    layout(getScreenWidth(getContext()) - getWidth(), t, getScreenWidth(getContext()), b);
                    break;
                case JustifyContent.CENTER:
                    layout((getScreenWidth(getContext()) - getWidth())/2, t, (getScreenWidth(getContext()) - getWidth())/2+getWidth(), b);
                    break;
                default:
                    layout(0, t, getWidth(), b);
                    break;
            }

        }



    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }
    boolean change;
    int l; int t; int r; int b;
    @Override
    public void setJustifyContent(@JustifyContent int justifyContent) {
        if (justifyContent != mJustifyContent) {
            invalidate();
            mJustifyContent = justifyContent;
            onLayout(true,l,t,r,b);
        }
    }
}
