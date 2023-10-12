package com.hutchison.dogsAPI;

import com.hutchison.dogsAPI.controller.DogsController;
import com.hutchison.dogsAPI.exceptions.DogException;
import com.hutchison.dogsAPI.service.DogsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

@SpringBootTest
class DogsApiApplicationTests {

    private final String jsonError = "{\"code\" : 400, \"message\" : \"";
    private MockMvc mockMvc;
    @Mock
    private DogsService dogsService;

    @InjectMocks
    private DogsController dogsController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(dogsController).build();
    }

    @Test
    public void testGetAllDogs() throws Exception {
        Map<String, ArrayList<String>> mockData = new HashMap<>();
        mockData.put("Bulldog", new ArrayList<>());
        mockData.put("Labrador", new ArrayList<>());

        Mockito.when(dogsService.getAllDogs()).thenReturn(mockData);

        mockMvc.perform(MockMvcRequestBuilders.get("/dogsApi/getAllDogs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testAddDogBreed_Success() throws Exception {
        String breedName = "Husky";
        Mockito.when(dogsService.addDogBreed(breedName)).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/addDogBreed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"breedName\": \"Husky\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddDogBreed_DogException() throws Exception {
        String breedName = "";
        String errorMessage = "Seems like you have not entered anything in the input. Please try again with a valid input.";

        Mockito.when(dogsService.addDogBreed(breedName)).thenThrow(new DogException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/addDogBreed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"breedName\": \"\"}"))
                .andExpect(MockMvcResultMatchers.content().json(jsonError + errorMessage + "\"}"));
    }

    @Test
    public void testDeleteDogBreed_Success() throws Exception {
        String breedName = "Husky";
        Mockito.when(dogsService.deleteDogBreed(breedName)).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/deleteDogBreed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"breedName\": \"Husky\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteDogBreed_DogException() throws Exception {
        String breedName = " ";
        String errorMessage = "Dog Breed doesn't exist to remove.";

        Mockito.when(dogsService.deleteDogBreed(breedName)).thenThrow(new DogException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/deleteDogBreed")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"breedName\": \" \"}"))
                .andExpect(MockMvcResultMatchers.content().json(jsonError + errorMessage + "\"}"));
    }

    @Test
    public void testAddDogBreedType_Success() throws Exception {
        String breedName = "Husky";
        String breedTypeName = "Siberian";
        Mockito.when(dogsService.addDogBreedType(breedName, breedTypeName)).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/addDogBreedType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"breedName\": \"Husky\", \"breedTypeName\": \"Siberian\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAddDogBreedType_DogException() throws Exception {
        String breedName = " ";
        String breedTypeName = "Huge";
        String errorMessage = "Seems like you have not entered anything in the input. Please try again with a valid input.";

        Mockito.when(dogsService.addDogBreedType(breedName, breedTypeName)).thenThrow(new DogException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/addDogBreedType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"breedName\": \" \", \"breedTypeName\": \"Huge\"}"))
                .andExpect(MockMvcResultMatchers.content().json(jsonError + errorMessage + "\"}"));
    }

    @Test
    public void testDeleteDogBreedType_Success() throws Exception {
        String breedName = "Husky";
        String breedTypeName = "Siberian";
        Mockito.when(dogsService.deleteDogBreedType(breedName, breedTypeName)).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/deleteDogBreedType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"breedName\": \"Husky\", \"breedTypeName\": \"Siberian\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteDogBreedType_DogException() throws Exception {
        String breedName = " ";
        String breedTypeName = "Huge";
        String errorMessage = "No Dog Breed exists with that name.";

        Mockito.when(dogsService.deleteDogBreedType(breedName, breedTypeName)).thenThrow(new DogException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/deleteDogBreedType")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"breedName\": \" \", \"breedTypeName\": \"Huge\"}"))
                .andExpect(MockMvcResultMatchers.content().json(jsonError + errorMessage + "\"}"));
    }

    @Test
    public void testUpdateDogBreedName_BreedNotExists() throws Exception {

		String errorMessage = "Dog Breed doesn't exist to update.";

        Mockito.when(dogsService.updateDogBreedName("Nonexistent Breed", "New Breed")).thenThrow(new DogException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/updateDogBreedName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"breedName\": \"Nonexistent Breed\", \"newBreedName\": \"New Breed\"}"))
                .andExpect(MockMvcResultMatchers.content().json(jsonError + errorMessage + "\"}"));
    }

    @Test
    public void testUpdateDogBreedName_Success() throws Exception {
        Mockito.when(dogsService.updateDogBreedName("Old Breed Name", "New Breed Name")).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/updateDogBreedName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"breedName\": \"Old Breed Name\", \"newBreedName\": \"New Breed Name\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

	@Test
	public void testUpdateDogBreedTypeName_Success() throws Exception {
		dogsService.addDogBreed("Labrador");
		dogsService.addDogBreedType("Labrador", "Yellow");
		dogsService.addDogBreedType("Labrador", "Black");
		Mockito.when(dogsService.updateDogBreedTypeName("Labrador", "Yellow", "Golden")).thenReturn("success");

		mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/updateDogBreedTypeName")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"breedName\": \"Labrador\", \"breedTypeName\": \"Yellow\", \"newBreedTypeName\": \"Golden\"}"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testUpdateDogBreedTypeName_BreedNotExists() throws Exception {
		String errorMessage = "Dog Breed doesn't exist. Please add that dog breed first.";

		Mockito.when(dogsService.updateDogBreedTypeName("Nonexistent Breed", "Type", "New Type")).thenThrow(new DogException(errorMessage));

		mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/updateDogBreedTypeName")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"breedName\": \"Nonexistent Breed\", \"breedTypeName\": \"Type\", \"newBreedTypeName\": \"New Type\"}"))
				.andExpect(MockMvcResultMatchers.content().json(jsonError + errorMessage + "\"}"));
	}

	@Test
	public void testUpdateDogBreedTypeName_TypeNotExists() throws Exception {
		String errorMessage = "Breed Type doesn't exist to update.";
		dogsService.addDogBreed("Labrador");
		dogsService.addDogBreedType("Labrador", "Black");
		Mockito.when(dogsService.updateDogBreedTypeName("Labrador", "Yellow", "Golden")).thenThrow(new DogException(errorMessage));

		mockMvc.perform(MockMvcRequestBuilders.post("/dogsApi/updateDogBreedTypeName")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"breedName\": \"Labrador\", \"breedTypeName\": \"Yellow\", \"newBreedTypeName\": \"Golden\"}"))
				.andExpect(MockMvcResultMatchers.content().json(jsonError + errorMessage + "\"}"));
	}

}
