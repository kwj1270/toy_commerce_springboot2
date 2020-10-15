package com.kwj1270.commerce.controller.user;

import com.kwj1270.commerce.dto.user.UserResponseDto;
import com.kwj1270.commerce.dto.user.UserSaveRequestDto;
import com.kwj1270.commerce.dto.user.UserUpdateRequestDto;
import com.kwj1270.commerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    // CRUD 기반 정렬
    @PostMapping("/api/v1/user")
    public Long save(@RequestBody UserSaveRequestDto requestDto){
        return userService.save(requestDto);
    }

    @GetMapping("/api/v1/user/{id}")
    public UserResponseDto findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PutMapping("/api/v1/user/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserUpdateRequestDto requestDto){
        return userService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        userService.delete(id);
        return id;
    }

}
