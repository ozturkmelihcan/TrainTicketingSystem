package com.melihcan.mapper;

import com.melihcan.dto.request.UserSaveRequestDto;
import com.melihcan.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IUserProfileMapper {

    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);


    UserProfile toUserProfile(final UserSaveRequestDto dto);
}
