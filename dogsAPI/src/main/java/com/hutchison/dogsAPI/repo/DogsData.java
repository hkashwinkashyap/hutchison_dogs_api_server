package com.hutchison.dogsAPI.repo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class DogsData {
    private static Map<String, ArrayList<String>> dogsData = new HashMap<>();

    public static Map<String, ArrayList<String>> getDogsData() {
        return dogsData;
    }

    public static String updateDogsData(Map<String, ArrayList<String>> dogsDataInput) {
        dogsData = dogsDataInput;
        return "success";
    }

    public static String addDogBreed(String dogBreed) {
        if (dogsData.containsKey(dogBreed)) {
            return "Dog Breed already exists!";
        }
        dogsData.put(dogBreed, new ArrayList<>());
        return "success";
    }

    public static String addDogBreed(String dogBreed, ArrayList<String> dogBreedTypes) {
        if (dogsData.containsKey(dogBreed)) {
            return "Dog Breed already exists!";
        }
        dogsData.put(dogBreed, dogBreedTypes);
        return "success";
    }

    public static String removeDogBreed(String dogBreed) {
        if (dogsData.containsKey(dogBreed)) {
            dogsData.remove(dogBreed);
            return "success";
        }
        return "Dog Breed doesn't exist to remove.";
    }

    public static String updateDogBreedName(String dogBreed, String newDogBreed) {
        if (dogsData.containsKey(dogBreed)) {
            ArrayList<String> tempArrayList = dogsData.get(dogBreed);
            dogsData.remove(dogBreed);
            addDogBreed(newDogBreed, tempArrayList);
            return "success";
        }
        return "Dog Breed doesn't exist to update.";
    }

    public static String addDogBreedType(String dogBreed, String dogBreedType) {
        if (!dogsData.containsKey(dogBreed)) {
            addDogBreed(dogBreed);
        }
        if (dogsData.get(dogBreed).contains(dogBreedType)) {
            return "Breed Type already exists in the Dog Breed.";
        }
        dogsData.get(dogBreed).add(dogBreedType);
        return "success";
    }

    public static String removeDogBreedType(String dogBreed, String dogBreedType) {
        if (!dogsData.containsKey(dogBreed)) {
            return "No Dog Breed exists with that name.";
        }
        if (dogsData.get(dogBreed).contains(dogBreedType)) {
            dogsData.get(dogBreed).remove(dogBreedType);
            return "success";
        }
        return "Breed type doesn't exist to remove.";
    }

    public static String updateDogBreedTypeName(String dogBreed, String dogBreedType, String newDogBreedType) {
        if (dogsData.containsKey(dogBreed)) {
            if (dogsData.get(dogBreed).contains(newDogBreedType)) {
                return "Breed Type already exists with that name.";
            }
            if (!dogsData.get(dogBreed).contains(dogBreedType)) {
                return "Breed Type doesn't exist to update.";
            }
            dogsData.get(dogBreed).remove(dogBreedType);
            dogsData.get(dogBreed).add(newDogBreedType);
            return "success";
        }
        return "Dog Breed doesn't exist. Please add that dog breed first.";
    }

}
