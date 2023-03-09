package com.melihcan.service;

import com.melihcan.dto.request.UserSaveRequestDto;
import com.melihcan.mapper.IUserProfileMapper;
import com.melihcan.repository.IUserProfileRepository;
import com.melihcan.repository.entity.UserProfile;
import com.melihcan.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository){
        super(repository);
        this.repository=repository;
    }

    public Boolean saveDto(UserSaveRequestDto dto) {
        UserProfile userProfile = IUserProfileMapper.INSTANCE.toUserProfile(dto);
        save(userProfile);
        return true;
    }
}
