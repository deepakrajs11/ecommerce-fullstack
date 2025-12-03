package com.ecommerce.userservice.controller;


import com.ecommerce.userservice.dto.DeleteRequestDto;
import com.ecommerce.userservice.dto.RegisterRequestDto;
import com.ecommerce.userservice.dto.ResponseDto;
import com.ecommerce.userservice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@Valid @RequestBody RegisterRequestDto request){
       try {
           ResponseDto response = userService.register(request);
           if (response.isSuccess()) {
               return ResponseEntity.ok(response);
           } else {
               return ResponseEntity
                       .status(HttpStatus.BAD_REQUEST)
                       .body(response);
           }
       } catch (Exception e) {
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDto<>(false, e.getMessage()));
       }
    }

    @GetMapping("/find")
    public ResponseEntity<ResponseDto> find(@NotBlank(message = "Invalid userName") @RequestParam String username){
        try{
        ResponseDto response = userService.find(username);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto<>(false, e.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestBody RegisterRequestDto request){
        try{
        ResponseDto response = userService.update(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    } catch (Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto<>(false, e.getMessage()));
    }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody DeleteRequestDto request){
        try{
        ResponseDto response = userService.delete(request);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(response);
        }
    } catch (Exception e) {
        return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(new ResponseDto<>(false, e.getMessage()));
        }
    }




}
