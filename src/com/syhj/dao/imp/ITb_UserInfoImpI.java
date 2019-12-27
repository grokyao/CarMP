package com.syhj.dao.imp;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.syhj.dao.ITb_UserInfo;
import com.syhj.domain.Tb_UserInfo;
import com.syhj.db.MySqlHelper;


public class ITb_UserInfoImpI implements ITb_UserInfo {
    public Connection conn1 = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;

    @Override
    public List<Tb_UserInfo> queryAllData() {
        conn1 = MySqlHelper.getConnection();// 链接数据库
        List<Tb_UserInfo> list = new ArrayList<Tb_UserInfo>();
        try {
            String sqlSelect = "select * from users "; // 查询多条数据
            ps = conn1.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            Tb_UserInfo user = null;
            while (rs.next()) {
                user = new Tb_UserInfo();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setBirthday(rs.getDate("birthday"));
                user.setSex(rs.getBoolean("sex"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlHelper.Close(rs, ps, conn1);
        }
        return list;
    }

    @Override
    public int insertData(Tb_UserInfo t) {
        conn1 = MySqlHelper.getConnection();
        int i = 0;
        try {
            String sqlInsert = "insert into users(name,password,email,birthday,sex) values(?,?,?,?,?) ;";
            ps = conn1.prepareStatement(sqlInsert,
                    PreparedStatement.RETURN_GENERATED_KEYS);
            // 这里的1,2..必须要按上面的新增的顺序来定义
            ps.setString(1, t.getName());
            ps.setString(2, t.getPassword());
            ps.setString(3, t.getEmail());
            ps.setDate(4, new java.sql.Date(t.getBirthday().getTime()));
            ps.setBoolean(5, t.isSex());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();// 得到 最新的 ID
            if (rs.next()) {// 是否存在
                i = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlHelper.Close(rs, ps, conn1);
        }
        return i;
    }

    @Override
    public int update(Tb_UserInfo t) {
        conn1 = MySqlHelper.getConnection();
        int i = 0;
        try {
            String sqlUpdate = "update users set name=?, password =? ,sex=?  where id=?";
            ps = conn1.prepareStatement(sqlUpdate);
            ps.setString(1, t.getName());
            ps.setString(2, t.getPassword());
            ps.setBoolean(3, t.isSex());
            ps.setInt(4, t.getId());
            i = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlHelper.Close(null, ps, conn1);
        }
        return i;
    }

    @Override
    public int delete(int id) {
        conn1 = MySqlHelper.getConnection();
        int i = 0;
        try {
            String sqlDelete = "delete from users where id=?";
            ps = conn1.prepareStatement(sqlDelete);
            ps.setInt(1, id);
            i = ps.executeUpdate();
            if (i == 1) {
                return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySqlHelper.Close(null, ps, conn1);
        }
        return i;
    }

    @Override
    public Tb_UserInfo queryDataById(int id) {
        conn1 = MySqlHelper.getConnection();
        String sql = "select * from users where id=?";
        Tb_UserInfo user = null;
        if (id > 0) {
            try {
                ps = conn1.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    user = new Tb_UserInfo();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setPassword(rs.getString("password"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setSex(rs.getBoolean("sex"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                MySqlHelper.Close(null, ps, conn1);
            }
        }
        return user;
    }
}
