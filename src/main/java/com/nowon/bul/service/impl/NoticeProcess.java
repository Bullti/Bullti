package com.nowon.bul.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.RowBounds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.bul.department.DeEntity;
import com.nowon.bul.department.DeRepository;
import com.nowon.bul.domain.dto.NoticeDTO;
import com.nowon.bul.domain.dto.NoticeSaveDTO;
import com.nowon.bul.domain.dto.NoticeUpdateDTO;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MemberRepository;
import com.nowon.bul.domain.entity.member.MyUser;
import com.nowon.bul.mybatis.mapper.NoticeMapper;
import com.nowon.bul.service.NoticeService;
import com.nowon.bul.utils.AuthenUtils;
import com.nowon.bul.utils.PageData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Service
public class NoticeProcess implements NoticeService{

	private final NoticeMapper noticeMapper;
	private final MemberRepository memberRepository;
	private final DeRepository deRepository;
	


	@Override
	public void listProcess(int page, Model model) {
		
		int limit = 10;
		int offset=(page-1)*limit;
		
		
		List<NoticeDTO> result = noticeMapper.findAllLimit(offset,limit);
		
		model.addAttribute("list",result);
		
		
		int rowCount = noticeMapper.countAll();
		
		model.addAttribute("pu",PageData.create(page, limit, rowCount, 5));

		
	}

	//게시글 저장 로직
	@Override
	public String saveProcess(Authentication auth,NoticeSaveDTO dto) {
		
		/*
	     public static long extractMemberNo(Authentication auth) {
        long result = 0L;
        try {
            result = ( (MyUser) auth.getPrincipal() ).getMemberNo();
        } catch (Exception e) {
            log.debug("Authentication object error", e);
        }
        return result;
    	}
		 */	
		// memberNo를 memberId에 저장 
		long memberId = AuthenUtils.extractMemberNo(auth);
		System.out.println("memberId: " + memberId);

	    // memberId를 사용하여  NoticeSaveDTO 객체의 dept_id,name을 찾습니다.
		NoticeSaveDTO member = noticeMapper.findNameById(memberId);
		
		// Member 객체에서 dept의 id를 가져와서 deptName을 찾습니다.
	    String deptName = noticeMapper.findDeptNameById(member.getDeptId());
	    
	 // dto의 writer와 dept를 설정합니다.
	    dto.setName(member.getName());
	    dto.setDeptName(deptName);
	    
	    // 이후 필요에 따라 dto를 사용합니다.
	    noticeMapper.save(dto);
	    
	    
		return "redirect:/members/notice";
			
	}

	@Override
	public void detailProcess(long boardNo, Model model) {
		
		NoticeDTO result = noticeMapper.findById(boardNo).orElseThrow();
		
		model.addAttribute("detail",result);
		
	}

	@Override
	public void deleteProcess(long boardNo) {
		
		int result = noticeMapper.deleteById(boardNo);
		
		log.debug(result+"개의 게시글이 삭제 됨 : "+boardNo);
		
	}

	@Override
	public void updateProcess(NoticeUpdateDTO dto) {
		
		noticeMapper.updateTitleOrContent(dto);
		
	}
	
	
}
