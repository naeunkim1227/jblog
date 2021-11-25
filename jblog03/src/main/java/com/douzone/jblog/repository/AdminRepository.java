package com.douzone.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Repository
public class AdminRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> getpostinfo(String id) {
		return sqlSession.selectList("post.getpostinfo",id);
	}

	public List<CategoryVo> getcateinfo(String id) {
		return sqlSession.selectList("category.getcateinfo", id);
	}

	public void write(PostVo vo, String id) {
		System.out.println(id);
			sqlSession.insert("post.write", vo);
		
	}

	public void settingBasic(BlogVo vo) {
		sqlSession.update("blog.settingBasic",vo);
	}

	public void addCategory(CategoryVo vo) {
		sqlSession.insert("category.insert",vo);
	}

	public void deleteCategory(int no) {
		
	sqlSession.delete("category.delete", no);
	}
	
}
