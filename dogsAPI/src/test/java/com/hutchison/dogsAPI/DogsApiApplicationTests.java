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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DogsApiApplicationTests {

	private MockMvc mockMvc;

	private final String jsonError = "{\"code\" : 400, \"message\" : \"";

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


}
