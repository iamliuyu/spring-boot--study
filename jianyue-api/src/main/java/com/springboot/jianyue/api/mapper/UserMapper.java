package com.springboot.jianyue.api.mapper;

import com.springboot.jianyue.api.entity.User;
import org.apache.ibatis.annotations.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "password", column = "password"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "status", column = "status"),
            @Result(property = "regtime", column = "regtime"),
            @Result(property = "token", column = "token")
    })
    @Select("SELECT * FROM t_user WHERE mobile = #{mobile}")
    User getUserByMobile(String mobile);

    @Select("SELECT * FROM t_user WHERE id = #{id}")
    User getUserById(int id);

    @Select("SELECT * FROM t_user ")
    List<User> getAllUser();

    @Update("UPDATE t_user SET password = #{password}, nickname = #{nickname}," +
            "avatar = #{avatar},status = #{status},token = #{token} WHERE id = #{id}")
    void update(User user);

    @Insert("INSERT INTO t_user(mobile,password,nickname,avatar,regtime,status) " +
            " VALUES(#{mobile}, #{password}, #{nickname},#{avatar},#{regtime},#{status}) ")
    void insert(User user);

    @InsertProvider(type = Provider.class, method = "batchInsert")
    int batchInsert(List<User> user);

    class Provider {
        /* 批量插入 */
        public String batchInsert(Map map) {
            List<User> users = (List<User>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO t_user (mobile,password,nickname,avatar,regtime,status) VALUES ");
            MessageFormat mf = new MessageFormat(
                    "(#'{'list[{0}].mobile}, #'{'list[{0}].password}, #'{'list[{0}].nickname}, #'{'list[{0}].avatar}, #'{'list[{0}].regtime}, #'{'list[{0}].status} )"
            );
            for (int i = 0; i < users.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < users.size() - 1)
                    sb.append(",");
            }
            return sb.toString();
        }
    }
}
