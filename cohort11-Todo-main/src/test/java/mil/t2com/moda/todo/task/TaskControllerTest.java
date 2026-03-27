package mil.t2com.moda.todo.task;

import mil.t2com.moda.todo.category.Category;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    TaskService taskService;

    TaskRepository taskRepository;

    @Captor
    ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
    @Captor
    private ArgumentCaptor<List<Task>> captors;

    String enablement = "enablement";
    Task learnHttpMethods;
    Task learnCaptor;
    Category enableCategory = new Category(enablement);
    Category studyCategory = new Category("study");
    List<Task> tasks = new ArrayList<>();

    //Start using when refactoring
    @BeforeEach
    void setUp() {
        Category newCategory = new Category("important");
        learnHttpMethods = new Task("Learn about testing HTTP request/response", "Learn how to use WebMvcTest", false, enableCategory);
        learnHttpMethods.setId(1L);

        learnCaptor = new Task(
                "Learn about testing HTTP request/response",
                "Learn how to use WebMvcTest",
                false,
                studyCategory);


    }
    @Test
    void shouldSaveNewTask() throws Exception {
        // Arrange

        when(taskService.saveTask(any(Task.class))).thenReturn(learnHttpMethods);

        // Act
        mockMvc.perform(post("/api/v1/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(learnHttpMethods)))
                // result matchers
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(matchesPattern("Learn about.*request/response")))
                .andExpect(jsonPath("$.description").value(containsString("Learn how to")))
                .andExpect(jsonPath("$.category.label").value("enablement"))
                .andDo(print()
                );

        // Assert
        verify(taskService, times(1)).saveTask(any(Task.class));
    }

    @Test
    void shouldSaveNewTaskUsingCaptor() throws Exception {
        // Arrange
        when(taskService.saveTask(any(Task.class))).thenReturn(learnCaptor);

        // Act
        mockMvc.perform(post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(learnCaptor)))
                // result matchers
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(matchesPattern("Learn about.*request/response")))
                .andExpect(jsonPath("$.description").value(containsString("Learn how to")))
                .andExpect(jsonPath("$.category.label").value("study"))
                .andDo(print()
                );

        // Assert
        verify(taskService,only()).saveTask(captor.capture());
        assertThat(captor.getValue()).usingRecursiveComparison().isEqualTo(learnCaptor);

        verify(taskService, only()).saveTask(any(Task.class));
    }

    @Test
    void shouldGetAllTasks() throws Exception{
        //Arrange
        //Act
        tasks.addAll(List.of(learnHttpMethods, learnHttpMethods));
        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/v1/task"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andDo(print()
                );

        //verify(taskService, only()).saveTask(any(Task.class));
//        verify(taskService).getAllTasks(captors.capture());

        //assertThat(captor.getAllValues()).usingRecursiveComparison().isEqualTo(tasks);

    }

    @Test
    void shouldGetById() throws Exception{
        when(taskRepository.findById(1L)).thenReturn(Optional.of(learnHttpMethods));

//        Task result = taskService.findTaskById(1L);
//        verify(taskRepository, only()).findById(1L);
//        assertThat(result).isEqualTo(learnHttpMethods);
        mockMvc.perform(get("/api/v1/task"))
                .andExpect(status().isOk());
//                .andExpect()
    }
}