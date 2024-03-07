// package sushigo.api;

// import org.junit.jupiter.api.BeforeEach;
// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpSession;
// import jakarta.ws.rs.core.*;

// import sushigo.api.controllers.SushiGoController;
// import sushigo.api.models.StartInputDTO;
// import sushigo.domain.ISushiGo;
// import sushigo.domain.ISushiGoFactory;
// import sushigo.domain.SushiGoFactory;
// import sushigo.persistence.ISushiGoRepository;
// import sushigo.persistence.SushiGoRepository;

// public class SushiGoControllerTest {

// private SushiGoController controller;
// private HttpSession session;
// private String gameId;

// @BeforeEach
// private void setUp() {
// createMockSession();
// createController();
// }

// private void createController() {
// ISushiGoFactory factory = new SushiGoFactory();
// ISushiGoRepository repository = new SushiGoRepository();
// controller = new SushiGoController(factory, repository);
// }

// private void createMockSession() {
// session = mock(HttpSession.class);
// doAnswer(invocation -> {
// if (invocation.getArgument(0).equals("gameId")) {
// gameId = invocation.getArgument(1);
// }
// return null;
// }).when(session).setAttribute(any(String.class), any(String.class));
// when(session.getAttribute("gameId")).thenAnswer(invocation -> gameId);
// }

// private Response executeStartRequest() {
// HttpServletRequest request = mock(HttpServletRequest.class);
// when(request.getSession(any(Boolean.class))).thenReturn(session);
// return controller.start(request, startInput("Mario", "Luigi"));
// }

// private Response startInput(String player1, String player2) {
// var input = new StartInputDTO();
// input.setPlayer1(player1);
// input.setPlayer2(player2);
// return input;
// }
// }
