package com.hutchison.dogsAPI.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hutchison.dogsAPI.exceptions.DogException;
import com.hutchison.dogsAPI.repo.DogsData;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


@Service
public class DogsServiceImpl implements DogsService {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private synchronized void loadData() {
        try {
            var jsonData = FileUtils.readFileToString(new File("dogs.json"), "utf-8");
            if (StringUtils.isNotBlank(jsonData)) {
                DogsData.updateDogsData(mapper.readValue(jsonData, new TypeReference<>() {
                }));
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void saveData() {
        try {
            var serialized = mapper.writeValueAsString(DogsData.getDogsData());
            FileUtils.writeStringToFile(new File("dogs.json"), serialized, "utf-8");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, ArrayList<String>> getAllDogs() {
        loadData();
        return DogsData.getDogsData();
    }

    @Override
    public String addDogBreed(String breedName) throws DogException {
        if (breedName == null || breedName.equals(" ")) {
            throw new DogException("Seems like you have not entered anything in the input. Please try again with a valid input.");
        }
        String result = DogsData.addDogBreed(breedName);
        if (result.equals("success")) {
            saveData();
            return "Dog Breed has been added successfully.";
        }
        throw new DogException(result);
    }

    @Override
    public String deleteDogBreed(String breedName) throws DogException {
        String result = DogsData.removeDogBreed(breedName);
        if (result.equals("success")) {
            saveData();
            return "Dog Breed is removed from the records.";
        }
        throw new DogException(result);
    }

    @Override
    public String addDogBreedType(String breedName, String breedTypeName) throws DogException {
        if (breedName == null || breedName.equals(" ") || breedTypeName == null || breedTypeName.equals(" ")) {
            throw new DogException("Seems like you have not entered anything in the input. Please try again with a valid input.");
        }
        String result = DogsData.addDogBreedType(breedName, breedTypeName);
        if(result.equals("success")){
            saveData();
            return "Breed Type is added to the Dog record.";
        }
        throw new DogException(result);
    }

    @Override
    public String deleteDogBreedType(String breedName, String breedTypeName) throws DogException {
        String result = DogsData.removeDogBreedType(breedName, breedTypeName);
        if(result.equals("success")){
            saveData();
            return "Removed the Breed Type from the Dog records.";
        }
        throw new DogException(result);
    }
}
