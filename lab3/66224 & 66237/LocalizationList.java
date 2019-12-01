package com.karol.web.controller;

import java.util.List;

public class LocalizationList {

    List<VehicleLocalization> result;


    public List<VehicleLocalization> getList() {
        return result;
    }

    public void setList(List<VehicleLocalization> list) {
        this.result = list;
    }
}
