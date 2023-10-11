package com.hutchison.dogsAPI.controller;

import com.hutchison.dogsAPI.exceptions.DogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hutchison.dogsAPI.service.DogsService;
import com.hutchison.dogsAPI.utils.ResponseResult;

import java.util.ArrayList;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/dogsApi")
public class DogsController {

    private final DogsService dogsService;

    @Autowired
    public DogsController(DogsService dogsService) {
        this.dogsService = dogsService;
    }

    @GetMapping(value = "/getAllDogs")
    public ResponseResult<Map<String, ArrayList<String>>> getAllDogs() {
        return ResponseResult.<Map<String, ArrayList<String>>>builder().
                code(200)
                .message(null)
                .data(dogsService.getAllDogs())
                .build();
    }

    @PostMapping(value = "/addDogBreed")
    public ResponseResult<Void> addDogBreed(Map<String, String> requestBody){
        try {
            return ResponseResult.<Void>builder().
                    code(200)
                    .message(dogsService.addDogBreed(
                            requestBody.get("breedName")
                    ))
                    .data(null)
                    .build();
        }

        catch (DogException e){
            return ResponseResult.<Void>builder()
                    .code(400)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PostMapping(value = "/deleteDogBreed")
    public ResponseResult<Void> deleteDogBreed(Map<String, String> requestBody){
        try {
            return ResponseResult.<Void>builder().
                    code(200)
                    .message(dogsService.deleteDogBreed(
                            requestBody.get("breedName")
                    ))
                    .data(null)
                    .build();
        }

        catch (DogException e){
            return ResponseResult.<Void>builder()
                    .code(400)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PostMapping(value = "/addDogBreedType")
    public ResponseResult<Void> addDogBreedType(Map<String, String> requestBody){
        try {
            return ResponseResult.<Void>builder().
                    code(200)
                    .message(dogsService.addDogBreedType(
                            requestBody.get("breedName"),
                            requestBody.get("breedTypeName")
                    ))
                    .data(null)
                    .build();
        }

        catch (DogException e){
            return ResponseResult.<Void>builder()
                    .code(400)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PostMapping(value = "/deleteDogBreedType")
    public ResponseResult<Void> deleteDogBreedType(Map<String, String> requestBody){
        try {
            return ResponseResult.<Void>builder().
                    code(200)
                    .message(dogsService.deleteDogBreedType(
                            requestBody.get("breedName"),
                            requestBody.get("breedTypeName")
                    ))
                    .data(null)
                    .build();
        }

        catch (DogException e){
            return ResponseResult.<Void>builder()
                    .code(400)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }
}
