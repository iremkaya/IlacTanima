package com.example.pethoalpar.zxingexample;

/**
 * Created by iremkaya on 4.5.2016.
 */
public class ILAC {
    //private variables
    int _id;
    String _name;
    String _pro;
    String _barcode;
    // Empty constructor
    public ILAC() {

    }

    // constructor
    public ILAC(int id, String name, String _pro) {
        this._id = id;
        this._name = name;
        this._pro = _pro;
    }

    // constructor
    public ILAC(String name, String _pro,String _barcode) {
        this._name = name;
        this._pro = _pro;
        this._barcode=_barcode;
    }
    public ILAC(int id,String name, String _pro,String _barcode) {
        this._id = id;
        this._name = name;
        this._pro = _pro;
        this._barcode=_barcode;
    }
    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int id) {
        this._id = id;
    }

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }

    // getting phone number
    public String getPhoneNumber() {
        return this._pro;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number) {
        this._pro = phone_number;
    }

    public String getBarcode() {
        return this._barcode;
    }

    // setting phone number
    public void setBarcode(String Barcode) {
        this._barcode= Barcode;
    }
}