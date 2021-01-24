package com.hairdresser.booking.auth;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.hairdresser.booking.security.ApplicationUserRole.*;

@Repository("fakeUsers")
@AllArgsConstructor
public class FakeApplicationUserDaoService implements ApplicationUserDao {

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "admin",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true,
                        ""
                ),
                new ApplicationUser(
                        "AdamKrzak",
                        passwordEncoder.encode("password"),
                        HAIRDRESSER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true,
                        "600ddcf9e56f6855d2ecdf64"
                ),
                new ApplicationUser(
                        "IzabelaGoluch",
                        passwordEncoder.encode("password"),
                        HAIRDRESSER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true,
                        "600ddd89e56f6855d2ecdf65"
                ),
                new ApplicationUser(
                        "client",
                        passwordEncoder.encode("password"),
                        CLIENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true,
                        ""
                ),
                new ApplicationUser(
                        "hairdresser",
                        passwordEncoder.encode("password"),
                        CLIENT.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true,
                        ""
                )
        );

        return applicationUsers;
    }
}
