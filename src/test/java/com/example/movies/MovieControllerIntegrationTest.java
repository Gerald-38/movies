package com.example.movies;

import java.nio.charset.Charset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.movies.controller.MovieController;
import com.example.movies.repository.MovieRepository;


@RunWith(SpringRunner.class) 
@AutoConfigureMockMvc
@SpringBootTest
public class MovieControllerIntegrationTest {

	 @MockBean
	    private MovieRepository movieRepository;
	    
	    @Autowired
	    MovieController movieController;

	    @Autowired
	    private MockMvc mockMvc;
	    
	    
	    @Test
	    public void whenPostRequestToMoviesAndValidMovie_thenCorrectResponse() throws Exception {
	    	MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
	        String movie = "{\"title\": \"The Movie\", \"description\" : \"the description\", \"restrictionAge\": 10 }";
	        mockMvc.perform(MockMvcRequestBuilders.post("/eleves/post")
	          .content(movie)
	          .contentType(MediaType.APPLICATION_JSON_UTF8))
	          .andExpect(MockMvcResultMatchers.status().isOk())
	          .andExpect(MockMvcResultMatchers.content()
	            .contentType(textPlainUtf8));
	    }


	    @Test
	    public void whenPostRequestToMoviesAndInValidMovie_thenCorrectResponse() throws Exception {
	        String movie = "{\"title\": \"\", \"description\" : \"this is a description\", \"restrictionAge\": 10}";
	        mockMvc.perform(MockMvcRequestBuilders.post("/movies/post")
	          .content(movie)
	          .contentType(MediaType.APPLICATION_JSON_UTF8))
	          .andExpect(MockMvcResultMatchers.status().isBadRequest());
	        }
	
}
