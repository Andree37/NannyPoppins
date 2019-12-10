package com.example.nannypoppins;

import java.io.Serializable;
import java.sql.Timestamp;

public class Baby implements Serializable {
    private String name;
    private Timestamp birthDate;
    private boolean gender;

    public Baby(String name, Timestamp birthDate, boolean gender) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
