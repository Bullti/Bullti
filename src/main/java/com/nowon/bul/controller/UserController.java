package com.nowon.bul.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.bul.department.DeController;
import com.nowon.bul.department.DeService;
import com.nowon.bul.domain.dto.DeptListDTO;
import com.nowon.bul.domain.dto.MemberListDTO;
import com.nowon.bul.domain.dto.MemberSaveDTO;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.service.AwsService;
import com.nowon.bul.service.MemberService;
import com.nowon.bul.utils.jpaPage.PageRequestDTO;
import com.nowon.bul.utils.jpaPage.PageResultDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@Controller
public class UserController {

	private final MemberService memberSerivce; 
	private final DeService deptService;
	private final AwsService awsService;
	
	@GetMapping("/individual")
	public String individualPage() {
		return "/views/members/individual";
	}
	
	@GetMapping("/login")
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception,
			Model model) {
		//System.out.println(error);
		//System.out.println(exception);
		//model.addAttribute("error", error);
		//model.addAttribute("exception", exception);
		return "/views/login";
	}
	
	@GetMapping("/members")
	public String joinPage(Model model) {
		List<DeptListDTO> deptList = deptService.getDeptList();
		model.addAttribute("deptList", deptList);
		return "/views/members/signup";
	}
	
	@GetMapping("/members/list")
	public String listPage(PageRequestDTO pageRequestDTO, Model model) {
		
		 List<String> departmentNames = deptService.getDepartmentNames(); // 상위부서 목록을 가져옴
		 model.addAttribute("names", departmentNames); // 뷰로 전달
		 PageResultDTO<MemberListDTO, Member> memberList = memberSerivce.getFindAllList(pageRequestDTO);
		 model.addAttribute("result", memberList);
		 
		return "/views/members/list";
	}
	
	@PostMapping("/members")
	public String join(MemberSaveDTO dto) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + dto);
		
		if(dto.getNewName() == null) {
			memberSerivce.save(dto, null);
		}else {
			String profileUrl = awsService.s3fileTemptoSrc(dto.getNewName());
			memberSerivce.save(dto, profileUrl);
		}
		return "redirect:/members";
	}
	
	//사진 임시저장
	@ResponseBody
	@PostMapping("/members/temp-upload") 
	public Map<String, String> s3fileUpload(@RequestParam(name = "img") MultipartFile img)  { 
		return awsService.s3fileTempUpload(img);
	}
	
	@ResponseBody
	@GetMapping("/memberIdCheck")
	public boolean checkId(@RequestParam(name = "member_id") String id) {
		System.out.println(">>>>>>>>>>>" + id);
		boolean flag = memberSerivce.checkId(id);
		if(flag) {
			System.out.println(">>>>>>>>>>>>>>>>참");
		}else {
			System.out.println(">>>>>>>>>>>>거짓");
		}
		return memberSerivce.checkId(id);
	}
	
	 
	
}
