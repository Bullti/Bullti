package com.nowon.bul.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nowon.bul.department.DeService;
import com.nowon.bul.domain.dto.DeptListDTO;
import com.nowon.bul.domain.dto.IndividualDTO;
import com.nowon.bul.domain.dto.MemberListDTO;
import com.nowon.bul.domain.dto.MemberSaveDTO;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MyUser;
import com.nowon.bul.service.AwsService;
import com.nowon.bul.service.MemberService;
import com.nowon.bul.utils.jpaPage.PageRequestDTO;
import com.nowon.bul.utils.jpaPage.PageResultDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final MemberService memberSerivce;
	private final DeService deptService;
	private final AwsService awsService;

	// 개인정보 조회
	@GetMapping("/individual")
	public String individualPage(Model model, Authentication authentication) {
		MyUser user = (MyUser) authentication.getPrincipal();
		IndividualDTO dto = memberSerivce.getIndividual(user.getMemberNo());

		model.addAttribute("dto", dto);
		return "views/members/individual";
	}

	// 로그인 페이지
	@GetMapping("/login")
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception, Model model) {
		// System.out.println(error);
		// System.out.println(exception);
		// model.addAttribute("error", error);
		// model.addAttribute("exception", exception);
		return "views/login";
	}

	// 회원가입 페이지
	@GetMapping("/members")
	public String joinPage(Model model) {
		List<DeptListDTO> deptList = deptService.getDeptList();
		model.addAttribute("deptList", deptList);
		return "views/members/signup";
	}

	// 사원 조회 페이지
	@GetMapping("/members/list")
	public String listPage(PageRequestDTO pageRequestDTO, Model model) {

		List<String> departmentNames = deptService.getDepartmentNames(); // 상위부서 목록을 가져옴
		model.addAttribute("names", departmentNames); // 뷰로 전달
		PageResultDTO<MemberListDTO, Member> memberList = memberSerivce.getFindAllList(pageRequestDTO);
		model.addAttribute("result", memberList);

		return "views/members/list";
	}

	// 회원가입
	@PostMapping("/members")
	public String join(MemberSaveDTO dto) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + dto);

		if (dto.getNewName() == null) {
			memberSerivce.save(dto, null);
		} else {
			String profileUrl = awsService.s3fileTemptoSrc(dto.getNewName());
			memberSerivce.save(dto, profileUrl);
		}
		return "redirect:members";
	}

	// 사진 임시저장
	@ResponseBody
	@PostMapping("/members/temp-upload")
	public Map<String, String> s3fileUpload(@RequestParam(name = "img") MultipartFile img) {
		return awsService.s3fileTempUpload(img);
	}

	@ResponseBody
	@GetMapping("/memberIdCheck")
	public boolean checkId(@RequestParam(name = "member_id") String id) {
		return memberSerivce.checkId(id);
	}

	@ResponseBody
	@GetMapping("/memberIdPattern")
	public boolean patternId(@RequestParam(name = "member_id") String id) {
		return memberSerivce.patternId(id);
	}

	/*******************비밀번호 변경 관련 로직******************************/
	
	//비밀번호 변경 페이지 (blank)
	@GetMapping("/members/pass")
	public String changePass() {

		return "views/members/change-pass";
	}
	
	//현재비밀번호, 새 비밀번호 일치 검사
	@ResponseBody
	@PostMapping("/members/current-pass")
	public boolean currentPass(@RequestParam(name = "pass") String pass, Authentication authentication) {
		MyUser user = (MyUser) authentication.getPrincipal();
		return memberSerivce.checkpass(user.getMemberNo(),pass);
	}
	

	//비밀번호 변경
	@ResponseBody
	@PostMapping("/members/password")
	public void changePass(@RequestParam(name = "newPass") String newPass, Authentication authentication) {
		MyUser user = (MyUser) authentication.getPrincipal();
		memberSerivce.changePassword(user.getMemberNo(), newPass);
	}
	
}
