package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Team;

public class TeamDao implements ICrud<Team> {
	
	private GenericDao gDao;
	
	public TeamDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public void add(Team t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO team VALUES (?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getId());
		ps.setString(2, t.getName());
		ps.setString(3, t.getCity());
		ps.execute();
		ps.close();
		c.close();			
	}

	@Override
	public void update(Team t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "UPDATE team SET name=?, city=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, t.getName());
		ps.setString(2, t.getCity());
		ps.execute();
		ps.close();
		c.close();		
	}

	@Override
	public void delete(Team t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "DELETE FROM team WHERE id=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getId());
		ps.execute();
		ps.close();
		c.close();		
	}

	@Override
	public Team search(Team t) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT id, name, city FROM team WHERE id=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, t.getId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setCity(rs.getString("city"));
		}
		rs.close();
		ps.close();
		return t;
	}

	@Override
	public List<Team> list() throws SQLException, ClassNotFoundException {
		List<Team> teams = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT id, name, city FROM team WHERE id=?";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			Team t = new Team();
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setCity(rs.getString("city"));
			
			teams.add(t);
		}
		rs.close();
		ps.close();
		return teams;
	}

}
