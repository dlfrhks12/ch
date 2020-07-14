package com.icia.cheatingday.freeboard.service.rest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icia.cheatingday.freeboard.dao.CommentDao;
import com.icia.cheatingday.freeboard.dao.FreeBoardDao;
import com.icia.cheatingday.freeboard.dao.UserBoardDao;
import com.icia.cheatingday.freeboard.dto.FreeBoardDto;
import com.icia.cheatingday.freeboard.entity.Comment;
import com.icia.cheatingday.freeboard.entity.FreeBoard;

@Service
public class FreeBoardRestService {
	//게시판 업데이트, ckimage저장,첨부파일 읽기
	//attachment delete
	//board delete
	//aa
	@Autowired
	private FreeBoardDao boardDao;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private UserBoardDao userBoardDao;
	@Value("http://localhost8081/ckimage/")
	private String ckUrl;
	@Value("${imageFolder}")
	private String imageFolder;
	@Value("${imagePath}")
	private String imagePath;
	
	Pattern ckImagePattern = Pattern.compile("src=\".+\"\\s");
	
	

	
	public List<Comment> writeComment(Comment comment){
		comment.setWriteTime(LocalDateTime.now());
		String commentContentStr = comment.getContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		comment.setContent(commentContentStr);
		commentDao.insert(comment);
		boardDao.update(FreeBoard.builder().bno(comment.getBno()).commentCnt(1).build());
		return commentDao.findAllByBno(comment.getBno());
	}
	
	public int updateComment(Comment comment){
		return commentDao.update(comment);
	}
	public List<Comment> deleteComment(Integer cno, Integer bno, String username){
		Comment comment = commentDao.findById(cno);
		//글쓴이가 다르면 exception발생하게 하는거 만들어줘야대
		commentDao.delete(cno);
		return commentDao.findAllByBno(bno);
	}
	
	public void deleteBoard(Integer bno, String username) {
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
		
		
		boardDao.delete(bno);
		
	}
	
	
	public String saveCkImage(MultipartFile upload) throws IOException {
		Map<String,String> map = new HashMap<String, String>();
		if(upload!=null) {
			if(upload.getContentType().toLowerCase().startsWith("image/")){
				String imageName = UUID.randomUUID().toString() + ".jpg";
				File file = new File(imageFolder, imageName);
				FileCopyUtils.copy(upload.getBytes(), file);
				String fileUrl = imagePath + imageName;
				// json 데이터로 등록
				// {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
				// 이런 형태로 리턴이 나가야함.
				map.put("uploaded", "1");
				map.put("fileName", imageName);
				map.put("url", fileUrl);
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
			}
		}
		return null;
	}
	  public int updateBoard(FreeBoardDto.DtoForUpdate dto) {
		  FreeBoard board = boardDao.findById(dto.getBno());
		  //board가 null일때랑 글쓴이가 다를 때 exception 보낼거 만들어줘야대
		  board = modelMapper.map(dto, FreeBoard.class);
		  return boardDao.update(board);
	  }
	  public int goodOrBad(Integer bno, boolean isGood, String username) {
		  FreeBoard board = boardDao.findById(bno);
		  System.out.println(board);
		  System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		  System.out.println(userBoardDao.alreadyExist(username, bno)==null);
		  System.out.println(username);
		  System.out.println(bno);
		  if(userBoardDao.alreadyExist(username, bno)!=null) {
			  System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
			  System.out.println(isGood);
			  int result = isGood==true? board.getGoodCnt() : board.getBadCnt();
			  System.out.println(result);
			  return result;
		  }
		  userBoardDao.insert(username, bno);
		  if(isGood==true) {
			  boardDao.update(FreeBoard.builder().bno(bno).goodCnt(board.getGoodCnt()+1).build());
			  return board.getGoodCnt()+1;
		  }else {
			  boardDao.update(FreeBoard.builder().bno(bno).badCnt(board.getBadCnt()+1).build());
			  return board.getBadCnt()+1;
			  
		  }
	  }
	  

}
