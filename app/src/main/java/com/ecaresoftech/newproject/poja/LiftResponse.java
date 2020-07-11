package com.ecaresoftech.newproject.poja;

import com.google.gson.annotations.SerializedName;

public class LiftResponse {
    @SerializedName("nid")
    private String nid;
    @SerializedName("title")
    private String title;
    @SerializedName("area")
    private String area=null;
    @SerializedName("contact_cell_no")
    private String contact_cell_no;
    @SerializedName("contact_person")
    private String contact_person;
    @SerializedName("date_of_completion")
    private String date_of_completion;
    @SerializedName("date_of_order")
    private String date_of_order;
    @SerializedName("district")
    private String district;
    @SerializedName("door_size")
    private String door_size;
    @SerializedName("lift_capacity")
    private String lift_capacity;
    @SerializedName("lift_no")
    private String lift_no;
    @SerializedName("site_name")
    private String site_name;
    @SerializedName("state")
    private String state;
    @SerializedName("cons")
    private String cons;
    @SerializedName("gear_type")
    private String gear_type;
    @SerializedName("lift")
    private String lift;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContact_cell_no() {
        return contact_cell_no;
    }

    public void setContact_cell_no(String contact_cell_no) {
        this.contact_cell_no = contact_cell_no;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getDate_of_completion() {
        return date_of_completion;
    }

    public void setDate_of_completion(String date_of_completion) {
        this.date_of_completion = date_of_completion;
    }

    public String getDate_of_order() {
        return date_of_order;
    }

    public void setDate_of_order(String date_of_order) {
        this.date_of_order = date_of_order;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDoor_size() {
        return door_size;
    }

    public void setDoor_size(String door_size) {
        this.door_size = door_size;
    }

    public String getLift_capacity() {
        return lift_capacity;
    }

    public void setLift_capacity(String lift_capacity) {
        this.lift_capacity = lift_capacity;
    }

    public String getLift_no() {
        return lift_no;
    }

    public void setLift_no(String lift_no) {
        this.lift_no = lift_no;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getGear_type() {
        return gear_type;
    }

    public void setGear_type(String gear_type) {
        this.gear_type = gear_type;
    }

    public String getLift() {
        return lift;
    }

    public void setLift(String lift) {
        this.lift = lift;
    }
}
