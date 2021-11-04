package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class AdminRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getpostinfo(String id) {
		return sqlSession.selectList("blog.getpostinfo",id);
	}

	public List<CategoryVo> getcateinfo(String id) {
		return sqlSession.selectList("blog.getcateinfo", id);
	}

	public void write(PostVo vo) {
		sqlSession.insert("blog.write", vo);
		
	}
	
}
