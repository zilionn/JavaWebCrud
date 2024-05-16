package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Player;

@WebServlet("/player")
public class PlayerServelet extends HttpServlet {
    private static final long serialVersionUID = 1L;
  
    public PlayerServelet() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd = request.getParameter("button");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String birthDate = request.getParameter("birthDate");  // Corrigido aqui
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
        
        String output = "";
        String error = "";
        Player p = new Player();
        List<Player> players = new ArrayList<>();
        
        try {
            if (cmd.contains("Add Player") || cmd.contains("Update Player")) {
                p.setName(name);
                p.setBirthDate(LocalDate.parse(birthDate));
                p.setHeight(Double.parseDouble(height));
                p.setWeight(Double.parseDouble(weight));
            }
            
            if (!cmd.contains("List Players")) {
                p.setId(Integer.parseInt(id));
            }

            switch (cmd) {
                case "Add Player":
                    addPlayer(p);
                    output = "Player added successfully";
                    break;
                case "Update Player":
                    updatePlayer(p);
                    output = "Player updated successfully";
                    break;
                case "Delete Player":
                    deletePlayer(p);
                    output = "Player deleted successfully";
                    break;
                case "Search":
                    p = searchPlayer(p);
                    break;
                case "List Players":
                    players = listPlayers();
                    break;
                default:
                    error = "Invalid command";
                    break;
            }
        } catch (SQLException | ClassNotFoundException e) {
            error = e.getMessage();
        } finally {
            request.setAttribute("output", output);
            request.setAttribute("error", error);
            request.setAttribute("player", p);
            request.setAttribute("players", players);
            
            RequestDispatcher rd = request.getRequestDispatcher("player.jsp");
            rd.forward(request, response);
        }        
    }

    private void addPlayer(Player p) throws SQLException, ClassNotFoundException {
        System.out.println(p);    
    }

    private void updatePlayer(Player p) throws SQLException, ClassNotFoundException {
        System.out.println(p);        
    }

    private void deletePlayer(Player p) throws SQLException, ClassNotFoundException {
        System.out.println(p.getId());    
    }

    private Player searchPlayer(Player p) throws SQLException, ClassNotFoundException {
        p.setName("Fulano");
        p.setBirthDate(LocalDate.parse("2004-09-21"));  // Corrigido o formato da data
        p.setHeight(1.77);
        p.setWeight(74.00);
        
        return p;        
    }
    private List<Player> listPlayers() throws SQLException, ClassNotFoundException {
        List<Player> players = new ArrayList<>();
        
        Player player1 = new Player();
        player1.setId(1);
        player1.setName("Player One");
        player1.setBirthDate(LocalDate.parse("1990-01-01"));
        player1.setHeight(1.80);
        player1.setWeight(75.0);
        players.add(player1);
        
        Player player2 = new Player();
        player2.setId(2);
        player2.setName("Player Two");
        player2.setBirthDate(LocalDate.parse("1992-05-15"));
        player2.setHeight(1.85);
        player2.setWeight(80.0);
        players.add(player2);
        
        Player player3 = new Player();
        player3.setId(3);
        player3.setName("Player Three");
        player3.setBirthDate(LocalDate.parse("1995-10-30"));
        player3.setHeight(1.90);
        player3.setWeight(85.0);
        players.add(player3);
        
        return players;
    }
}