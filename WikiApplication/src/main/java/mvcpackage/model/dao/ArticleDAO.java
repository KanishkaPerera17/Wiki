package mvcpackage.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvcpackage.model.bean.Article;

public class ArticleDAO {
	
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
    
    private String NEW_ARTICLE = "INSERT INTO articles(title, category_id, content, is_public, created_at) VALUES (?, ?, ?, ?, ?);";
    private String UPDATE_ARTICLE = "UPDATE articles SET title = ?, category_id = ?, content = ?, is_public = ? WHERE id = ?;";
    private String HIDE_UNHIDE = "UPDATE articles SET is_public = NOT is_public WHERE id = ?;";
	
    private String SELECT_ALL_ARTICLES = "SELECT * FROM articles a LEFT JOIN categories c ON a.category_id = c.id ORDER BY created_at DESC;";
	private String SELECT_REC_ARTICLES = "SELECT * FROM articles ORDER BY created_at DESC limit 3;";
	private String SELECT_ARTICLE_BY_ID = "SELECT * FROM articles a LEFT JOIN categories c ON a.category_id = c.id WHERE a.id = ? ORDER BY created_at DESC;";
	private String SELECT_HIDDEN_ARTICLES = "SELECT * FROM articles a LEFT JOIN categories c ON a.category_id = c.id WHERE is_public = false ORDER BY created_at DESC;";

	private String DELETE_ARTICLE = "DELETE FROM articles WHERE id=?";
    //constructor
    public ArticleDAO() {}

    public List < Article > selectAllArticles() {
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Article > articles = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        
        	connection = getConnection(); 
        	
            // Step 2:Create a statement using connection object
        	preparedStatement = connection.prepareStatement(SELECT_ALL_ARTICLES);
   
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
        	while(rs.next()) {        		
        		int Aid = rs.getInt("id");
        		String Atitle = rs.getString("title");
        		String Acategory = rs.getString("name");
        		String Acontent = rs.getString("content");
        		Boolean Apublic = rs.getBoolean("is_public");
        		String Acreated_at = rs.getString("created_at");
        		
        		System.out.print(Atitle);
        		
        		articles.add(new Article(Aid, Atitle, Acategory, Acontent, Apublic, Acreated_at));
        	}
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return articles;
    }
    
    public List < Article > selectRecArticles() {
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Article > articles = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        
        	connection = getConnection(); 
        	
            // Step 2:Create a statement using connection object
        	preparedStatement = connection.prepareStatement(SELECT_REC_ARTICLES);
   
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
        	while(rs.next()) {        		
        		int Aid = rs.getInt("id");
        		String Atitle = rs.getString("title");
        		String Acategory = rs.getString("category_id");
        		String Acontent = rs.getString("content");
        		String Acreated_at = rs.getString("created_at");
        		
        		System.out.print(Atitle);
        		
        		articles.add(new Article(Aid, Atitle, Acategory, Acontent, Acreated_at));
        	}
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return articles;
    }

