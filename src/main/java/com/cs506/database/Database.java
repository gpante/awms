package com.cs506.database;

import java.sql.*;
import com.cs506.workshop.WorkshopRequest;
import java.util.Date;

public class Database {
  
  Connection db = null;
  
  public Database() {
    try {
        this.db = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement statement = db.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS account ("
                          + "account_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                          + "username TEXT NOT NULL UNIQUE, "
                          + "password TEXT NOT NULL, "
                          + "permission_lvl INTEGER DEFAULT 0)");
        statement.execute("CREATE TABLE IF NOT EXISTS workshop ("
                          + "workshop_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                          + "workshop_type_name TEXT, "
                          + "workshop_type INT, "
                          + "group_name TEXT NOT NULL, "
                          + "contact_name TEXT NOT NULL, "
                          + "contact_phone TEXT NOT NULL, "
                          + "contact_email TEXT NOT NULL, "
                          + "location TEXT NOT NULL, "
                          + "consist_75 INTEGER NOT NULL, "
                          + "date TEXT NOT NULL, "
                          + "start TEXT NOT NULL, "
                          + "end TEXT NOT NULL, "
                          + "alternate_date TEXT NOT NULL, "
                          + "alternate_start TEXT, "
                          + "alternate_end TEXT NOT NULL, "
                          + "participants INTEGER NOT NULL, "
                          + "areas INTEGER NOT NULL, "
                          + "how_you_heard TEXT, "
                          + "special TEXT)");
        statement.execute("CREATE TABLE IF NOT EXISTS signed_up ("
                          + "account_id INTEGER, "
                          + "workshop_id INTEGER, "
                          + "PRIMARY KEY (account_id, workshop_id), "
                          + "FOREIGN KEY (account_id) "
                          + "REFERENCES account (account_id) "
                          + "ON DELETE CASCADE "
                          + "ON UPDATE CASCADE, "
                          + "FOREIGN KEY (workshop_id) "
                          + "REFERENCES workshop (workshop_id) "
                          + "ON DELETE CASCADE "
                          + "ON UPDATE CASCADE)");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
  }
  
  public void addWorkshop(WorkshopRequest request) {
    
    String date = request.getDate().toString();
    String start = request.getStartTime().toString();
    String end = request.getEndTime().toString();
    String alternateDate = new String();
    String alternateStart = new String();
    String alternateEnd = new String();
    int consist75 = 0;
    if (request.getAlternateDate() != null) {
      alternateDate = request.getAlternateDate().toString();
    }
    if (request.getAlternateStartTime() != null) {
      alternateStart = request.getAlternateStartTime().toString();
    }
    if (request.getAlternateEndTime() != null) {
      alternateEnd = request.getAlternateEndTime().toString();
    }
    if (request.isConsist75() == false) {
      consist75 = 0;
    }
    if (request.isConsist75() == true) {
      consist75 = 1;
    }
    
    String sql = String.format("INSERT INTO account (" 
                  + "group_name, contact_name, contact_phone, "
                  + "contact_email, location, consist_75, date, start, end, "
                  + "alternate_date, alternate_start, alternate_end, participants, "
                  + "areas, how_you_heard, special) "
                  + "VALUES ("
                  + "'%1$s', '%2$s', '%3$s', "
                  + "'%4$s', '%5$s', %6$d, '%7$s', '%8$s', '%9$s', "
                  + "'%10$s', '%11$s', '%12$s', %13$d, "
                  + "%14$d, '%15$s', '%16$s')",
                  request.getNameOfGroup(), request.getNameOfContact(), request.getContactPhone(),
                  request.getContactEmail(), request.getLocation(), consist75, date, start, end,
                  alternateDate, alternateStart, alternateEnd, request.getParticipants(),
                  request.getAreas(), request.getHowDidYouHear(), request.getSpecial());
    try {
      Statement statement = db.createStatement();
      statement.execute(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
