/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Card;
import model.StudySet;
import model.User;

/**
 *
 * @author LENOVO
 */
public class DAO extends DBContext {

    public ArrayList<User> getAllUser() {
        ArrayList<User> listU = new ArrayList<>();
        String sql = "select * from User_HE161521";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("status"), rs.getInt("userID"));
                listU.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listU;
    }

    public User checkUser(String username, String password) {
        ArrayList<User> listU = getAllUser();
        for (User u : listU) {
            if ((u.getName().equals(username) || u.getEmail().equals(username)) && u.getPass().equals(password)) {
                return u;
            }
        }
        return null;
    }

    public void register(User user) {
        String sql = "INSERT INTO [dbo].[User_HE161521]\n"
                + "           ([name]\n"
                + "           ,[password]\n"
                + "           ,[status]\n"
                + "           ,[email])\n"
                + "     VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getName());
            st.setString(2, user.getPass());
            st.setString(3, user.getStatus());
            st.setString(4, user.getEmail());
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getIdStudySet() {
        int idx = 0;
        String sql = "SELECT TOP 1 * FROM StudySet_HE161521 ORDER BY studysetID DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("studySetID");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return idx;
    }

    public boolean activeAccount(String email) {
        boolean check = false;
        String sql = "update user_he161521 set status='active' where email like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.execute();
            check = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return check;
    }

    public void addStudySet(StudySet set) {
        String sql = "INSERT INTO [dbo].[StudySet_HE161521]\n"
                + "           ([title]\n"
                + "           ,[describe]\n"
                + "           ,[isShare]\n"
                + "           ,[folderID]\n"
                + "           ,[userID])\n"
                + "     VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, set.getTitle());
            st.setString(2, set.getDescribe());
            st.setBoolean(3, true);
            st.setInt(4, set.getFolderId());
            st.setInt(5, set.getUserId());
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addCard(Card c) {
        String sql = "INSERT INTO [dbo].[Card_HE161521]\n"
                + "           ([title]\n"
                + "           ,[describe]\n"
                + "           ,[studySetID])\n"
                + "     VALUES(?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getTitle());
            st.setString(2, c.getDescribe());
            st.setInt(3, c.getStudySetId());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<StudySet> getAllStudySet(User user) {
        ArrayList<StudySet> listS = new ArrayList<>();
        String sql = "SELECT [studySetID]\n"
                + "      ,[title]\n"
                + "      ,[describe]\n"
                + "      ,[isShare]\n"
                + "      ,[folderID]\n"
                + "      ,[userID]\n"
                + "  FROM [dbo].[StudySet_HE161521]\n"
                + "  WHERE userID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StudySet set = new StudySet();
                set.setId(rs.getInt("studySetID"));
                set.setTitle(rs.getString("title"));
                set.setDescribe(rs.getString("describe"));
                set.setIsShare(rs.getBoolean("isShare"));
                set.setFolderId(rs.getInt("folderID"));
                set.setUserId(rs.getInt("userID"));
                listS.add(set);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listS;
    }

    public StudySet getStudySetById(int id) {
        ArrayList<StudySet> listS = new ArrayList<>();
        String sql = "SELECT [studySetID]\n"
                + "      ,[title]\n"
                + "      ,[describe]\n"
                + "      ,[isShare]\n"
                + "      ,[folderID]\n"
                + "      ,[userID]\n"
                + "  FROM [dbo].[StudySet_HE161521]\n"
                + "  WHERE studySetID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StudySet set = new StudySet();
                set.setId(rs.getInt("studySetID"));
                set.setTitle(rs.getString("title"));
                set.setDescribe(rs.getString("describe"));
                set.setIsShare(rs.getBoolean("isShare"));
                set.setFolderId(rs.getInt("folderID"));
                set.setUserId(rs.getInt("userID"));
                return set;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Card> getAllCardInSet(int id) {
        ArrayList<Card> listC = new ArrayList<>();
        String sql = "SELECT [cardID]\n"
                + "      ,[title]\n"
                + "      ,[describe]\n"
                + "      ,[studySetID]\n"
                + "  FROM [dbo].[Card_HE161521]\n"
                + "  WHERE studySetID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Card c = new Card();
                c.setId(rs.getInt("cardID"));
                c.setTitle(rs.getString("title"));
                c.setDescribe(rs.getString("describe"));
                c.setStudySetId(rs.getInt("studySetID"));
                listC.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return listC;
    }

    public User getUserByUserId(int userId) {
        ArrayList<User> listU = getAllUser();
        for (User u : listU) {
            if (u.getId() == userId) {
                return u;
            }
        }
        return null;
    }

    public void deleteStudySet(int id) {
        String sql = "DELETE FROM [dbo].[StudySet_HE161521]\n"
                + "      WHERE studySetID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteAllCard(int studySetId) {
        String sql = "DELETE FROM [dbo].[Card_HE161521]\n"
                + "      WHERE studySetID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, studySetId);
            st.executeUpdate();
            deleteStudySet(studySetId);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateStudySet(StudySet set) {
        String sql = "UPDATE [dbo].[StudySet_HE161521]\n"
                + "   SET [title] = ?\n"
                + "      ,[describe] = ?\n"
                + "      ,[isShare] = ?\n"
                + " WHERE studySetID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, set.getTitle());
            st.setString(2, set.getDescribe());
            st.setBoolean(3, set.isIsShare());
            st.setInt(4, set.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateCard(Card c) {
        String sql = "UPDATE [dbo].[Card_HE161521]\n"
                + "   SET [title] = ?\n"
                + "      ,[describe] = ?\n"
                + " WHERE cardID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getTitle());
            st.setString(2, c.getDescribe());
            st.setInt(3, c.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public ArrayList<StudySet> getAllStudySet() {
        ArrayList<StudySet> listS = new ArrayList<>();
        String sql = "Select * from StudySet_HE161521";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                StudySet set = new StudySet(rs.getInt("studySetID"), rs.getString("title"), rs.getString("describe"), rs.getBoolean("isShare"), rs.getInt("folderID"), rs.getInt("userID") );
                listS.add(set);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listS;
    }
}
