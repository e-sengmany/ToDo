package mil.t2com.moda.todo.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    Category newCategory;

    //Start using when refactoring
    @BeforeEach
    void setUp() {
        newCategory = new Category("delayed");
        newCategory.setId(1L);

        //        MockitoAnnotations.openMocks(this);
        }

        @Test
        void shouldSaveNewCategory () {
            // Arrange

            // Act
            when(categoryRepository.save(newCategory)).thenReturn(newCategory);

            Category result = categoryService.saveCategory(newCategory);

            // Assert
            assertThat(result.getId()).isEqualTo(1L);
            assertThat(result.getLabel()).isEqualTo("delayed");

            verify(categoryRepository, only()).save(newCategory);
        }
        @Test
        void shouldFindTaskByLabel () {

            newCategory.getId();

            when(categoryRepository.findByLabel(newCategory.getLabel())).thenReturn(Optional.of(newCategory));
            Optional<Category> result = categoryService.findCategoryByLabel(newCategory.getLabel());

            assertThat(result.get().getLabel()).isEqualTo("delayed");
            verify(categoryRepository, only()).findByLabel(newCategory.getLabel());
        }

        @Test
        void shouldCheckExistingCategoryAndSaveIfExists () {
            //Arrange
            Category createdCategory = new Category("delayed");
            createdCategory.setId(3L);

            //Act
            when(categoryRepository.findByLabel(createdCategory.getLabel())).thenReturn(Optional.empty());
            when(categoryRepository.save(any(Category.class))).thenReturn(createdCategory);

            Category result = categoryService.createCategoryIfNotExists("delayed");
            assertThat(result.getLabel()).isEqualTo("delayed");

            verify(categoryRepository, times(1)).save(any(Category.class));
            verify(categoryRepository, times(1)).findByLabel("delayed");
        }


}

