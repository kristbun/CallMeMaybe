package com.buu.se.s55160077.callmemaybe;

/**
 * Created by LUKHINNN on 11/05/2015.
 */
public class ContactItem {
    private String txtName;
    private String txtTelnum;
    private String txtGroup;

    public ContactItem() {
    }

    public ContactItem(String txtName, String txttelnum) {
        this.txtName = txtName;
        this.txtTelnum = txttelnum;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtTelnum() {
        return txtTelnum;
    }

    public void setTxtTelnum(String txtTelnum) {
        this.txtTelnum = txtTelnum;
    }

    public String getTxtGroup() {
        return txtGroup;
    }

    public void setTxtGroup(String txtGroup) {
        this.txtGroup = txtGroup;
    }
}
