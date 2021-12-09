package com.example.navigation;

public class GroupItem extends BaseItem {
    private int mLevel;

    public GroupItem(String mName, int icon) {
        super(mName, icon);
        mLevel = 0;
    }

    public GroupItem(String name) {
        super(name);
        mLevel = 0;
    }

    public int getLevel() {
        return 0;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

}
