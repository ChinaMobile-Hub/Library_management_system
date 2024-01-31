package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import utils.DBConnect;

/**
 * @ClassName DBUtil
 * @Description 数据库工具类
 * @Author FARO_Z
 * @Date 2020/11/13 3:42 下午
 * @Version 1.0
 **/
public class DBUtil {
    private static final String url="jdbc:mysql://localhost:3306/librarymasterone?userSSL=false&serverTimezone=UTC";
    // private static final String url="jdbc:mysql:///book_management_db";
    private static final String name="user";
    private static final String password="123456";

    /**
     * 静态代码块
     * 注入JDBC连接
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得JDBC连接
     * @return 连接Connection conn
     */
    public static Connection getConnection() {
        Connection conn=null;
        try {
            conn = DriverManager.getConnection(url, name, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }



//public static void main(String[] args) {
//	Connection conn = DBUtil.getConnection();
//	
//	if(conn != null){
//        System.out.println("登录成功");
//    }else{
//        System.out.println("登录失败");
//    }
//}

}
