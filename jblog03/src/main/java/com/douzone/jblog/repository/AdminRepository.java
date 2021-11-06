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

		if(vo.getCategory_no() == -1) {
			System.out.println("basicinsert구현중 카테고리 없을경우");
			sqlSession.insert("category.basicinsert", id);
			System.out.println("ok");
			sqlSession.insert("post.write", vo);
		}else {
			System.out.println("카테고리 있을 경우");
			sqlSession.insert("post.write", vo);
		}
		
	}

	public void settingBasic(BlogVo vo) {
		
	}
	
}
