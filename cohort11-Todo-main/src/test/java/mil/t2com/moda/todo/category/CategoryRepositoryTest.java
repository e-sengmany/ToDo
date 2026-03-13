package mil.t2com.moda.todo.category;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void shouldSaveNewCategory() {
        // Arrange
        Category newCategory = new Category("important");

        // Act
        Category savedNewCategory = categoryRepository.save(newCategory);
        Optional<Category> result = categoryRepository.findById(savedNewCategory.getId());

        // Assert
        assertEquals("important", result.get().getLabel());
        assertThat(result.get().getLabel()).isEqualTo(newCategory.getLabel());
        // Add category value test
        assertThat(result.get().getLabel()).isEqualTo(newCategory.getLabel());
        assertThat(result.get()).isEqualTo(newCategory);
    }
}