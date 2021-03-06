package bike;

import java.sql.*;

public class Notification {
    private Database db;

    public Notification() {
    }

    public int notiNews() {
        String sql;
        Connection connect = null;
        int unRead = 0,read = 0,countNews=0;
        Statement s = null;
        try {
            connect = db.connectDb("ja","jaja036");
            s = connect.createStatement();
            
            sql = "SELECT COUNT(DISTINCT newsID) AS countRead FROM News_Reader Where userID=12345";
            ResultSet rs = s.executeQuery(sql);
            while(rs.next()){
                read = rs.getInt("countRead");
            }
            
            sql = "SELECT COUNT(newsID) AS countNews FROM News";
            rs = s.executeQuery(sql);
            while(rs.next()){
                countNews = rs.getInt("countNews");
            }
            
            unRead = countNews-read;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
                connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return unRead;
    }

    public boolean notiTime(Object obj,int hr, int min, int sec) {
          if(obj instanceof Sharing){
            if(hr==0 && min == 10 && sec ==0){
              return true;
            }
          }else if(obj instanceof Repair){
            if(hr==0 && min == 3 && sec ==0){
                return true;
            }
          }
          return false;
    }

    public String notiRepairFinish() {
        return "---Notification---\nRepairs completed,You can pick up it. From this day onwards.";
    }

    public String notiRepairIncreseTime(String oldDate, String detail, String newDate) {
        return "---Notification---\n>>Old Date :\n" + oldDate + "\n>>Detail :\n" + detail + "\n>>New Date :\n" + newDate;
    }
}

