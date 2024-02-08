package com.xische.domain;

public enum UserType {

    EMPLOYEE("Employee"), AFFILIATE("affiliate"), CUSTOMER("customer"), NEW("new");

    private String userType;

    UserType(final String userType) {
        this.userType = userType;
    }
}
