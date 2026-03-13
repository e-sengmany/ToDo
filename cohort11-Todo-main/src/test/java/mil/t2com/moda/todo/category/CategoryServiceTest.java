package mil.t2com.moda.todo.category;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    // Start using when refactoring
//    @BeforeEach
//    void setUp() {
    ////        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void shouldSaveNewCategory() {
        // Arrange
        Category newCategory = new Category("delayed");
        newCategory.setId(1L);

        // Act
        when(categoryRepository.save(newCategory)).thenReturn(newCategory);

        Category result = categoryService.saveCategory(newCategory);

        // Assert
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getLabel()).isEqualTo("delayed");

        verify(categoryRepository, only()).save(newCategory);
    }

}