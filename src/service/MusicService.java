package service;

import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicService {

    public void createMusicTable() throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS music (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL)");
        }
    }

    public void insertMusicInitialData() throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("MERGE INTO music (id,name) KEY(id) VALUES " +
                    "(1,'Bohemian Rhapsody')," +
                    "(2,'Stairway to Heaven')," +
                    "(3,'Imagine')," +
                    "(4,'Sweet Child O Mine')," +
                    "(5,'Hey Jude')," +
                    "(6,'Hotel California')," +
                    "(7,'Billie Jean')," +
                    "(8,'Wonderwall')," +
                    "(9,'Smells Like Teen Spirit')," +
                    "(10,'Let It Be')," +
                    "(11,'I Want It All')," +
                    "(12,'November Rain')," +
                    "(13,'Losing My Religion')," +
                    "(14,'One')," +
                    "(15,'With or Without You')," +
                    "(16,'Sweet Caroline')," +
                    "(17,'Yesterday')," +
                    "(18,'Dont Stop Believin')," +
                    "(19,'Crazy Train')," +
                    "(20,'Always')");
        }
    }

    public List<String> getAllMusic() throws SQLException {
        List<String> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM music ORDER BY id")) {
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        }
        return list;
    }

    public List<String> getMusicWithoutMAndT() throws SQLException {
        List<String> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT name FROM music WHERE LOWER(name) NOT LIKE '%m%' AND LOWER(name) NOT LIKE '%t%'")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("name"));
            }
        }
        return list;
    }

    public void addMusic(String name) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO music (name) VALUES (?)")) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }
}