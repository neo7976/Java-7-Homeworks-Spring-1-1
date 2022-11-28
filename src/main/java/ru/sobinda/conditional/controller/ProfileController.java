package ru.sobinda.conditional.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sobinda.conditional.profile.SystemProfile;

@RestController
@RequestMapping("/")
public class ProfileController {
    public SystemProfile profile;

    public ProfileController(SystemProfile profile) {
        this.profile = profile;
    }
@GetMapping("profile")
    public String getProfile() {
        return profile.getProfile();
    }
}
