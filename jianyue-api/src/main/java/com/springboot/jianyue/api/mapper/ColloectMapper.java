package com.springboot.jianyue.api.mapper;

import com.springboot.jianyue.api.entity.Collect;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ColloectMapper {
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "u_id", property = "uId"),
            @Result(column = "a_id", property = "aId"),
    })
    @Select("SELECT * FROM t_collect WHERE u_id = #{uId}")
    List<Collect> selectCollectsByAId(int uId);

    @Insert("INSERT INTO t_collect(u_id,a_id) VALUES (#{uId},#{aId})")
    void insertCollect(Collect collect);
}
