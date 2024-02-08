package com.xische.repository;

import com.xische.domain.User;
import com.xische.domain.UserType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;

@Component
public class UsersRepository {
    public HashMap<Integer, User> usersData;

    @PostConstruct
    public void initUserData() {
        usersData = new HashMap<>();

        usersData.put(1, buildUserDetails(1L, UserType.AFFILIATE, "Dubai", "010101",
                LocalDateTime.of(2020, 05, 14, 12, 12), "username1"));

        usersData.put(2, buildUserDetails(2L, UserType.CUSTOMER, "Dubai", "0458978",
                LocalDateTime.of(2021, 10, 12, 12, 12), "username2"));

        usersData.put(1, buildUserDetails(3L, UserType.EMPLOYEE, "Dubai", "7895256",
                LocalDateTime.of(2023, 05, 21, 12, 12), "username3"));

        usersData.put(1, buildUserDetails(1L, UserType.CUSTOMER, "Dubai", "025227277",
                LocalDateTime.of(2023, 07, 14, 12, 12), "username4"));

        usersData.put(1, buildUserDetails(1L, UserType.AFFILIATE, "Dubai", "01017575701",
                LocalDateTime.of(2022, 01, 17, 12, 12), "username5"));

        usersData.put(1, buildUserDetails(1L, UserType.EMPLOYEE, "Dubai", "78575785",
                LocalDateTime.of(2022, 01, 16, 12, 12), "username6"));
    }

    private User buildUserDetails(final Long userId, final UserType userType, final String address, final String mobileNo,
                                  final LocalDateTime localDateTime, final String username) {
        return User.builder()
                .userId(userId)
                .userType(userType)
                .address(address)
                .mobileNo(mobileNo)
                .storeJoinDate(localDateTime)
                .username(username)
                .build();
    }
}
