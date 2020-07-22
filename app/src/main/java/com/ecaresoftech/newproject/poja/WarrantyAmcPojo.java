package com.ecaresoftech.newproject.poja;

import com.google.gson.annotations.SerializedName;

public class WarrantyAmcPojo {
    @SerializedName("nid")
    private String nid;
    @SerializedName("title")
    private String title;
    @SerializedName("receive_payment")
    private String receive_payment=null;
    @SerializedName("warranty_amc")
    private String warranty_amc;
    @SerializedName("warranty_expired")
    private String warranty_expired;
    @SerializedName("amc_expired")
    private String amc_expired;
    @SerializedName("due_payment")
    private String due_payment;
    @SerializedName("payment_date")
    private String payment_date;
    @SerializedName("last_service_date")
    private String last_service_date;
    @SerializedName("service_image")
    private String service_image;
    @SerializedName("service_type")
    private String service_type;
    @SerializedName("site_name")
    private String site_name;
    @SerializedName("field_service_mode")
    private String field_service_mode;
    @SerializedName("field_contact_person")
    private String field_contact_person;
    @SerializedName("field_contact_cell_no_")
    private String field_contact_cell_no_;
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

    public String getReceive_payment() {
        return receive_payment;
    }

    public void setReceive_payment(String receive_payment) {
        this.receive_payment = receive_payment;
    }

    public String getWarranty_amc() {
        return warranty_amc;
    }

    public void setWarranty_amc(String warranty_amc) {
        this.warranty_amc = warranty_amc;
    }

    public String getWarranty_expired() {
        return warranty_expired;
    }

    public void setWarranty_expired(String warranty_expired) {
        this.warranty_expired = warranty_expired;
    }

    public String getAmc_expired() {
        return amc_expired;
    }

    public void setAmc_expired(String amc_expired) {
        this.amc_expired = amc_expired;
    }

    public String getDue_payment() {
        return due_payment;
    }

    public void setDue_payment(String due_payment) {
        this.due_payment = due_payment;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getLast_service_date() {
        return last_service_date;
    }

    public void setLast_service_date(String last_service_date) {
        this.last_service_date = last_service_date;
    }

    public String getService_image() {
        return service_image;
    }

    public void setService_image(String service_image) {
        this.service_image = service_image;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getField_service_mode() {
        return field_service_mode;
    }

    public void setField_service_mode(String field_service_mode) {
        this.field_service_mode = field_service_mode;
    }

    public String getField_contact_person() {
        return field_contact_person;
    }

    public void setField_contact_person(String field_contact_person) {
        this.field_contact_person = field_contact_person;
    }

    public String getField_contact_cell_no_() {
        return field_contact_cell_no_;
    }

    public void setField_contact_cell_no_(String field_contact_cell_no_) {
        this.field_contact_cell_no_ = field_contact_cell_no_;
    }

    public String getLift() {
        return lift;
    }

    public void setLift(String lift) {
        this.lift = lift;
    }
}
