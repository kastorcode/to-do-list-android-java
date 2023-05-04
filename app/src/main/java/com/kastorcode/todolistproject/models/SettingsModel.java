package com.kastorcode.todolistproject.models;

import java.io.Serializable;

public class SettingsModel implements Serializable {

    private int theme;

    public SettingsModel (int theme) {
        this.theme = theme;
    }

    public int getTheme () {
        return theme;
    }

    public void setTheme (int theme) {
        this.theme = theme;
    }

}