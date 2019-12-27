package com.syhj.command;

import java.util.List;

public interface IServiceCommand<T> {

    //查询所有的数据
    List<T> queryAllData();

    //新增数据
    boolean insertData(T t);

    //修改数据
    boolean update(T t);

    //删除数据
    boolean delete(int id);

    //查询一条数据通过ID
    T queryDataById(int id);
}
