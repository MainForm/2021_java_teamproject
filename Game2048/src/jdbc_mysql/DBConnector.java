package jdbc_mysql;

import java.sql.*;

public class DBConnector {
    private Connection conn; //DB Ŀ�ؼ� ���� ��ü
    private static final String USERNAME = "root";//DBMS���� �� ���̵�
    private static final String PASSWORD = "[Password_1234]";//DBMS���� �� ��й�ȣ
    private static final String URL = "jdbc:mysql://54.180.82.189:55876/Game2048";//DBMS������ db��
    
    public DBConnector() throws Exception, SQLException {
        try {
            System.out.println("������");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("����̹� �ε� ����");
        } catch (Exception e) {
            System.out.println("����̹� �ε� ���� ");
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
