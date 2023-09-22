package com.example.crudapp.dto;

public class AdminAddTenantToHouseDto {
    private Long houseId;
    private Long userId;

    public AdminAddTenantToHouseDto(Long house, Long userId) {
        this.houseId = house;
        this.userId = userId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
