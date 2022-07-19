package com.openclassrooms.entrevoisins.utils;

import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.hamcrest.Matchers.allOf;

import android.support.design.widget.TabLayout;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

public class SelectTabAtPosition implements ViewAction {

    private int tabIndex;

    public SelectTabAtPosition(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    @Override
    public Matcher<View> getConstraints() {
        return allOf(isDisplayed(), isAssignableFrom(TabLayout.class));
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void perform(UiController uiController, View view) {
        TabLayout tabLayout= (TabLayout) view;
        tabLayout.getTabAt(tabIndex).select();
    }


}
