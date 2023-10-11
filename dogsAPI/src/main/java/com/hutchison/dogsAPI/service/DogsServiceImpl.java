package com.hutchison.dogsAPI.service;

import com.hutchison.dogsAPI.exceptions.DogException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class DogsServiceImpl implements DogsService{
    @Override
    public Map<String, ArrayList<String>> getAllDogs() {
        Map<String, ArrayList<String>> result = new HashMap<>();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        result.put("Test",arrayList);
        return result;
    }

    @Override
    public String addDogBreed(String breedName) throws DogException {
        return null;
    }

    @Override
    public String addDogBreedType(String breedName, String breedTypeName) throws DogException {
        return null;
    }

    @Override
    public String deleteDogBreed(String breedName) throws DogException {
        return null;
    }

    @Override
    public String deleteDogBreedType(String breedName, String breedTypeName) throws DogException {
        return null;
    }
}
