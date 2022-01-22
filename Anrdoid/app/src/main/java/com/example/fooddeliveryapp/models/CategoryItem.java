package com.example.fooddeliveryapp.models;

public class CategoryItem {
    String text;
    boolean selected;

    public CategoryItem(String text) {
        this.text = text;
        this.selected = false;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        if(this.selected != selected)
            this.selected = selected;
    }


}
