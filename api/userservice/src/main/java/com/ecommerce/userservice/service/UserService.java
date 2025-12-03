package com.ecommerce.userservice.service;

import com.ecommerce.userservice.UserRepository;
import com.ecommerce.userservice.dto.DeleteRequestDto;
import com.ecommerce.userservice.dto.RegisterRequestDto;
import com.ecommerce.userservice.dto.ResponseDto;
import com.ecommerce.userservice.mapper.UserMapper;
import com.ecommerce.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    private final UserMapper userMapper= new UserMapper();
    public ResponseDto register(RegisterRequestDto request) {
        try {
            User alreadyExist = userRepo.findByEmail(request.getEmail());

            if (alreadyExist != null) {
                return new ResponseDto(false, "User with given email already exist");
            }
            User user = userMapper.requestUserMapper(request, new User());
            user = userRepo.save(user);

            return new ResponseDto(true, userMapper.userUserDtoMapper(user));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public ResponseDto find(String username) {
        try{
            User user = userRepo.findByUsername(username);
            if (user == null){
                return new ResponseDto<>(false,"User with given username does not exists");
            }
            return new ResponseDto<>(true, userMapper.userUserDtoMapper(user));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseDto update(RegisterRequestDto request) {
        try{
            User alreadyExist = userRepo.findByEmail(request.getEmail());
            if (alreadyExist == null){
                return new ResponseDto<>(false,"User with given email does not exist");
            }
            alreadyExist = userRepo.save(userMapper.requestUserMapper(request, alreadyExist));
            return new ResponseDto<>(true, userMapper.userUserDtoMapper(alreadyExist));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseDto delete(DeleteRequestDto request) {
        try{
            User alreadyExist = userRepo.findByEmail(request.getEmail());
            if (alreadyExist == null){
                return new ResponseDto<>(false,"User with given email does not exist");
            }
            userRepo.delete(alreadyExist);
            return new ResponseDto<>(true, userMapper.userUserDtoMapper(alreadyExist));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
