/*
 * package com.icia.cheatingday.freeboard.service;
 * 
 * import java.io.File; import java.io.IOException; import
 * java.time.format.DateTimeFormatter; import java.util.ArrayList; import
 * java.util.List;
 * 
 * import org.modelmapper.ModelMapper; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service; import
 * org.springframework.util.FileCopyUtils; import
 * org.springframework.web.multipart.MultipartFile;
 * 
 * import com.icia.cheatingday.common.dto.Page; import
 * com.icia.cheatingday.freeboard.dao.AttachmentDao; import
 * com.icia.cheatingday.freeboard.dao.FreeBoardDao; import
 * com.icia.cheatingday.freeboard.dto.FreeBoardDto; import
 * com.icia.cheatingday.freeboard.entity.Attachment; import
 * com.icia.cheatingday.freeboard.entity.FreeBoard; import
 * com.icia.cheatingday.util.PagingUtil;
 * 
 * @Service public class FreeBoardService {
 * 
 * //리스트 페이지, 글쓰기, 글 읽기,
 * 
 * @Autowired private FreeBoardDao dao;
 * 
 * @Autowired private AttachmentDao attachmentDao;
 * 
 * @Autowired private ModelMapper modelMapper;
 * 
 * public Page list(int pageno, String username) { int countOfBoard =
 * dao.count(username); Page page = PagingUtil.getPage(pageno, countOfBoard);
 * int srn = page.getStartRowNum(); int ern = page.getEndRowNum();
 * List<FreeBoard> list = null; if(username!=null) list = dao.findAll(srn, ern);
 * 
 * List<FreeBoardDto.DtoForLIst> dtoList = new
 * ArrayList<FreeBoardDto.DtoForLIst>(); for(FreeBoard board: list) {
 * FreeBoardDto.DtoForLIst dto = modelMapper.map(board,
 * FreeBoardDto.DtoForLIst.class);
 * dto.setWriteTimeStr(board.getWriteTime().format(DateTimeFormatter.ofPattern(
 * "yyyy년MM월dd일"))); dtoList.add(dto); } page.setFreelist(dtoList); return page;
 * 
 * } public int write(FreeBoardDto.DtoForWrite dto) throws IOException {
 * FreeBoard board = modelMapper.map(dto, FreeBoard.class);
 * if(dto.getAttachment()!=null)
 * board.setAttachementCnt(dto.getAttachment().size()); else
 * board.setAttachementCnt(0); dao.insert(board); List<MultipartFile> attachment
 * = dto.getAttachment(); if(attachment!=null) { for(MultipartFile
 * mf:attachment) { Attachment attachments = new Attachment(); String
 * originalFileName = mf.getOriginalFilename(); long time = System.nanoTime();
 * String saveFileName = time+"-"+originalFileName; boolean isImage =
 * mf.getContentType().toLowerCase().startsWith("image/");
 * attachments.setBno(board.getBno()).setImage(isImage).setWriter(board.
 * getUsername()).setFlength((int)mf.getSize()).setOriginalFileName(
 * originalFileName).setSaveFileName(saveFileName); File file = new
 * File("d:/upload/attachment",saveFileName); FileCopyUtils.copy(mf.getBytes(),
 * file); attachmentDao.insert(attachments); } } return board.getBno(); } public
 * FreeBoardDto.DtoForeRead read(int bno, String username){ FreeBoard board =
 * dao.findById(bno); FreeBoardDto.DtoForeRead dto = modelMapper.map(board,
 * FreeBoardDto.DtoForeRead.class);
 * if(username!=null&&username.equals(dto.getUsername())==false)
 * dao.update(FreeBoard.builder().bno(bno).readCnt(0).build()); String str =
 * board.getWriteTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일"));
 * dto.setWriteTimeStr(str); return dto; } //dkdkdd }
 */