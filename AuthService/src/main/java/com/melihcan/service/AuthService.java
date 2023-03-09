package com.melihcan.service;

import com.melihcan.dto.request.ActivateRequestDto;
import com.melihcan.dto.request.DoLoginRequestDto;
import com.melihcan.dto.request.RegisterRequestDto;
import com.melihcan.dto.response.RegisterResponseDto;
import com.melihcan.exception.AuthManagerException;
import com.melihcan.exception.ErrorType;
import com.melihcan.mapper.IAuthMapper;
import com.melihcan.repository.IAuthRepository;
import com.melihcan.repository.entity.Auth;
import com.melihcan.repository.enums.EStatus;
import com.melihcan.utility.CodeGenerator;
import com.melihcan.utility.JWTTokenManager;
import com.melihcan.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService extends ServiceManager<Auth, Long> {

    private final IAuthRepository repository;
    private final JWTTokenManager jwtTokenManager;

    public AuthService(IAuthRepository repository, JWTTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public RegisterResponseDto register(RegisterRequestDto dto) {
        try {
            Auth auth = IAuthMapper.INSTANCE.toAuth(dto);
            auth.setActivationCode(CodeGenerator.generateCode());
            save(auth);
            return IAuthMapper.INSTANCE.toRegisterResponseDto(auth);

        } catch (Exception exception) {
            throw new AuthManagerException(ErrorType.USER_NOT_CREATED);
        }
    }

    public String doLogin(DoLoginRequestDto dto) {
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isEmpty()) throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        if (token.isEmpty()) throw new AuthManagerException(ErrorType.TOKEN_NOT_CREATED);
        return token.get();
    }

    public Boolean activateStatus(ActivateRequestDto dto) {
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty()) throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        if (dto.getActivationCode().equals(auth.get().getActivationCode())) {
            auth.get().setStatus(EStatus.ACTIVE);
            save(auth.get());
            return true;
        } else {
            throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
        }
    }
}
