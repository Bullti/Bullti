package com.nowon.bul.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nowon.bul.department.DeService;
import com.nowon.bul.domain.dto.ApprovalWaitListDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDeptList;
import com.nowon.bul.domain.dto.approval.ApprovalMemberDTO;
import com.nowon.bul.domain.dto.approval.ApprovalMemberListDTO;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MyUser;
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
		return "views/approval/home";
	}

	// 결재상신
	@PostMapping("")
	public String approval(ApprovalDTO dto, Authentication authentication) {
		MyUser user = (MyUser) authentication.getPrincipal();
		Member member = memberService.getFindById(user.getMemberNo());

		approvalService.saveApproval(dto, member);
		return "redirect:approval";
	}

	// 새 결재 진행
	@GetMapping("/write")
	public String writePage(Model model, Authentication authentication) {
		String MemberName = authentication.getName();
		ApprovalMemberDTO member = memberService.getMemberByName(MemberName);
		List<ApprovalDeptList> deptList = deptService.getApprovalList();

		model.addAttribute("member", member);
		model.addAttribute("deptList", deptList);

		return "views/approval/write";
	}

	// 임시저장함
	@GetMapping("/temp/list")
	public String tempLlist() {
		return "views/approval/tempList";
	}

	// 결재대기함
	@GetMapping("/wait-list")
	public String waitLlist(Model model, Authentication authentication) {
		MyUser user = (MyUser) authentication.getPrincipal();
		Member member = memberService.getFindById(user.getMemberNo());

		List<ApprovalWaitListDTO> list = approvalService.getWaitList(member);

		model.addAttribute("list", list);
		return "views/approval/wait-list";
	}

	// 결재대기문서 상세
//	@GetMapping("/wait-list/{no}")
//	public String waitDetail(Model model, Authentication authentication) {
//		MyUser user = (MyUser) authentication.getPrincipal();
//		Member member = memberService.getFindById(user.getMemberNo());
//
//		List<ApprovalWaitListDTO> list = approvalService.getWaitList(member);
//
//		model.addAttribute("list", list);
//		return "views/approval/wait-list";
//	}

	/********************** 아래 비동기 ****************************/

	// 결재선 지정 -> 부서 선택시 멤버 리스트 반환
	@ResponseBody
	@GetMapping("/members")
	public List<ApprovalMemberListDTO> approvalMemberList(@RequestParam(name = "deptName") String deptName) {
		return memberService.getApprovalList(deptName);
	}

	// 결재선 저장
	@ResponseBody
	@PostMapping("/line")
	public ModelAndView approvalLine(EmpDTO dto) {
		return new ModelAndView("views/approval/approval-line").addObject("list", memberService.getFindById(dto));
	}
}
