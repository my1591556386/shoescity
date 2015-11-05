package com.senthqh.global.base;

import com.github.abel533.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2015/1/20.
 * 业务层基类
 */
public abstract class BaseService<T> {
    @Autowired
    protected Mapper<T> mapper;

    public List<T> selectAll(){
        return mapper.select(null);
    }

    public List<T> selectByWhere(T t){
        return mapper.select(t);
    }

    public T selectById(String id){
        return mapper.selectByPrimaryKey(id);
    }

    /**根据条件查询返回单条数据*/
    public T selectSingleByWhere(T t){
        List<T> list = mapper.select(t);
        if(list != null && list.size() != 0) return list.get(0);
        return null;
    }

    /**条件分页查询*/
//    public Page<T> selectByPage(int pageNum, int pageSize, T t){
//        PageHelper.startPage(pageNum, pageSize, true);
//        Page<T> list = (Page)mapper.select(t);
//        return list;
//    }

    public int save(T t){
        return mapper.insert(t);
    }

    public int delete(T t){
        return mapper.delete(t);
    }

    public int update(T t){
        return mapper.updateByPrimaryKeySelective(t);
    }

}
