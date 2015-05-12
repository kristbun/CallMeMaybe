package com.buu.se.s55160077.callmemaybe;

/**
 * Created by LUKHINNN on 11/05/2015.
 */
public class ContactItem {
    private String txtName;
    private String txtTelnum;
    private String txtConID;


    public ContactItem() {
    }

    public ContactItem(String txtName, String txtTelnum, String txtConID) {

        this.txtName = txtName;
        this.txtTelnum = txtTelnum;
        this.txtConID = txtConID;
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

    public String getTxtConID() {
        return txtConID;
    }

    public void setTxtConID(String txtConID) {
        this.txtConID = txtConID;
    }
}
