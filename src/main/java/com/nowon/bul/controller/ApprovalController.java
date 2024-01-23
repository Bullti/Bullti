package com.nowon.bul.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nowon.bul.department.DeListDTO;
import com.nowon.bul.department.DeService;
import com.nowon.bul.domain.dto.ApprovalDeptList;
import com.nowon.bul.domain.dto.ApprovalMemberDTO;
import com.nowon.bul.domain.dto.ApprovalMemberListDTO;
import com.nowon.bul.domain.dto.MemberDTO;
import com.nowon.bul.service.ApprovalService;
import com.nowon.bul.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/approval")
@Controller
public class ApprovalController {

	private final DeService deptService;
	
	private final ApprovalService approvalService;
	
	private final MemberService memberService;
	
	@GetMapping("")
	public String approvalModal() {
		return "/views/approval/home";
	}
	
	
	@GetMapping("/write")
	public String writePage(Model model, Authentication authentication) {
		String MemberName = authentication.getName();
		System.out.println(">>>>>>>>>>>>>"+MemberName);
		ApprovalMemberDTO member = memberService.getMemberByName(MemberName);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>"+member.toString());
		List<ApprovalDeptList> deptList = deptService.getApprovalList();
		
		model.addAttribute("member", member);
		model.addAttribute("deptList", deptList);
		
		return "/views/approval/write";
	}
	
	//임시저장함
	@GetMapping("/temp/list")
	public String tempLlist() {
		
		return "/views/approval/tempList";
	}
	
	@ResponseBody
	@GetMapping("/members")
	public List<ApprovalMemberListDTO> approvalMemberList(@RequestParam(name = "deptName") String deptName) {
		System.out.println(">>>>>>>>>>>>>부서이름 : " + deptName);
		List<ApprovalMemberListDTO> list = null;
		list = memberService.getApprovalList(deptName);
		for(ApprovalMemberListDTO dto : list) {
			System.out.println(">>>>>>>>>>dto : "+ dto);
		}
		return list;
	}
}
