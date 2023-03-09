package com.melihcan.service;

import com.melihcan.dto.request.UserSaveRequestDto;
import com.melihcan.exception.ErrorType;
import com.melihcan.exception.UserManagerException;
import com.melihcan.manager.IAuthManager;
import com.melihcan.mapper.IUserProfileMapper;
import com.melihcan.repository.IUserProfileRepository;
import com.melihcan.repository.entity.UserProfile;
import com.melihcan.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {

    private final IUserProfileRepository repository;
    private final IAuthManager authManager;

    public UserProfileService(IUserProfileRepository repository,IAuthManager authManager){
        super(repository);
        this.repository=repository;
        this.authManager=authManager;
    }

    public Boolean saveDto(UserSaveRequestDto dto) {
        try {
            UserProfile userProfile = IUserProfileMapper.INSTANCE.toUserProfile(dto);
            save(userProfile);
            return true;
        }catch (Exception exception){
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }

    }
}
