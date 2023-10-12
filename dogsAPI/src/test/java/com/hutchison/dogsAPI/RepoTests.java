package com.hutchison.dogsAPI;


import com.hutchison.dogsAPI.repo.DogsData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

    @Test
    public void testAddDogBreed_Success() {
        String dogBreed = "Golden Retriever";
        ArrayList<String> dogBreedTypes = new ArrayList<>(Arrays.asList("English Cream", "American"));

        String result = DogsData.addDogBreed(dogBreed, dogBreedTypes);

        assertEquals("success", result);
    }

    @Test
    public void testAddDogBreed_AlreadyExists() {
        String dogBreed = "Labrador";
        ArrayList<String> dogBreedTypes = new ArrayList<>(Arrays.asList("Yellow", "Black"));
        DogsData.addDogBreed(dogBreed, dogBreedTypes);

        String result = DogsData.addDogBreed(dogBreed, dogBreedTypes);

        assertEquals("Dog Breed already exists!", result);
    }

    @Test
    public void testUpdateDogBreedName_Success() {
        String dogBreed = "Old Breed Name";
        String newDogBreed = "New Breed Name";
        ArrayList<String> dogBreedTypes = new ArrayList<>(Arrays.asList("Type1", "Type2"));
        DogsData.addDogBreed(dogBreed, dogBreedTypes);

        String result = DogsData.updateDogBreedName(dogBreed, newDogBreed);

        assertEquals("success", result);
        assertFalse(DogsData.getDogsData().containsKey(dogBreed));
        assertTrue(DogsData.getDogsData().containsKey(newDogBreed));
    }

    @Test
    public void testUpdateDogBreedName_BreedNotExists() {
        String dogBreed = "Nonexistent Breed";
        String newDogBreed = "New Breed";

        String result = DogsData.updateDogBreedName(dogBreed, newDogBreed);

        assertEquals("Dog Breed doesn't exist to update.", result);
    }

    @Test
    public void testUpdateDogBreedTypeName_Success() {
        String dogBreed = "Labrador";
        String dogBreedType = "Yellow";
        String newDogBreedType = "Golden";
        ArrayList<String> dogBreedTypes = new ArrayList<>(Arrays.asList("Yellow", "Black"));
        DogsData.addDogBreed(dogBreed, dogBreedTypes);

        String result = DogsData.updateDogBreedTypeName(dogBreed, dogBreedType, newDogBreedType);

        assertEquals("success", result);
        assertTrue(DogsData.getDogsData().get(dogBreed).contains(newDogBreedType));
        assertFalse(DogsData.getDogsData().get(dogBreed).contains(dogBreedType));
    }

    @Test
    public void testUpdateDogBreedTypeName_BreedNotExists() {
        String dogBreed = "Nonexistent Breed";
        String dogBreedType = "Type";
        String newDogBreedType = "New Type";

        String result = DogsData.updateDogBreedTypeName(dogBreed, dogBreedType, newDogBreedType);

        assertEquals("Dog Breed doesn't exist. Please add that dog breed first.", result);
    }

    @Test
    public void testUpdateDogBreedTypeName_TypeNotExists() {
        String dogBreed = "Labrador";
        String newDogBreedType = "Golden";
        ArrayList<String> dogBreedTypes = new ArrayList<>(Arrays.asList("Yellow", "Black"));
        DogsData.addDogBreed(dogBreed, dogBreedTypes);

        String result = DogsData.updateDogBreedTypeName(dogBreed, "Nonexistent Type", newDogBreedType);

        assertEquals("Breed Type doesn't exist to update.", result);
    }

}
