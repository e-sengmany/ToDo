package mil.t2com.moda.todo.task;

import mil.t2com.moda.todo.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    // Setup test objects
    private String title;
    private String description;
    private Boolean isComplete;
    private Category started;
    private Task firstTask = new Task("Learn Tdd", "research Tdd", false, new Category("normal"));
    private Task secondTask = new Task("Practice Tdd", "practice Tdd", false, new Category("important"));
    Task learnTdd;

   @BeforeEach
    void setUp() {
       started = new Category("started");
       learnTdd = new Task( "Learn TDD", "research TDD", false, started);
    }

    @Test
    public void shouldCreateTask() throws Exception {
        String learnTddJson = objectMapper.writeValueAsString(learnTdd);

        MvcResult savedTask = mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(learnTddJson))
                .andReturn();
        String expectType = savedTask.getRequest().getContentType();
        Task expectedTask = objectMapper.readValue(savedTask.getResponse().getContentAsString(), Task.class);

        assertEquals(expectType, "application/json");
        assertEquals(expectedTask.getTitle(), learnTdd.getTitle());
        assertEquals(expectedTask.getCategory().getLabel(), learnTdd.getCategory().getLabel());
    }

    @Test
    public void shouldGetAllTasks() throws Exception {
        taskRepository.save(firstTask);
        taskRepository.save(secondTask);

        mockMvc.perform(get("/api/v1/task"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("Learn Tdd"))
                .andExpect(jsonPath("$[1].title").value("Practice Tdd"))
                .andExpect(jsonPath("$[0].category.id").value(1L))
                .andExpect(jsonPath("$[0].category.label").value("normal"))
                .andExpect(jsonPath("$[1].category.label").value("important"))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    public void shouldGetTaskById() throws Exception {
        Task savedTask = taskRepository.save(learnTdd);

        mockMvc.perform(get("/api/v1/task/" + savedTask.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value(""))
                .andExpect(jsonPath("$.category.id").value(1L))
                .andExpect(jsonPath("$.category.label").value("not started"));
    }

}
