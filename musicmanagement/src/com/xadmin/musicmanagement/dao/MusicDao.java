package com.xadmin.musicmanagement.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.musicmanagement.bean.Music;



public class MusicDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/HOSPITAL?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Put your code here";
	//private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private static final String INSERT_MUSICS_SQL = "INSERT INTO musics" + "  (title, singers, country) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_MUSICS_BY_ID = "select id,title,singers,country from musics where id =?";
	private static final String SELECT_ALL_MUSICS = "select * from musics";
	private static final String DELETE_MUSICS_SQL = "delete from musics where id = ?;";
	private static final String UPDATE_MUSICS_SQL = "update musics set title = ?,singers= ?, country =? where id = ?;";
	public MusicDao() {
	
	
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertMusic(Music music) throws SQLException {
		System.out.println(INSERT_MUSICS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MUSICS_SQL)) {
			preparedStatement.setString(1, music.getTitle());
			preparedStatement.setString(2, music.getSingers());
			preparedStatement.setString(3, music.getCountry());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Music selectMusic(int id) {
		Music music = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MUSICS_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String title = rs.getString("title");
				String singers = rs.getString("singers");
				String Country = rs.getString("country");
				music = new Music(id, title, singers, Country);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return music;
	}

	public List<Music> selectAllMusics() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Music> musics = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MUSICS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String singers = rs.getString("singers");
				String Country = rs.getString("country");
				musics.add(new Music(id, title, singers, Country));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return musics;
	}

	public boolean deleteMusic(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_MUSICS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateMusic(Music music) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_MUSICS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, music.getTitle());
			statement.setString(2, music.getSingers());
			statement.setString(3, music.getCountry());
			statement.setInt(4, music.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}


}
