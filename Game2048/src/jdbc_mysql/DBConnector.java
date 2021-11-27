package jdbc_mysql;

import java.sql.*;

public class DBConnector {
    private Connection conn; //DB 커넥션 연결 객체
    private static final String USERNAME = "root";//DBMS접속 시 아이디
    private static final String PASSWORD = "[Password_1234]";//DBMS접속 시 비밀번호
    private static final String URL = "jdbc:mysql://54.180.82.189:55876/Game2048";//DBMS접속할 db명
    
    public DBConnector() throws Exception, SQLException {
        try {
            System.out.println("생성자");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("드라이버 로딩 성공");
        } catch (Exception e) {
            System.out.println("드라이버 로딩 실패 ");
            try {
                conn.close();
            } 
            catch (SQLException e1) { 
            	throw e1;
            }
            
            throw e;
        }
    }
    
    public boolean SignUp(String id,String password) throws SQLException {
    	String sql = "insert into User_tb values(?,?)";
    	PreparedStatement ppst;
    	
    	ppst = conn.prepareStatement(sql);
    		
    	ppst.setString(1, id);
    	ppst.setString(2, password);
    	
    	try {
	    	if(ppst.executeUpdate() == 1) {
	    		return true;
	    	}
    	}
    	catch(SQLException err) {
    		return false;
    	}

    	return false;
    }
    
    public boolean SignIn(String id,String password) throws SQLException{
    	String sql = "select * FROM User_tb WHERE id=? AND Password=?";
    	PreparedStatement ppst;
    	
    	ppst = conn.prepareStatement(sql);
    	ppst.setString(1, id);
    	ppst.setString(2, password);
    	
    	ResultSet rs = ppst.executeQuery();
    	
    	return rs.next();
    }
}
