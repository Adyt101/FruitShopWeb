package cn.cst.fruit.service;
import java.util.ArrayList;
import java.util.SplittableRandom;

import cn.cst.fruit.bean.FruitItem;
import cn.cst.fruit.dao.AdminDao;
public class  AdminService {
    private AdminDao adminDao = new AdminDao();

    public ArrayList<FruitItem> queryAllData() {
        ArrayList<FruitItem> data = adminDao.queryAllData();
        return data;
    }

    public ArrayList<FruitItem> queryDataForCon(String number, String name, String price, String unit) {
        ArrayList<FruitItem> data = adminDao.queryDataForCon(number, name, price, unit);
        return data;
    }

    public FruitItem findFruitByNumber(String number) {
        ArrayList<FruitItem> data = adminDao.queryDataForCon(number, null, null, null);
        if (data.size() == 0) {
            return null;
        } else {
            return data.get(0);
        }
    }

    public boolean addFruitItem(FruitItem fruitItem) {
        ArrayList<FruitItem> data = queryAllData();
        for (int i = 0; i < data.size(); i++) {
            FruitItem fruit= data.get(i);
            if (fruitItem.getNumber().equals(fruit.getNumber())) {
                return false;
            }
        }
        adminDao.addFruitItem(fruitItem);
        return true;
    }
    public void updateFruit(FruitItem fruitItem){adminDao.updateFruitItem(fruitItem);}
    public void deleteFruitById(String id ){
        adminDao.delFruitItem(id);
    }
    public ArrayList<FruitItem> queryAllDataOrderBy(String sort) {
        ArrayList<FruitItem> data = adminDao.queryAllDataOrderBy(sort);
        return data;
    }
}

