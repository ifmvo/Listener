package com.ifmvo.matthew.custom.observableScrollView;

import android.view.ViewGroup;

/**
 * Created by pyy on 17-2-8.
 */

public interface Scrollable {
    /**
     * Sets a callback listener.
     *
     * @param listener listener to set
     */
    void setScrollViewCallbacks(ObservableScrollViewCallbacks listener);

    /**
     * Scrolls vertically to the absolute Y.
     * Implemented classes are expected to scroll to the exact Y pixels from the top,
     * but it depends on the type of the widget.
     *
     * @param y vertical position to scroll to
     */
    void scrollVerticallyTo(int y);

    /**
     * Returns the current Y of the scrollable view.
     *
     * @return current Y pixel
     */
    int getCurrentScrollY();

    /**
     * Sets a touch motion event delegation ViewGroup.
     * This is used to pass motion events back to parent view.
     * It's up to the implementation classes whether or not it works.
     *
     * @param viewGroup ViewGroup object to dispatch motion events
     */
    void setTouchInterceptionViewGroup(ViewGroup viewGroup);
}
