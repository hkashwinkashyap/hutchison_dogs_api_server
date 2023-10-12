package com.hutchison.dogsAPI.controller;

import com.hutchison.dogsAPI.exceptions.DogException;
import com.hutchison.dogsAPI.service.DogsService;
import com.hutchison.dogsAPI.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
                .message("success")
                .data(dogsService.getAllDogs())
                .build();
    }

    @PostMapping(value = "/addDogBreed")
    public ResponseResult<Void> addDogBreed(@RequestBody Map<String, String> requestBody) {
        try {
            return ResponseResult.<Void>builder().
                    code(200)
                    .message(dogsService.addDogBreed(
                            requestBody.get("breedName")
                    ))
                    .build();
        } catch (DogException e) {
            return ResponseResult.<Void>builder()
                    .code(400)
                    .message(e.getMessage())
                    .build();
        }
    }

    @PostMapping(value = "/deleteDogBreed")
    public ResponseResult<Void> deleteDogBreed(@RequestBody Map<String, String> requestBody) {
        try {
            return ResponseResult.<Void>builder().
                    code(200)
                    .message(dogsService.deleteDogBreed(
                            requestBody.get("breedName")
                    ))
                    .build();
        } catch (DogException e) {
            return ResponseResult.<Void>builder()
                    .code(400)
                    .message(e.getMessage())
                    .build();
        }
    }

    @PostMapping(value = "/updateDogBreedName")
    public ResponseResult<Void> updateDogBreedName(@RequestBody Map<String, String > requestBody) {
        try {
            return ResponseResult.<Void>builder().
                    code(200)
                    .message(dogsService.updateDogBreedName(
                            requestBody.get("breedName"),
                            requestBody.get("newBreedName")
                    ))
                    .build();
        } catch (DogException e) {
            return ResponseResult.<Void>builder()
                    .code(400)
                    .message(e.getMessage())
                    .build();
        }
    }

    @PostMapping(value = "/addDogBreedType")
    public ResponseResult<Void> addDogBreedType(@RequestBody Map<String, String> requestBody) {
        try {
            return ResponseResult.<Void>builder().
                    code(200)
                    .message(dogsService.addDogBreedType(
                            requestBody.get("breedName"),
                            requestBody.get("breedTypeName")
                    ))
                    .build();
        } catch (DogException e) {
            return ResponseResult.<Void>builder()
                    .code(400)
                    .message(e.getMessage())
                    .build();
        }
    }

    @PostMapping(value = "/deleteDogBreedType")
    public ResponseResult<Void> deleteDogBreedType(@RequestBody Map<String, String> requestBody) {
        try {
            return ResponseResult.<Void>builder().
                    code(200)
                    .message(dogsService.deleteDogBreedType(
                            requestBody.get("breedName"),
                            requestBody.get("breedTypeName")
                    ))
                    .build();
        } catch (DogException e) {
            return ResponseResult.<Void>builder()
                    .code(400)
                    .message(e.getMessage())
                    .build();
        }
    }

    @PostMapping(value = "/updateDogBreedTypeName")
    public ResponseResult<Void> updateDogBreedTypeName(@RequestBody Map<String, String > requestBody) {
        try {
            return ResponseResult.<Void>builder().
                    code(200)
                    .message(dogsService.updateDogBreedTypeName(
                            requestBody.get("breedName"),
                            requestBody.get("breedTypeName"),
                            requestBody.get("newBreedTypeName")
                    ))
                    .build();
        } catch (DogException e) {
            return ResponseResult.<Void>builder()
                    .code(400)
                    .message(e.getMessage())
                    .build();
        }
    }
}
