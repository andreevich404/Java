package dao;

import util.DbUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDao {

    public void createTable() throws Exception {
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS music (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL UNIQUE)");
        }
    }

    public void initMusic() throws Exception {
        createTable();
        String[] songs = {
                "Bohemian Rhapsody", "Stairway to Heaven", "Imagine", "Sweet Child O Mine",
                "Hey Jude", "Hotel California", "Billie Jean", "Wonderwall",
                "Smells Like Teen Spirit", "Let It Be", "I Want It All", "November Rain",
                "Losing My Religion", "One", "With or Without You", "Sweet Caroline",
                "Yesterday", "Dont Stop Believin", "Crazy Train", "Always"
        };
        try (Connection conn = DbUtil.getConnection()) {
            for (String song : songs) {
                try (PreparedStatement ps = conn.prepareStatement(
                        "MERGE INTO music (name) KEY(name) VALUES (?)")) {
                    ps.setString(1, song);
                    ps.executeUpdate();
                }
            }
        }
    }

    public List<String> getAllMusic() throws Exception {
        List<String> result = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * " + "FROM music ORDER BY id")) {
            while (rs.next()) {
                result.add(rs.getString("name"));
            }
        }
        return result;
    }

    public List<String> getMusicWithoutMAndT() throws Exception {
        List<String> result = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT name FROM music WHERE LOWER(name) NOT LIKE '%m%' AND LOWER(name) NOT LIKE '%t%' ORDER BY id");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(rs.getString("name"));
            }
        }
        return result;
    }

    public void addMusic(String name) throws Exception {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO music(name) VALUES(?)")) {
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }
}