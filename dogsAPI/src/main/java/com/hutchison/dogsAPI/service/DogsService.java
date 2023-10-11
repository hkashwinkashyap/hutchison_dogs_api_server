package com.hutchison.dogsAPI.service;

import com.hutchison.dogsAPI.exceptions.DogException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public interface DogsService {

    public Map<String, ArrayList<String>> getAllDogs();

    public String addDogBreed(String breedName) throws DogException;

    public String addDogBreedType(String breedName, String breedTypeName) throws DogException;

    public String deleteDogBreed(String breedName) throws DogException;

    public String deleteDogBreedType(String breedName, String breedTypeName) throws DogException;
}
