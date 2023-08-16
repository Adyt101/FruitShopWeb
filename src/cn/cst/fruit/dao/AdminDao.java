package cn.cst.fruit.dao;

import cn.cst.fruit.bean.FruitItem;
import cn.cst.fruit.tools.JDBCUtils;
import jdk.nashorn.internal.scripts.JD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class AdminDao {
    public ArrayList<FruitItem> queryAllData() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<FruitItem> fruitlist = new ArrayList<FruitItem>();
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM fruit";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String number = rs.getString("number");
                String name = rs.getString("fruitname");
                String price = rs.getString("price");
                String unit = rs.getString("unit");
                FruitItem fruitItem = new FruitItem(number,name,price,unit);
                fruitlist.add(fruitItem);
            }
            return fruitlist;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return null;
    }

    public void addFruitItem(FruitItem fruitItem) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            //发送SQL语句
            String sql = "INSERT INTO fruit(number,fruitname,price,unit)"
                    + "VALUES('" + fruitItem.getNumber() + "','"
                    + fruitItem.getName() + "','" + fruitItem.getPrice()
                    + "','" + fruitItem.getUnit() + "')";
            int num = stmt.executeUpdate(sql);
            if (num > 0) {
                System.out.println("添加数据成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
    }

    public ArrayList<FruitItem> queryDataForCon(String number,String name,String price ,String unit ){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<FruitItem> dataList = new ArrayList<>();
        try{
            //获得数据的连接
            conn = JDBCUtils.getConnection();
            //获得Statement对象
            stmt = conn.createStatement();
            //发送SQL语句
            String sql = "SELECT * FROM fruit WHERE 1=1";
            if (number!=null&&number.trim().length()!=0){
                sql = sql+" and number='"+number.trim()+"'";
            }
            if (name!=null&&name.trim().length()!=0){
                sql = sql+" and fruitname like'%"+name.trim()+"%'";
            }
            if (price!=null&&price.trim().length()!=0){
                sql = sql+" and price='"+price.trim()+"'";
            }if (unit!=null&&unit.trim().length()!=0){
                sql = sql+" and unit='"+unit.trim()+"'";
            }
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                FruitItem fruitItem = new FruitItem();
                fruitItem.setNumber(resultSet.getString("number"));
                fruitItem.setName(resultSet.getString("fruitname"));
                fruitItem.setPrice(resultSet.getString("price"));
                fruitItem.setUnit(resultSet.getString("unit"));
                dataList.add(fruitItem);
            } return dataList;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
        return null;
    }

    public void updateFruitItem(FruitItem fruitItem){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "update fruit set  fruitname =  '" + fruitItem.getName() + "',price = '"
                    +fruitItem.getPrice() + "',unit = '" + fruitItem.getUnit() + "' where number = '"
                    + fruitItem.getNumber() + "'";
            int num = stmt.executeUpdate(sql);
            if(num>0){
                System.out.println("修改数据成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
    }
    public void delFruitItem(String delNumber){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "DELETE FROM fruit WHERE number = "+ delNumber;
            int num = stmt.executeUpdate(sql);
            if(num>0){
                System.out.println("删除数据成功");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JDBCUtils.release(rs,stmt,conn);
        }
    }
    public ArrayList<FruitItem> queryAllDataOrderBy(String sort) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<FruitItem> fruitlist = new ArrayList<FruitItem>();
        try {
            conn = JDBCUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM fruit order by " + sort;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String number = rs.getString("number");
                String name = rs.getString("fruitname");
                String price = rs.getString("price");
                String unit = rs.getString("unit");
                FruitItem fruitItem = new FruitItem(number,name,price,unit);
                fruitlist.add(fruitItem);
            }
            return fruitlist;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return null;
    }
}


