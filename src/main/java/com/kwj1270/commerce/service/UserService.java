package com.kwj1270.commerce.service;

import com.kwj1270.commerce.config.auth.dto.SessionUser;
import com.kwj1270.commerce.domain.user.User;
import com.kwj1270.commerce.domain.user.UserRepository;
import com.kwj1270.commerce.dto.user.UserListResponseDto;
import com.kwj1270.commerce.dto.user.UserSaveRequestDto;
import com.kwj1270.commerce.dto.user.UserUpdateRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Transactional
    public Long save(UserSaveRequestDto requestDto){
        return userRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long login(Long id, String password){
        User user = userRepository.findByIdAndPassword(id, password).orElseThrow(() -> new
                IllegalArgumentException("해당 아이디 또는 비밀번호가 존재하지 않습니다."));
        httpSession.setAttribute("user", new SessionUser(user));
        return id;
    }

    @Transactional
    public List<UserListResponseDto> findAll(){
        return userRepository.findAll()
                .stream()
                .map(UserListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto requestDto){
        User user = userRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 사용자가 없습니다. id="+ id));
        user.update(requestDto.getName(), requestDto.getPassword(), requestDto.getPicture());
        return id;
    }

    @Transactional
    public void delete(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 사용자 없습니다. id="+ id));
        userRepository.delete(user);
    }


}
