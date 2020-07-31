import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.IOException;


public class ResultSset {

    static Connection connection = null;
    static Statement statement = null;
    static java.sql.ResultSet resultset = null;
    static String[][] login;
	static String[][] students;
	static int row=0;

    ResultSset() {
        getConnection("Login");
		//getConnectionStudent("Students");
	
    }

    public static int getDataLength(String tableName) {
        int rows = 0;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String accessDatabase = "database.accdb";
            String dbUrl = "jdbc:ucanaccess://" + accessDatabase;

            connection = DriverManager.getConnection(dbUrl);
			//java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY
            statement = connection.createStatement();
            resultset = statement.executeQuery("SELECT * FROM "+tableName);
			
		
            while (resultset.next()) {
                rows = resultset.getRow();
                //System.out.println(resultset.getString(1) + " " + resultset.getString(2));
            }
			

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (null != connection) {
                    resultset.close();
                    statement.close();
                    connection.close();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        return rows;

    }

    public static void getConnection(String tableName) {
        int rows = getDataLength(tableName);
        login = new String[rows][2];

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String accessDatabase = "database.accdb";
            String dbUrl = "jdbc:ucanaccess://" + accessDatabase;

            connection = DriverManager.getConnection(dbUrl);
            statement = connection.createStatement(java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery("SELECT * FROM "+tableName);
			
			
			
			resultset.absolute(0);
            while (resultset.next()) {
                
				try{
				if(row<=rows){
				
				String userName=resultset.getString(2);
				String password=resultset.getString(1);
				
			
                login[row][0] = userName;
                login[row][1] = password;
				
                row++;
				}
				}catch(ArrayIndexOutOfBoundsException ex){
				
                }
            }
			
			
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            try {
                if (null != connection) {
                    resultset.close();
                    statement.close();
                    connection.close();
                }
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }

    }

	public static void main(String args[]){
		ResultSset obj=new ResultSset();
		
		for(int i=0;i<3;i++){
			for(int j=0;j<2;j++){
				System.out.println(obj.login[i][j]);
			}
		}
		
		
	
		
	}

}
