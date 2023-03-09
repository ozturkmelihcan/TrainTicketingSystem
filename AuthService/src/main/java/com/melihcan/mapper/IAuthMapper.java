package com.melihcan.mapper;

import com.melihcan.dto.request.RegisterRequestDto;
import com.melihcan.dto.response.RegisterResponseDto;
import com.melihcan.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAuthMapper {
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);
    Auth toAuth (final RegisterRequestDto dto);

    RegisterResponseDto toRegisterResponseDto (final Auth auth);
}
