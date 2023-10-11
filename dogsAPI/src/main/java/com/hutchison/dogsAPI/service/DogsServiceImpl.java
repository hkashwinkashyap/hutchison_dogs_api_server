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
public class DogsServiceImpl implements DogsService{

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private synchronized void loadData(){
        try{
            var jsonData = FileUtils.readFileToString(new File("dogs.json"), "utf-8");
            if (StringUtils.isNotBlank(jsonData)) {
                DogsData.setDogsData(mapper.readValue(jsonData, new TypeReference<Map<String, ArrayList<String>>>() {}));
            }
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized void saveData(DogsData dogsData) {
        try {
            var serialized = mapper.writeValueAsString(dogsData);
            FileUtils.writeStringToFile(new File("dogs.json"), serialized, "utf-8", false);
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
