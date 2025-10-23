/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Freez
 */
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement dropScheduleByStudent;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        
        try{
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (SEMESTER, STUDENTID, COURSECODE, STATUS, TIMESTAMP) values (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getStudentID());
            addScheduleEntry.setString(3, entry.getCourseCode());
            addScheduleEntry.setString(4, entry.getCourseStatus());
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<>();
        
        try{
            getScheduleByStudent = connection.prepareStatement("select SEMESTER, COURSECODE, STATUS, TIMESTAMP from app.schedule where STUDENTID = ? and SEMESTER = ? order by TIMESTAMP");
            getScheduleByStudent.setString(1, studentID);
            getScheduleByStudent.setString(2, semester);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next()){
                schedule.add(new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), studentID, resultSet.getString(3), resultSet.getTimestamp(4)));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return schedule;
    }
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode){
        connection = DBConnection.getConnection();
        int scheduledStudents = 0;
        
        try{
            getScheduledStudentCount = connection.prepareStatement("select count(STUDENTID) from app.schedule where SEMESTER = ? and COURSECODE = ?");
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
            while(resultSet.next()){
                scheduledStudents = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return scheduledStudents;
    }
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode) {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedules = new ArrayList<>();
        try{
            getScheduledStudentsByCourse = connection.prepareStatement("select STUDENTID, TIMESTAMP from app.schedule where SEMESTER = ? and COURSECODE = ? and STATUS = 'S' order by TIMESTAMP");
            getScheduledStudentsByCourse.setString(1, semester);
            getScheduledStudentsByCourse.setString(2, courseCode);
            resultSet = getScheduledStudentsByCourse.executeQuery();
            
            while(resultSet.next()){
                schedules.add(new ScheduleEntry(semester, courseCode, resultSet.getString(1), "S", resultSet.getTimestamp(2)));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return schedules;
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlist = new ArrayList<>();
        try{
            getWaitlistedStudentsByCourse = connection.prepareStatement("select STUDENTID, TIMESTAMP from app.schedule where SEMESTER = ? and COURSECODE = ? and STATUS = 'W' order by TIMESTAMP");
            getWaitlistedStudentsByCourse.setString(1, semester);
            getWaitlistedStudentsByCourse.setString(2, courseCode);
            resultSet = getWaitlistedStudentsByCourse.executeQuery();

            while(resultSet.next()){
                waitlist.add(new ScheduleEntry(semester, courseCode, resultSet.getString(1), "W", resultSet.getTimestamp(2)));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return waitlist;
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode){
        connection = DBConnection.getConnection();
        try{
            dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where SEMESTER = ? and STUDENTID = ? and COURSECODE = ?");
            dropStudentScheduleByCourse.setString(1, semester);
            dropStudentScheduleByCourse.setString(2, studentID);
            dropStudentScheduleByCourse.setString(3, courseCode);
            dropStudentScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try{
            dropScheduleByCourse = connection.prepareStatement("delete from app.schedule where SEMESTER = ? and COURSECODE = ?");
            dropScheduleByCourse.setString(1, semester);
            dropScheduleByCourse.setString(2, courseCode);
            dropScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }        
    }
    
    public static void updateScheduleEntry(String semester, ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try{
            updateScheduleEntry = connection.prepareStatement("update app.schedule set STATUS = ? where SEMESTER = ? and STUDENTID = ? and COURSECODE = ?");
            updateScheduleEntry.setString(1, entry.getCourseStatus());
            updateScheduleEntry.setString(2, entry.getSemester());
            updateScheduleEntry.setString(3, entry.getStudentID());
            updateScheduleEntry.setString(4, entry.getCourseCode());
            updateScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }        
    }
    public static void dropScheduleByStudent(String studentID){
        connection = DBConnection.getConnection();
        try{
            dropScheduleByStudent = connection.prepareStatement("delete from app.schedule where STUDENTID = ?");
            dropScheduleByStudent.setString(1, studentID);
            dropScheduleByStudent.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }    
}
