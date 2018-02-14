package com.youzou.libsys.dao;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by 葉蔵 on 2018/1/23.
 */
public class ActionDao {
    private static final String DRIVER="org.sqlite.JDBC";
    private static final String URL="jdbc:sqlite:f:"+ File.separator+"Java"+File.separator+"myProject"+ File.separator+"myLibSys.db";

    /**
     * 建立数据库连接
     * @return Connection 连接对象
     */
    public Connection connect(){
        Connection connection=null;
        try {
            Class.forName(DRIVER);
            connection=DriverManager.getConnection(URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放资源
     * @param resultSet 查询的结果集
     * @param preparedStatement 预编译的sql语句
     * @param connection 连接对象
     */
    public void release(ResultSet resultSet, PreparedStatement preparedStatement,Connection connection){
        try {
            if(resultSet!=null){
                resultSet.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改数据库内容（包括增加与删除）
     * @param sql 数据库操作语句
     * @param objects 占位符集合
     * @return 数据库有变化true，否则false
     */
    public boolean update(String sql, List<Object> objects){
        int res=0;//影响的行数
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        connection=connect();
        try {
            preparedStatement=connection.prepareStatement(sql);
            if(objects!=null){
                //有占位符先替换
                for(int i=0;i<objects.size();i++){
                    preparedStatement.setObject(i+1,objects.get(i));
                }
            }
            res=preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(resultSet,preparedStatement,connection);
        }
        return res>=0?true:false;
    }

    /**
     * 数据库查询
     * @param sql 数据库操作语句
     * @param objects sql语句中的占位符的顺序集合，sql中没有占位符则为空
     * @param tClass 将被查询的类型
     * @param <T> tClass的实例类型
     * @return List<T> 结果集
     * @exception  IllegalAccessException 利用反射创建实例时无法调用
     * @exception  InstantiationException 需要创建的类无法实例化
     * @exception  NoSuchFieldException 不包含指定字段
     * @exception  SecurityException 取消访问权限检查可能产生安全问题
     */
    public <T>List<T> query(String sql,List<Object> objects,Class<T> tClass) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        connection=connect();
        List<T> list=new ArrayList<T>();
        try {
            preparedStatement=connection.prepareStatement(sql);
            if(objects!=null){
                //有占位符先替换
                for(int i=0;i<objects.size();i++){
                    preparedStatement.setObject(i+1,objects.get(i));
                }
            }
            resultSet=preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();//记录集元数据对象
            while (resultSet.next()){
                T t=tClass.newInstance();//查询的类的实例对象
                for (int i=0;i<resultSetMetaData.getColumnCount();i++){
                    String columnName=resultSetMetaData.getColumnName(i+1);//列名
                    Object object=resultSet.getObject(columnName);//列名对应的值
                    Field field=tClass.getDeclaredField(columnName);
                    field.setAccessible(true);//取消访问权限检查
                    field.set(t,object);
                }
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            release(resultSet,preparedStatement,connection);
        }
        return list;
    }
}
