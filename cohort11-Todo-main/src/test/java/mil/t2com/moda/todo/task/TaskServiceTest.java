package mil.t2com.moda.todo.task;

import mil.t2com.moda.todo.category.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    // Start using when refactoring
//    @BeforeEach
//    void setUp() {
////        MockitoAnnotations.openMocks(this);
//    }

    @Test
    void shouldSaveNewTask() {
        // Arrange
        Category newCategory = new Category("important");
        Task newTask = new Task("Learn about Mocks", "Learn about Inject mocks", false, newCategory);
        newTask.setId(1L);

        // Act
        when(taskRepository.save(newTask)).thenReturn(newTask);

        Task result = taskService.saveTask(newTask);

        // Assert
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Learn about Mocks");
        assertThat(result.getCategory().getLabel()).isEqualTo("important");

        verify(taskRepository, only()).save(newTask);
    }

}