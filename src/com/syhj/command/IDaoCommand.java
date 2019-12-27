package com.syhj.command;

import java.util.List;

public interface IDaoCommand<T> {
    //查询所有的数据
    List<T> queryAllData();

    //新增数据
    int insertData(T t);

    //修改数据
    int update(T t);

    //删除数据
    int delete(int id);

    //查询一条数据通过ID
    T queryDataById(int id);
}