    public List < Article > selectArticleByTitle(String keyword) {
    	
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        List < Article > articles = new ArrayList < > ();

        try { 
        
        	connection = getConnection(); 
        	
        	preparedStatement = connection.prepareStatement(
        	"SELECT a.id, a.title, a.content, a.category_id, c.name, a.created_at FROM articles a LEFT JOIN categories c ON a.category_id = c.id WHERE a.title LIKE '%"+keyword+"%' ORDER BY created_at DESC;");
 
            rs = preparedStatement.executeQuery();

        	while(rs.next()) {        		
        		int Aid = rs.getInt("id");
        		String Atitle = rs.getString("title");
        		String Acategory = rs.getString("name");
        		String Acontent = rs.getString("content");
        		String Acreated_at = rs.getString("created_at");
        		
        		articles.add(new Article(Aid, Atitle, Acategory, Acontent, Acreated_at));
        	}
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return articles;
    }

    public Article selectArticleByID(int id) {
    	
    	Article arc = null;
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;

        try { 
        
        	connection = getConnection(); 
        	
        	preparedStatement = connection.prepareStatement(SELECT_ARTICLE_BY_ID);
        	preparedStatement.setInt(1, id);
 
            rs = preparedStatement.executeQuery();

        	while(rs.next()) {        		
        		int Aid = rs.getInt("id");
        		String Atitle = rs.getString("title");
        		String Acategory = rs.getString("name");
        		String Acontent = rs.getString("content");
        		Boolean Apublic = rs.getBoolean("is_public");
        		String Acreated_at = rs.getString("created_at");
        		
        		arc = new Article(Aid, Atitle, Acategory, Acontent, Apublic, Acreated_at);
        	}
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return arc;
    }
    
    public List < Article > AllHiddenArticles() {
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Article > articles = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        
        	connection = getConnection(); 
        	
            // Step 2:Create a statement using connection object
        	preparedStatement = connection.prepareStatement(SELECT_HIDDEN_ARTICLES);
   
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
        	while(rs.next()) {        		
        		int Aid = rs.getInt("id");
        		String Atitle = rs.getString("title");
        		String Acategory = rs.getString("name");
        		String Acontent = rs.getString("content");
        		String Acreated_at = rs.getString("created_at");
        		
        		System.out.print(Atitle);
        		
        		articles.add(new Article(Aid, Atitle, Acategory, Acontent, Acreated_at));
        	}
            
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return articles;
    }
    
    // Insert New Article
    public void newArticle(Article arc) throws SQLException {
        Connection connection = null; 
    	PreparedStatement preparedStatement = null;

        try {
        	connection = getConnection(); 
        	preparedStatement = connection.prepareStatement(NEW_ARTICLE);
            preparedStatement.setString(1, arc.getAtitle());
            preparedStatement.setString(2, arc.getAcategory());
            preparedStatement.setString(3, arc.getAcontent());
            preparedStatement.setBoolean(4, arc.getApublic());
            preparedStatement.setString(5, java.time.LocalDate.now().toString());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            	printSQLException(e);
        } finally {
        	finallySQLException(connection,preparedStatement,null);
        }
    }
    
    // Update Existing Article
    public boolean updateArticle (Article arc) throws SQLException {
        
    	boolean empUpdated = false;
        Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	
      	try {
        	connection = getConnection(); 
        	preparedStatement = connection.prepareStatement(UPDATE_ARTICLE);
            preparedStatement.setString(1, arc.getAtitle());
            preparedStatement.setString(2, arc.getAcategory());
            preparedStatement.setString(3, arc.getAcontent());
            preparedStatement.setBoolean(4, arc.getApublic());
            preparedStatement.setInt(5, arc.getAid());

        	empUpdated = preparedStatement.executeUpdate() > 0 ? true:false;
        }
        catch (SQLException e) {
        	printSQLException (e);
        }     
      	finally {
        	finallySQLException(connection,preparedStatement,null);
        }
        return empUpdated;
    }
    
    public boolean ShowHideArticle (int id) throws SQLException {
    	
        boolean arcUpdated = false;
        Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	
      	try {
        	connection = getConnection(); 
        	preparedStatement = connection.prepareStatement(HIDE_UNHIDE);
        	preparedStatement.setInt(1, id);

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
    
    // Delete Article By ID
    public boolean deleteArticle(int id) throws SQLException {
        
    	boolean arcDeleted = false;
        Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	
      	try {
        	 connection = getConnection(); 
        	 preparedStatement = connection.prepareStatement(DELETE_ARTICLE);
        	 preparedStatement.setInt(1, id);
             arcDeleted = preparedStatement.executeUpdate() > 0 ? true:false;
        }
        finally {
        	finallySQLException(connection,preparedStatement,null);
        }
        return arcDeleted;
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
