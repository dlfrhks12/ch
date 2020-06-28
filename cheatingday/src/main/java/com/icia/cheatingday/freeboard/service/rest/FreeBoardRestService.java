/*package com.icia.cheatingday.freeboard.service.rest;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icia.cheatingday.freeboard.dao.AttachmentDao;
import com.icia.cheatingday.freeboard.dao.FreeBoardDao;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
import com.icia.cheatingday.freeboard.entity.Attachment;
import com.icia.cheatingday.freeboard.entity.FreeBoard;

@Service
public class FreeBoardRestService {
	//게시판 업데이트, ckimage저장,첨부파일 읽기
	//attachment delete
	//board delete
	//aa
	@Autowired
	private AttachmentDao attachmentDao;
	@Autowired
	private FreeBoardDao boardDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ObjectMapper objectMapper;
	@Value("http://localhost8081/ckimage/")
	private String ckUrl;
	@Value("${imageFolder}")
	private String imageFolder;
	@Value("${imagePath}")
	private String imagePath;
	
	Pattern ckImagePattern = Pattern.compile("src=\".+\"\\s");
	
	
	
	public void updateBoard(FreeBoardDto.DtoForUpdate dto) {
		FreeBoard board = boardDao.findById(dto.getBno());
		board = modelMapper.map(dto, FreeBoard.class);
		boardDao.update(board);
	}
	
	  public String saveCkImage(MultipartFile upload) {
	         Map<String, String> map = new HashMap<String, String>();
	         if (upload!= null) {
	            if (upload.getContentType().toLowerCase().startsWith("image/")) {
	               String imageName = UUID.randomUUID().toString() + ".jpg";
	               try {
	                  File file = new File("d:/upload/ckimage", imageName);
	                  // FileCopyUtils.copy(upload.getBytes(), file);
	                  upload.transferTo(file);
	                  map.put("uploaded", "1");
	                  map.put("fileName", imageName);
	                  map.put("url", ckUrl + imageName);
	                  return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
	               } catch (Exception e) {
	                  e.printStackTrace();
	               }
	            }
	         }
	         return null;
	      }
	  
	  public Attachment readAttachment(int bno) {
		  return attachmentDao.findById(bno);
	  }
	  
	  public List<Attachment> deleteAttachment(int fno,int bno,String username){
		  Attachment attachment = attachmentDao.findById(bno);
		  if(attachment!=null) {
			  if(attachment.getWriter().equals(username)==true) {
				  File file = new File("d:/upload/attachment",attachment.getSaveFileName());
				  if(file.exists()==true)
					  file.delete();
				  attachmentDao.deleteById(fno);
			  }
		  }
		  boardDao.update(FreeBoard.builder().bno(bno).build());
		  return attachmentDao.findAllByBno(bno);
	  }
	  public void deleteBoard(int bno, String username) {
		  FreeBoard board = boardDao.findById(bno);
		  String content = board.getContent();
		  Matcher matcher = ckImagePattern.matcher(content);
		  while(matcher.find()) {
				String src = matcher.group();
				int start = src.indexOf("ckimage/");
				int end = src.indexOf("style=");
				String fileName = src.substring(start+8, end-2);
				File file = new File(imageFolder, fileName);
				if(file.exists()==true)
					file.delete();
			}
			
		  List<Attachment> list = attachmentDao.findAllByBno(bno);
		  for(Attachment a: list) {
			  File file = new File("d:/upload/attachment",a.getSaveFileName());
			  if(file.exists()==true)
				  file.delete();
		  }
		  attachmentDao.deleteAllByBno(bno);
		  boardDao.delete(bno);
		  
	  }

}*/
