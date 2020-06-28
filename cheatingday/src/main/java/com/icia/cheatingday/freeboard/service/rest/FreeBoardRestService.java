package com.icia.cheatingday.freeboard.service.rest;

import java.io.File;
import java.time.LocalDateTime;
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
import com.icia.cheatingday.freeboard.dao.CommentDao;
import com.icia.cheatingday.freeboard.dao.FreeBoardDao;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
import com.icia.cheatingday.freeboard.entity.Attachment;
import com.icia.cheatingday.freeboard.entity.Comment;
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
	@Autowired
	private CommentDao commentDao;
	@Value("http://localhost8081/ckimage/")
	private String ckUrl;
	@Value("${imageFolder}")
	private String imageFolder;
	@Value("${imagePath}")
	private String imagePath;
	
	Pattern ckImagePattern = Pattern.compile("src=\".+\"\\s");
	
	
	public Attachment readAttachment(int bno) {
		return attachmentDao.findById(bno);
	}
	
	public List<Comment> writeComment(Comment comment){
		comment.setWriteTime(LocalDateTime.now());
		String commentStr = comment.getContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		comment.setContent(commentStr);
		commentDao.insert(comment);
		boardDao.update(FreeBoard.builder().bno(comment.getBno()).commentCnt(1).build());
		return commentDao.findAllByBno(comment.getBno());
	}
	
	public int updateComment(Comment comment){
		return commentDao.update(comment);
	}
	public List<Comment> deleteComment(int cno, int bno, String username){
		Comment comment = commentDao.findById(cno);
		//글쓴이가 다르면 exception발생하게 하는거 만들어줘야대
		commentDao.delete(cno);
		return commentDao.findAllByBno(bno);
	}
	
	public void deleteBoard(int bno, String username) {
		FreeBoard board = boardDao.findById(bno);
		//board가 null일때랑 글쓴이가 다를때 excepttion보내는거 만들어줘야대!
		
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
			//파일이 있는게 맞으면 지우기
			if(file.exists()==true)
				file.delete();
		}
		attachmentDao.deleteAllByBno(bno);
		boardDao.delete(bno);
		
	}
	public List<Attachment> deleteAttachment(int fno,int bno,String username){
		Attachment attachment = attachmentDao.findById(bno);
		//null이 아니면
		if(attachment!=null) {
			//글쓴이가 같은지 확인하고
			if(attachment.getWriter().equals(username)==true) {
				//파일 경로에 파일이 있으면은
				File file = new File("d:/upload/attachment",attachment.getSaveFileName());
				if(file.exists()==true)
					//지워
					file.delete();
				attachmentDao.deleteById(fno);
			}
		}
		boardDao.update(FreeBoard.builder().bno(bno).attachementCnt(1).build());
		return attachmentDao.findAllByBno(bno);
	}
	
	  public String saveCkImage(MultipartFile upload) {
	         Map<String, String> map = new HashMap<String, String>();
	        //업로드가 되면
	         if (upload!= null) {
	        	 //image/로 시작되면
	        	 
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
	  public void updateBoard(FreeBoardDto.DtoForUpdate dto) {
		  FreeBoard board = boardDao.findById(dto.getBno());
		  //board가 null일때랑 글쓴이가 다를 때 exception 보낼거 만들어줘야대
		  board = modelMapper.map(dto, FreeBoard.class);
		  boardDao.update(board);
	  }
	  
	  

}
