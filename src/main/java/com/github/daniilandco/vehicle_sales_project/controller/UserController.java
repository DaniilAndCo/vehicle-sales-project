package com.github.daniilandco.vehicle_sales_project.controller;

import com.github.daniilandco.vehicle_sales_project.controller.request.RegisterRequest;
import com.github.daniilandco.vehicle_sales_project.controller.response.RestApiResponse;
import com.github.daniilandco.vehicle_sales_project.exception.InvalidImageSizeException;
import com.github.daniilandco.vehicle_sales_project.exception.JwtAuthenticationException;
import com.github.daniilandco.vehicle_sales_project.exception.UserIsNotLoggedInException;
import com.github.daniilandco.vehicle_sales_project.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody RegisterRequest request) {
        try {
            userService.updateProfile(request);
            return ResponseEntity
                    .ok(new RestApiResponse("profile is updated"));
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(new RestApiResponse("update profile error"));
        }
    }

    @PostMapping("/profile_photo")
    public ResponseEntity<?> updateProfilePhoto(@RequestParam("file") MultipartFile imageFile) {
        try {
            userService.updateProfilePhoto(imageFile.getBytes());
            return ResponseEntity.ok(new RestApiResponse("profile photo is updated"));
        } catch (IOException | JwtAuthenticationException | UserIsNotLoggedInException | InvalidImageSizeException e) {
            return ResponseEntity
                    .badRequest()
                    .body((new RestApiResponse("Update profile photo error")));
        }
    }

    @GetMapping("/profile_photo")
    public ResponseEntity<?> getProfilePhoto() {
        try {
            return ResponseEntity.ok(new RestApiResponse("ok", userService.getProfilePhoto()));
        } catch (IOException | JwtAuthenticationException | UserIsNotLoggedInException e) {
            return ResponseEntity
                    .badRequest()
                    .body((new RestApiResponse(e.getMessage())));
        }
    }

}
