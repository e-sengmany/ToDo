package mil.t2com.moda.todo.task;

//@SpringBootTest
//@Transactional
//@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private TaskService taskService;
//
//    // Setup test objects
//
//    @BeforeEach
//    void setUp() {
//        learnTdd = new Task( "Learn TDD", "research TDD", false, started);
//    }
//
//    @Test
//    public void shouldCreateTask() throws Exception {
//        String learnTddJson = objectMapper.writeValueAsString(learnTdd);
//
//        MvcResult savedTask = mockMvc.perform(MockMvcRequestBuilders
//                        .post("/api/v1/task")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(learnTddJson))
//                .andReturn();
//        String expectType = savedTask.getRequest().getContentType();
//        Task expectedTask = objectMapper.readValue(savedTask.getResponse().getContentAsString(), Task.class);
//
//        assertEquals(expectType, "application/json");
//        assertEquals(expectedTask.getTitle(), learnTddJson.getTitle());
//        assertEquals(expectedTask.getCategory().getLabel(), learnTddJson.getCategory().getLabel());
//    }
//
//    @Test
//    public void shouldGetAllTasks() throws Exception {
//        taskRepository.save(firstTask);
//        taskRepository.save(secondTask);
//
//        mockMvc.perform(get("/api/v1/task"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.*", hasSize(2)))
//                //.andExpect(jsonPath("$[0].id").value(1L))
//                .andExpect(jsonPath("$[0].title").value("Learn Tdd"))
//                .andExpect(jsonPath("$[1].title").value("Practice Tdd"))
//                //.andExpect(jsonPath("$[0].category.id").value(1L))
//                .andExpect(jsonPath("$[0].category.label").value("normal"))
//                .andExpect(jsonPath("$[1].cetegory.label").value("important"));
//                //.andExpect(jsonPath("$[1].id").value(2L))
//    }
//
//    @Test
//    public void shouldGetTaskById() throws Exception {
//        Task savedTask = taskRepository.save(learnTdd);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/task/" + savedTask.getId()))
//                .andExpect(status().isOk())
//                //.andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.title").value(""))
//                //.andExpect(jsonPath("$.category.id").value(1L))
//                .andExpect(jsonPath("$.category.label").value("not started"));
//    }
}
