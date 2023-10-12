package com.hutchison.dogsAPI;


import com.hutchison.dogsAPI.repo.DogsData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepoTests {
    @BeforeEach
    public void setUp() {
        DogsData.updateDogsData(new HashMap<>());
    }

    @Test
    public void testAddDogBreed() {
        assertEquals("success", DogsData.addDogBreed("Golden Retriever"));

        assertEquals("Dog Breed already exists!", DogsData.addDogBreed("Golden Retriever"));
    }

    @Test
    public void testRemoveDogBreed() {
        assertEquals("Dog Breed doesn't exist to remove.", DogsData.removeDogBreed("Poodle"));

        DogsData.addDogBreed("Beagle");
        assertEquals("success", DogsData.removeDogBreed("Beagle"));
    }

    @Test
    public void testAddDogBreedType() {
        DogsData.addDogBreed("German Shepherd");

        assertEquals("success", DogsData.addDogBreedType("German Shepherd", "Working"));

        assertEquals("Breed Type already exists in the Dog Breed.", DogsData.addDogBreedType("German Shepherd", "Working"));

        assertEquals("success", DogsData.addDogBreedType("Rottweiler", "Guard"));
    }

    @Test
    public void testRemoveDogBreedType() {
        DogsData.addDogBreed("Labrador");
        DogsData.addDogBreedType("Labrador", "Retriever");

        assertEquals("success", DogsData.removeDogBreedType("Labrador", "Retriever"));

        assertEquals("Breed type doesn't exist to remove.", DogsData.removeDogBreedType("Labrador", "Retriever"));

        assertEquals("No Dog Breed exists with that name.", DogsData.removeDogBreedType("Poodle", "Miniature"));
    }

    @Test
    public void testUpdateDogsData() {
        Map<String, ArrayList<String>> data = new HashMap<>();
        data.put("Beagle", new ArrayList<>(List.of("Hound", "Small")));
        data.put("Golden Retriever", new ArrayList<>(List.of("Retriever", "Large")));

        assertEquals("success", DogsData.updateDogsData(data));

        assertEquals(data, DogsData.getDogsData());
    }
}
