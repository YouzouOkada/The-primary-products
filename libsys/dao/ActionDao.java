package com.youzou.libsys.dao;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by �~�i on 2018/1/23.
 */
public class ActionDao {
    private static final String DRIVER="org.sqlite.JDBC";
    private static final String URL="jdbc:sqlite:f:"+ File.separator+"Java"+File.separator+"myProject"+ File.separator+"myLibSys.db";

    /**
     * �������ݿ�����
     * @return Connection ���Ӷ���
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
     * �ͷ���Դ
     * @param resultSet ��ѯ�Ľ����
     * @param preparedStatement Ԥ�����sql���
     * @param connection ���Ӷ���
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
     * �޸����ݿ����ݣ�����������ɾ����
     * @param sql ���ݿ�������
     * @param objects ռλ������
     * @return ���ݿ��б仯true������false
     */
    public boolean update(String sql, List<Object> objects){
        int res=0;//Ӱ�������
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        connection=connect();
        try {
            preparedStatement=connection.prepareStatement(sql);
            if(objects!=null){
                //��ռλ�����滻
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
     * ���ݿ��ѯ
     * @param sql ���ݿ�������
     * @param objects sql����е�ռλ����˳�򼯺ϣ�sql��û��ռλ����Ϊ��
     * @param tClass ������ѯ������
     * @param <T> tClass��ʵ������
     * @return List<T> �����
     * @exception  IllegalAccessException ���÷��䴴��ʵ��ʱ�޷�����
     * @exception  InstantiationException ��Ҫ���������޷�ʵ����
     * @exception  NoSuchFieldException ������ָ���ֶ�
     * @exception  SecurityException ȡ������Ȩ�޼����ܲ�����ȫ����
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
                //��ռλ�����滻
                for(int i=0;i<objects.size();i++){
                    preparedStatement.setObject(i+1,objects.get(i));
                }
            }
            resultSet=preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();//��¼��Ԫ���ݶ���
            while (resultSet.next()){
                T t=tClass.newInstance();//��ѯ�����ʵ������
                for (int i=0;i<resultSetMetaData.getColumnCount();i++){
                    String columnName=resultSetMetaData.getColumnName(i+1);//����
                    Object object=resultSet.getObject(columnName);//������Ӧ��ֵ
                    Field field=tClass.getDeclaredField(columnName);
                    field.setAccessible(true);//ȡ������Ȩ�޼��
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
