package mvcpackage.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvcpackage.model.bean.Article;
import mvcpackage.model.bean.Category;

public class CategoryDAO {

	//private ConnectionDAO Connection;
	
	   private String DBURL = "jdbc:mysql://localhost:3306/db_wiki?serverTimezone=Australia/Melbourne";
	    private String DBUsername = "root";
	    private String DBPassword = "bit235mysql";
	    
	    // Build the connection with MYSQL
	    protected Connection getConnection() {
	        Connection connection = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return connection;
	    }
	
	private String NEW_CATEGORY = "INSERT INTO categories(name, description) VALUES (?, ?);";
	private String SELECT_ALL_CATEGORIES = "SELECT * FROM categories;";
	private String DELETE_CATEGORY = "DELETE FROM categories WHERE id = ?;";
	private String UPDATE_ARTICLE = "UPDATE articles SET category_id = (SELECT id FROM categories WHERE name = 'Unknown') WHERE category_id = ?";

    public List <Category> selectAllCategries() {
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List <Category> category = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        
        	connection = getConnection();
        	
            // Step 2:Create a statement using connection object
        	preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES);
   
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
        	while(rs.next()) {   
        		System.out.print(rs.getInt("id"));
        		int Cid = rs.getInt("id");
        		String Cname = rs.getString("name");
        		String Cdescription = rs.getString("description");

        		
        		System.out.print(Cname);
        		
        		category.add(new Category(Cid, Cname, Cdescription));
        	}
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return category;
    }
    
    public void newCategory(Category category) throws SQLException {
        Connection connection = null; 
    	PreparedStatement preparedStatement = null;

        try {
        	connection = getConnection(); 
        	preparedStatement = connection.prepareStatement(NEW_CATEGORY);
            preparedStatement.setString(1, category.getCname());
            preparedStatement.setString(2, category.getCdescription());
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            	printSQLException(e);
        } finally {
        	finallySQLException(connection,preparedStatement,null);
        }
    }
    
    public boolean deleteCategory(int id) throws SQLException {
        
    	boolean catDeleted = false;
        Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	
      	try {
        	 connection = getConnection(); 
        	 
        	 Boolean result = updateArticleCategory(id);
        	 
        	 if(result) {
            	 preparedStatement = connection.prepareStatement(DELETE_CATEGORY);
            	 preparedStatement.setInt(1, id);
            	 catDeleted = preparedStatement.executeUpdate() > 0 ? true:false;
        	 }   
        }
        finally {
        	finallySQLException(connection,preparedStatement,null);
        }
        return catDeleted;
    }
    
    // Update Existing Article
    public boolean updateArticleCategory(int delete_id) throws SQLException {
        
    	boolean arcUpdated = false;
        Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	
      	try {
        	connection = getConnection(); 
        	preparedStatement = connection.prepareStatement(UPDATE_ARTICLE);
            preparedStatement.setInt(1, delete_id);


        	arcUpdated = preparedStatement.executeUpdate() > 0 ? true:false;
        }
        catch (SQLException e) {
        	printSQLException (e);
        }     
      	finally {
        	finallySQLException(connection,preparedStatement,null);
        }
        return arcUpdated;
    }
    
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
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
    
    private void finallySQLException(Connection c, PreparedStatement p, ResultSet r){
    	 if (r != null)	{
             try {
                r.close();
             } catch (Exception e) {}
                r = null;
             }
 	
          if (p != null) {
             try {
                p.close();
             } catch (Exception e) {}
                p = null;
             }
 	
          if (c != null) {
             try {
                c.close();
             } catch (Exception e) {
           	  c = null;
             }

          }
    }
}
