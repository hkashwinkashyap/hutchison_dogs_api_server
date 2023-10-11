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

    public static Map<String, ArrayList<String>> getDogsData(){
        return dogsData;
    }

    public static void setDogsData(Map<String, ArrayList<String>> dogsDataInput){
        dogsData = dogsDataInput;
    }

    public static void addDogBreed(String dogBreed){
        dogsData.put(dogBreed, new ArrayList<>());
    }

    public static void removeDogBreed(String dogBreed){
        dogsData.remove(dogBreed);
    }
}
