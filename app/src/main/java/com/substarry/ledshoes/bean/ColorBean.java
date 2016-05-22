package com.substarry.ledshoes.bean;

import android.graphics.Color;

/**
 * Created by chengsi on 16/5/21.
 */
public class ColorBean {

    private int rgbColor;
    private boolean enabled = false;
    private boolean selected = false;

    public ColorBean(int rgbColor) {
        this.rgbColor = rgbColor;
    }

    public int getRgbColor() {
        return rgbColor;
    }

    public void setRgbColor(int rgbColor) {
        this.rgbColor = rgbColor;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
