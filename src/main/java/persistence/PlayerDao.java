package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Player;

public class PlayerDao implements ICrud<Player> {
	
	private GenericDao gDao;

	public PlayerDao(GenericDao gdao) {
		this.gDao = gdao;
	}
	
	@Override
	public void add(Player p) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		Date date = Date.valueOf(p.getBirthDate());
		String sql = "INSERT INTO player VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, p.getId());
		ps.setString(2, p.getName());
		ps.setDate(3, date);
		ps.setDouble(4, p.getHeight());
		ps.setDouble(5, p.getWeight());
		ps.setInt(6, p.getTeam().getId());
		ps.execute();
		ps.close();
		c.close();	
	}

	@Override
	public void update(Player p) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		Date date = Date.valueOf(p.getBirthDate());
		String sql = "UPDATE player SET name=?, birthDate=?, height=?, weight=?, teamId=? WHERE id=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, p.getName());
		ps.setDate(2, date);
		ps.setDouble(3, p.getHeight());
		ps.setDouble(4, p.getWeight());
		ps.setInt(5, p.getTeam().getId());
		ps.setInt(6, p.getId());
		ps.execute();
		ps.close();
		c.close();	
	}

	@Override
	public void delete(Player p) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "DELETE FROM player WHERE id=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, p.getId());
		ps.execute();
		ps.close();
		c.close();		
	}

	@Override
	public Player search(Player p) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.id AS idPlay, p.name as namePlay, p.birthDate as birthPlay, p.height as heiPlay, p.weight as weiPlay, ");
		sql.append("t.id as idTeam, t.name as nameTeam, t.city as cityTeam ");
		sql.append("FROM player p, team t");
		sql.append("WHERE t.id = p.idTeam");
		sql.append("AND t.id = ?");
		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setInt(1, p.getId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			
		}
		rs.close();
		ps.close();
		return p;
	}

	@Override
	public List<Player> list() throws SQLException, ClassNotFoundException {
		List<Player> players = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT id, name, birthDate, height, weight FROM player";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Player p = new Player();		
			p.setId(rs.getInt("id"));
			p.setName(rs.getString("name"));
			java.sql.Date sqlDate = rs.getDate("birthDate");
		    LocalDate localDate = sqlDate.toLocalDate();
		    p.setBirthDate(localDate);
		    p.setHeight(rs.getDouble("height"));
		    p.setWeight(rs.getDouble("weight"));
		    
		    players.add(p);
		}
		rs.close();
		ps.close();
		c.close();

		return players;
	}
}
