package com.hairdresser.booking.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.hairdresser.booking.security.ApplicationUserPermission.*;

@AllArgsConstructor
public enum ApplicationUserRole {
    CLIENT(Sets.newHashSet(VISIT_ADD, HAIRSTYLE_READ)),
    HAIRDRESSER(Sets.newHashSet(HAIRSTYLE_READ, EMPLOYEE_READ, VISIT_ADD, CALENDAR_READ)),
    ADMIN(Sets.newHashSet(HAIRSTYLE_READ, HAIRSTYLE_WRITE, EMPLOYEE_READ, EMPLOYEE_WRITE, VISIT_ADD, CALENDAR_READ, CALENDAR_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
