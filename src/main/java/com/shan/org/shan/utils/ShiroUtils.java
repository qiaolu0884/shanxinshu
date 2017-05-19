package com.shan.org.shan.utils;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.shan.org.shan.pojo.sys.Sysprivilege;

public class ShiroUtils {

	public static List<Sysprivilege> getPerms(){
		List<Sysprivilege> list = new ArrayList<>();
		 //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/shanxinshu_console";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "root";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from sxs_sysprivilege";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
            	Sysprivilege sysprivilege = new Sysprivilege();
            	sysprivilege.setId(Long.valueOf(rs.getString("id")));
            	sysprivilege.setName(rs.getString("name"));
            	sysprivilege.setPerms(rs.getString("perms"));
            	sysprivilege.setPid(Long.valueOf(rs.getString("pid")));
            	sysprivilege.setUrl(rs.getString("url"));
            	list.add(sysprivilege);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(Exception e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }finally{
            System.out.println("数据库数据成功获取！！");
        }
		return list;
	}
}
