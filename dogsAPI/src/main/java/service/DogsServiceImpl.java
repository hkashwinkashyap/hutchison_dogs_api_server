package service;

import exceptions.DogException;

import java.util.ArrayList;
import java.util.Map;

public class DogsServiceImpl implements DogsService{
    @Override
    public Map<String, ArrayList<String>> getAllDogs() {
        return null;
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
