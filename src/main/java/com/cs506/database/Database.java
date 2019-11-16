

package com.cs506.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Locale;

import com.cs506.workshop.Area;
import com.cs506.workshop.WorkshopRequest;
import com.cs506.workshop.WorkshopType;

public class Database {

  Connection db = null;

  public static final SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("us"));


  public Database() {

    try {
        this.db = DriverManager.getConnection("jdbc:sqlite:database.db");
        Statement statement = db.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS account ("
                          + "username TEXT PRIMARY KEY, "
                          + "password TEXT NOT NULL, "
                          + "permission_lvl INTEGER DEFAULT 0)");
        statement.execute("CREATE TABLE IF NOT EXISTS workshop ("
                          + "workshop_type_name TEXT, "
                          + "group_name TEXT NOT NULL UNIQUE, "
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
                          + "username TEXT, "
                          + "group_name TEXT, "
                          + "PRIMARY KEY (username, group_name), "
                          + "FOREIGN KEY (username) "
                          + "REFERENCES account (username) "
                          + "ON DELETE CASCADE "
                          + "ON UPDATE CASCADE, "
                          + "FOREIGN KEY (group_name) "
                          + "REFERENCES workshop (group_name) "
                          + "ON DELETE CASCADE "
                          + "ON UPDATE CASCADE)");
        System.out.println("db connected");
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        try {
    		if(db != null) {
    			db.close();
    		}
    	} catch(SQLException ex) {
    		System.out.println(ex.getMessage());
    	}
    }
  }


  public void closeConn() {
	  try {
		  if(db != null) {
			  db.close();
			  System.out.println("connection closed");
		  }
	  } catch(SQLException e) {
		  System.out.println(e.getMessage());
	  }
  }


  public int addWorkshop(WorkshopRequest request) {

    String date = request.getDate().toString();
    String start = request.getStartTime().toString();
    String end = request.getEndTime().toString();
    String alternateDate = new String();
    String alternateStart = new String();
    String alternateEnd = new String();
    String workshopType = request.getWorkshopType().toString();
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
    if (request.isConsist75() == true) {
      consist75 = 1;
    }

    String sql = String.format("INSERT INTO workshop (workshop_type_name, "
                  + "group_name, contact_name, contact_phone, "
                  + "contact_email, location, consist_75, date, start, end, "
                  + "alternate_date, alternate_start, alternate_end, participants, "
                  + "areas, how_you_heard, special) "
                  + "VALUES ("
                  + "'%1$s', '%2$s', '%3$s', "
                  + "'%4$s', '%5$s', '%6$s', %7$d, '%8$s', '%9$s', "
                  + "'%10$s', '%11$s', '%12$s', '%13$s', "
                  + "%14$d, %15$d, '%16$s', '%17$s')",

                  workshopType, request.getNameOfGroup(), request.getNameOfContact(), request.getContactPhone(),
                  request.getContactEmail(), request.getLocation(), consist75, date, start, end,
                  alternateDate, alternateStart, alternateEnd, request.getParticipants(),
                  request.getAreasValue(), request.getHowDidYouHear(), request.getSpecial());

    try {
      Statement statement = db.createStatement();
      statement.execute(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return -1;
    }
      return 1;
  }


  	public WorkshopRequest getWorkshop(String name) {

  		String sql = ("SELECT * FROM workshop WHERE group_name = '" + name + "'");
  		ResultSet result = null;

  		try {
  			Statement statement = db.createStatement();
  			result = statement.executeQuery(sql);

  			while (result.next()) {
  				String c75;
  				if(result.getString("consist_75").equals("1")) {
  					c75 = "yes";
  				} else {
  					c75 = "no";
  				}
  				WorkshopRequest request = new WorkshopRequest();
  				request.setNameOfGroup(result.getString("group_name"));
  				request.setNameOfContact(result.getString("contact_name"));
  				request.setConsist75(c75.equals("yes"));
  				request.setContactPhone(result.getString("contact_phone"));
  				request.setContactEmail(result.getString("contact_email"));
  				request.setWorkshopType(WorkshopType.valueOf(result.getString("workshop_type_name")));
  				request.setLocation(result.getString("location"));
  				request.setDate(sdf.parse(result.getString("date")));
  				request.setStartTime(LocalTime.parse(result.getString("start")));
  				request.setEndTime(LocalTime.parse(result.getString("end")));
  				request.setAlternateDate(sdf.parse(result.getString("alternate_date")));
  				request.setAlternateStartTime(LocalTime.parse(result.getString("alternate_start")));
  				request.setAlternateEndTime(LocalTime.parse(result.getString("alternate_end")));
  				request.setParticipants(Integer.parseInt(result.getString("participants")));
  				request.setAreas(Area.getAreasFromValue(Integer.parseInt(result.getString("areas"))));
  				request.setHowDidYouHear(result.getString("how_you_heard"));
  				request.setSpecial(result.getString("special"));
  				return request;
  			}
  		} catch (SQLException | ParseException e) {
  			System.out.println(e.getMessage());
  		}
  		return null;
  	}

	public int delWorkshop(String name) {

		String sql = "DELETE FROM workshop WHERE group_name = '" + name + "'";

		try {
  			Statement statement = db.createStatement();
  			statement.executeQuery(sql);
  		} catch (SQLException e) {
  			System.out.println(e.getMessage());
  		return -1;

		}
      		return 1;
  	}

  	public int editWorkshop(String name, WorkshopRequest request) {

		String date = request.getDate().toString();
    		String start = request.getStartTime().toString();
    		String end = request.getEndTime().toString();
    		String alternateDate = new String();
    		String alternateStart = new String();
    		String alternateEnd = new String();
    		String workshopType = request.getWorkshopType().toString();
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
    		if (request.isConsist75() == true) {
      			consist75 = 1;
    		}

    		String sql = String.format("UPDATE workshop SET workshop_type_name = '%1$s', "
					   + "contact_name = '%2$s', contact_phone = '%3$s', contact_email = '%4$s', "
					   + "location = '%5$s', consist_75 = %6$d, date = '%7$s', start = '%8$s', end = '%9$s', "
					   + "alternate_date = '%10$s', alternate_start = '%11$s', alternate_end = '%12$s', "
					   + "participants = %13$d, areas = %14$d, how_you_heard = '%15$s', special = '%16$s' "
					   + "WHERE group_name = '%17$s'",

				workshopType, request.getNameOfContact(), request.getContactPhone(),
                  		request.getContactEmail(), request.getLocation(), consist75, date, start, end,
                  		alternateDate, alternateStart, alternateEnd, request.getParticipants(),
                  		request.getAreasValue(), request.getHowDidYouHear(), request.getSpecial(),
		  		request.getNameOfGroup());

  		try {
  			Statement statement = db.createStatement();
  			statement.executeUpdate(sql);
  		} catch (SQLException e) {
  			System.out.println(e.getMessage());
  		return -1;

		}
      		return 1;
  	}


  	public LinkedList<String[]> getAllWorkshops() {

	    LinkedList<String[]> list = new LinkedList<String[]>();
	    String sql = ("SELECT * FROM workshop order by date desc");
	    ResultSet result = null;

	    try {
	    	Statement statement = db.createStatement();
	    	System.out.println("executing");
	    	result = statement.executeQuery(sql);
	    	System.out.println("results");

	    	while (result.next()) {
	    		String c75;
	    		if(result.getString("consist_75").equals("1")) {
	    			c75 = "yes";
	    		} else {
	    			c75 = "no";
	    		}

	    		String areas = new String();
	    		int areaInt = Integer.parseInt(result.getString("areas"));
	    		if(areaInt >= 0x80) {
	    			areas = "Resiliency";
	    			areaInt -= 0x80;
	    		}
	    		if(areaInt >= 0x40) {
	    			String add = "Community Building";
	    			if(areas.equals("")) {
	    				areas = add;
	    			} else {
	    				areas = areas.concat("\n" + add);
	    			}
	    			areaInt -= 0x40;
	    		}
	    		if(areaInt >= 0x20) {
	    			String add = "Goal Setting";
	    			if(areas.equals("")) {
	    				areas = add;
	    			} else {
	    				areas = areas.concat("\n" + add);
	    			}
	    			areaInt -= 0x20;
	    		}
	    		if(areaInt >= 0x10) {
	    			String add = "Vision and Mission";
	    			if(areas.equals("")) {
	    				areas = add;
	    			} else {
	    				areas = areas.concat("\n" + add);
	    			}
	    			areaInt -= 0x10;
	    		}
	    		if(areaInt >= 0x08) {
	    			String add = "Problem Solving/Decision Making";
	    			if(areas.equals("")) {
	    				areas = add;
	    			} else {
	    				areas = areas.concat("\n" + add);
	    			}
	    			areaInt -= 0x8;
	    		}
	    		if(areaInt >= 0x04) {
	    			String add = "Conflict Resolution";
	    			if(areas.equals("")) {
	    				areas = add;
	    			} else {
	    				areas = areas.concat("\n" + add);
	    			}
	    			areaInt -= 0x4;
	    		}
	    		if(areaInt >= 0x02) {
	    			String add = "Trust";
	    			if(areas.equals("")) {
	    				areas = add;
	    			} else {
	    				areas = areas.concat("\n" + add);
	    			}
	    			areaInt -= 0x2;
	    		}
	    		if(areaInt >= 0x01) {
	    			String add = "Communication";
	    			if(areas.equals("")) {
	    				areas = add;
	    			} else {
	    				areas = areas.concat("\n" + add);
	    			}
	    			areaInt -= 0x1;
	    		}
	    		System.out.println("areaInt = " + areaInt);

	    		String[] array = {result.getString("group_name"), result.getString("contact_name"),
	    				c75, result.getString("contact_email"),
						result.getString("contact_phone"), result.getString("workshop_type_name"),
						result.getString("location"), result.getString("date"), result.getString("start"),
						result.getString("end"), result.getString("alternate_date"), result.getString("alternate_start"),
						result.getString("alternate_end"), result.getString("participants"), areas,
						result.getString("how_you_heard"), result.getString("special")};
	            list.add(array);
	    }
	    } catch (SQLException e) {
	    	System.out.println("error");
	    	System.out.println(e.getMessage());
	    }

	    return list;
	  }


  public int addUser(String username, String password, int permission) {

	String sql = ("INSERT INTO account VALUES ('" + username + "', '" + password + "', '" + permission + "')");

    try {
      Statement statement = db.createStatement();
      statement.execute(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    return -1;
    }
      return 1;
  }


 public LinkedList<String[]> getUser(String username, String password) {

   LinkedList<String[]> list = new LinkedList<String[]>();
   String sql = ("SELECT * FROM account WHERE username = '" + username + "' AND password = '" + password + "'");
   ResultSet result = null;

   try {
     Statement statement = db.createStatement();
     result = statement.executeQuery(sql);

     while (result.next()) {
       String[] array = {result.getString("username"), result.getString("password"),
           result.getString("permission_lvl")};
           list.add(array);
     }
   } catch (SQLException e) {
     System.out.println(e.getMessage());
   }

  return list;
 }
}
