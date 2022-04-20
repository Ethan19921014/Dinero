package dao;

import java.sql.SQLException;
import java.util.List;

import model.Profiles;

public interface ProfilesDao {

	void insertProfiles(Profiles Profiles) throws SQLException;

	Profiles selectProfiles(long ProfileID);

	List<Profiles> selectAllProfiles();

	boolean deleteProfiles(int ProfileID) throws SQLException;

	boolean updateProfiles(Profiles Profiles) throws SQLException;

	boolean deletProfiles(int ProfileID) throws SQLException;
	
	
}