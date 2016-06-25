package com.it520.activity.main.fragment;

/**
 * Created by kay on 16/5/12.
 */
public class FragmentInfo {
    private String title;
    private String fragment;

    public FragmentInfo(String title, String fragment) {
        this.title = title;
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFragment() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;
    }
}
