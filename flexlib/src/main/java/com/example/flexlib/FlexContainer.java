package com.example.flexlib;

/**
 * @author Created by jackieyao on 2018/6/20 上午11:51.
 */

public interface FlexContainer {
    /**
     * Sets the given justify content attribute to the flex container.
     *
     * @param justifyContent the justify content value
     * @see JustifyContent
     */
    void setJustifyContent(@JustifyContent int justifyContent);
}
