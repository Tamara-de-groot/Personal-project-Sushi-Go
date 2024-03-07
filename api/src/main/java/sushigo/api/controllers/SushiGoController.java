package sushigo.api.controllers;

import java.util.UUID;

import jakarta.servlet.http.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import sushigo.api.models.SushiGoDTO;
import sushigo.api.models.PlayInputDTO;
import sushigo.api.models.StartInputDTO;
import sushigo.domain.ISushiGo;
import sushigo.domain.ISushiGoFactory;
import sushigo.persistence.ISushiGoRepository;

@Path("/sushigo/api")
public class SushiGoController {

    private ISushiGoFactory factory;
    private ISushiGoRepository repository;

    public SushiGoController(ISushiGoFactory factory, ISushiGoRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    @Path("/start")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response start(@Context HttpServletRequest request, StartInputDTO body) {
        HttpSession session = request.getSession(true);
        ISushiGo sushiGo = factory.createNewGame(body.getPlayer1(), body.getPlayer2());
        String gameId = UUID.randomUUID().toString();
        session.setAttribute("gameId", gameId);
        repository.save(gameId, sushiGo);
        SushiGoDTO output = new SushiGoDTO(sushiGo);
        return Response.status(200).entity(output).build();
    }

    @Path("/play")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response play(@Context HttpServletRequest request, PlayInputDTO body) {
        // Retrieve HTTP session.
        HttpSession session = request.getSession(false);

        // Retrieve game ID.
        String gameId = (String) session.getAttribute("gameId");

        // Retrieve the game from the database
        ISushiGo sushiGo = repository.get(gameId);

        // Play a card.
        sushiGo.playCard(body.getIndexToPlay(), body.getOwner());

        // Save the updated game in the database
        repository.save(gameId, sushiGo);

        // Use the game to create a DTO.
        SushiGoDTO output = new SushiGoDTO(sushiGo);

        // Send DTO back in response.
        return Response.status(200).entity(output).build();
    }

}
