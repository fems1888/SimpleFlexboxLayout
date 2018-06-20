package com.example.flexlib;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Created by jackieyao on 2018/6/20 上午11:46.
 */
@IntDef({JustifyContent.FLEX_START,JustifyContent.FLEX_END,JustifyContent.CENTER})
@Retention(RetentionPolicy.SOURCE)
public @interface JustifyContent {
    /** Flex items are packed toward the start line. */
    int FLEX_START = 0;

    /** Flex items are packed toward the end line. */
    int FLEX_END = 1;

    /** Flex items are centered along the flex line where the flex items belong. */
    int CENTER = 2;
}
