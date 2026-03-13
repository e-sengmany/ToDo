package mil.t2com.moda.todo.category;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    CategoryService categoryService;

    @Captor
    ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);

    @Test
    void shouldSaveNewCategory() throws Exception {
        // Arrange
        Category newCategory = new Category("normal");
        newCategory.setId(1L);

        when(categoryService.saveCategory(any(Category.class))).thenReturn(newCategory);

        // Act
        mockMvc.perform(post("/api/v1/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCategory)))
                // result matchers
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.label").value("Normal"));

        // Assert
        verify(categoryService, only()).saveCategory(captor.capture());
        assertThat(captor.getValue()).usingRecursiveComparison().isEqualTo(newCategory);

        verify(categoryService, times(1)).saveCategory(any(Category.class));
    }
}