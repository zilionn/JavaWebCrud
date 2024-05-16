package persistence;

import java.sql.SQLException;
import java.util.List;

public interface ICrud<T> {
	
	public void add(T t) throws SQLException, ClassNotFoundException;
    public void update(T t) throws SQLException, ClassNotFoundException;
    public void delete(T t) throws SQLException, ClassNotFoundException;
    public T search(T t) throws SQLException, ClassNotFoundException;
    public List<T> list() throws SQLException, ClassNotFoundException;
 
}