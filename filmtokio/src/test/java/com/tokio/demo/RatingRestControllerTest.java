package com.tokio.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.security.Principal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)

public class RatingRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRatings() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ratings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetRatingById() throws Exception {

        int id = 2;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ratings/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testPostRating() throws Exception {

        String ratingJson = """
                {
                     "score": 5,
                     "film": { "id": 2 }
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/api/ratings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ratingJson)
                        .principal(new Principal() {
                            @Override
                            public String getName() {
                                return "coco"; // simulated username
                            }
                        }))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteRating() throws Exception {
        Long id = 9L;
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/ratings/{id}", id))
                .andExpect(status().isOk());
    }


}
