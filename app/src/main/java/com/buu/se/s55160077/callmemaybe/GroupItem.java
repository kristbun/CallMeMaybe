package com.buu.se.s55160077.callmemaybe;

/**
 * Created by LUKHINNN on 12/05/2015.
 */
public class GroupItem {
    private String txtGroup;
    private String txtGroupID;

    public GroupItem() {
    }

    public GroupItem(String txtGroup, String txtGroupID) {
        this.txtGroup = txtGroup;
        this.txtGroupID = txtGroupID;
    }

    public String getTxtGroup() {
        return txtGroup;
    }

    public void setTxtGroup(String txtGroup) {
        this.txtGroup = txtGroup;
    }

    public String getTxtGroupID() {
        return txtGroupID;
    }

    public void setTxtGroupID(String txtGroupID) {
        this.txtGroupID = txtGroupID;
    }
}
