package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public BlogVo getbloginfo(String id) {
		return sqlSession.selectOne("blog.getbloginfo", id);
	}

	public List<PostVo> getpostinfo(String id) {
		System.out.println("2");
	
		
		return sqlSession.selectList("blog.getpostinfo",id);
	}
	
}
