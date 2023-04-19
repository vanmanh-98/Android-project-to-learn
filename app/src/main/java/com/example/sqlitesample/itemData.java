package com.example.sqlitesample;

import java.util.HashMap;

public class itemData {
    String name;
    String phoneNum;
    String typeText;
    int typeId;
    static HashMap<String, Integer> typeMap = new HashMap<String, Integer>();
    static {
        typeMap.put("Doctor", 2);
        typeMap.put("None", 0);
        typeMap.put("Lover", 1);
    }


    itemData(String name, String phone, String type){
        this.name = name;
        this.phoneNum = phone;
        this.typeText = type;
        this.typeId = typeMap.get(type);
    }

}
