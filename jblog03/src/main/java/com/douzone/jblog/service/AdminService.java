package com.douzone.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.repository.AdminRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminrepository;
	
	public List<PostVo> getpostinfo(String id) {
		return adminrepository.getpostinfo(id);
	}

	public List<CategoryVo> getcateinfo(String id) {
		return adminrepository.getcateinfo(id);
	}

	public void write(PostVo vo,String id) {
		adminrepository.write(vo, id);
	}

	public void setingbasic(String id, String title, MultipartFile multipartFile) {
		String SAVA_PATH = "/upload-images";
		String URL_BASE = "/gallery/images";
		
		File uploaddir = new File(SAVA_PATH);
		
		try {

			if(!uploaddir.exists()) {
				uploaddir.mkdir();
			}
			
			String originName = multipartFile.getOriginalFilename();
			System.out.println(originName);
			String extName = originName.substring(originName.lastIndexOf(".")+1);
			String saveName = generateSaveName(extName);
			byte[] data = multipartFile.getBytes();
			
			OutputStream os = new FileOutputStream(SAVA_PATH + "/" + saveName);
			os.write(data);
			os.close();
			
			BlogVo vo = new BlogVo();
			vo.setId(id);
			vo.setLogo(URL_BASE + "/" + saveName);
			vo.setTitle(title);
			
			adminrepository.settingBasic(vo);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}

	private String generateSaveName(String extName) {
		String name = "";
		
		Calendar calendar = Calendar.getInstance();
		
		name += calendar.get(Calendar.YEAR);		
		name += calendar.get(Calendar.MONTH);		
		name += calendar.get(Calendar.DATE);		
		name += calendar.get(Calendar.HOUR);		
		name += calendar.get(Calendar.MINUTE);		
		name += calendar.get(Calendar.MILLISECOND);
		name += "." + extName;
		
		
		return name;
	}

}
