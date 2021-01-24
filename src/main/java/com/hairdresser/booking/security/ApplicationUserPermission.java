package com.hairdresser.booking.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApplicationUserPermission {
    HAIRSTYLE_READ("hairstyle:read"),
    HAIRSTYLE_WRITE("hairstyle:write"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    VISIT_ADD("visit:add"),
    CALENDAR_READ("calendar:read"),
    CALENDAR_WRITE("calendar:write"),;

    private final String permission;

    public String getPermission() {
        return permission;
    }
}
