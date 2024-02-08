package com.nowon.bul.controller;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nowon.bul.department.DeService;
import com.nowon.bul.domain.dto.approval.AppRequestFilesDTO;
import com.nowon.bul.domain.dto.approval.AppResponseFilesDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDeptList;
import com.nowon.bul.domain.dto.approval.ApprovalDraftDTO;
import com.nowon.bul.domain.dto.approval.ApprovalDraftListDTO;
import com.nowon.bul.domain.dto.approval.ApprovalMemberDTO;
import com.nowon.bul.domain.dto.approval.ApprovalMemberListDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitDTO;
import com.nowon.bul.domain.dto.approval.ApprovalWaitListDTO;
import com.nowon.bul.domain.dto.approval.EmpDTO;
import com.nowon.bul.domain.entity.approval.ApprovalDoc;
import com.nowon.bul.domain.entity.member.Member;
import com.nowon.bul.domain.entity.member.MyUser;
import com.nowon.bul.service.ApprovalService;
import com.nowon.bul.service.AwsService;
import com.nowon.bul.service.DeptService;
import com.nowon.bul.service.MemberService;
import com.nowon.bul.utils.jpaPage.PageRequestDTO;
import com.nowon.bul.utils.jpaPage.PageResultDTO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/approval")
@Controller
public class ApprovalController {

	private final DeptService deptService;

	private final ApprovalService approvalService;

	private final MemberService memberService;
	
	private final AwsService awsService; 

	@GetMapping("")
	public String approvalModal() {
		return "views/approval/home";
	}

	// 결재상신
	@Transactional
	@PostMapping("")
	public String approval(ApprovalDTO dto,AppRequestFilesDTO filesDto ,Authentication authentication) {
		MyUser user = (MyUser) authentication.getPrincipal();
		Member member = memberService.getFindById(user.getMemberNo());
		
		if(filesDto.getNewNames()==null) {
			approvalService.saveApproval(dto, member, null);
		}else {
			awsService.s3fileTemptoSrc(filesDto.getNewNames());
			approvalService.saveApproval(dto, member, filesDto);
		}
		
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

	// 결재문서함
	@GetMapping("/list")
	public String list(Model model, Authentication authentication) {
		MyUser user = (MyUser) authentication.getPrincipal();

		List<ApprovalWaitListDTO> list = approvalService.getApprovalList(user.getMemberNo());
		model.addAttribute("list", list);

		return "views/approval/list";
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

	// 결제대기함 상세
	@GetMapping("/wait-list/{no}")
	public String waitDetail(@PathVariable(name = "no") Long docNo, Model model, Authentication authentication) {
//		MyUser user = (MyUser) authentication.getPrincipal();
//		Member member = memberService.getFindById(user.getMemberNo());

//		if (member.getNo() != approvalService.getDocByid(docNo).getMember().getNo()) {
//			return "views/approval/wait-list";
//		};

		ApprovalWaitDTO dto = approvalService.getWait(docNo);
		List<AppResponseFilesDTO> files = approvalService.getFiles(docNo);

		model.addAttribute("files", files);
		model.addAttribute("dto", dto);
		return "views/approval/wait-doc";
	}

	// 기안문서함
	@GetMapping("/draft-list")
	public String draftLlist(PageRequestDTO pageRequestDTO, Model model, Authentication authentication) {
		MyUser user = (MyUser) authentication.getPrincipal();
		Member member = memberService.getFindById(user.getMemberNo());

		PageResultDTO<ApprovalDraftListDTO, ApprovalDoc> result = approvalService.getDraftList(member, pageRequestDTO);

		model.addAttribute("result", result);
		return "views/approval/draft-list";
	}

	// 기안문서 상세
	@GetMapping("/draft-list/{no}")
	public String draftDetail(@PathVariable(name = "no") Long docNo, Model model, Authentication authentication) {
		MyUser user = (MyUser) authentication.getPrincipal();
		Member member = memberService.getFindById(user.getMemberNo());

		// url직접 접근 방지
		if (member.getNo() != approvalService.getDocByid(docNo).getMember().getNo()) {
			return "views/approval/draft-list";
		}
		;

		ApprovalDraftDTO dto = approvalService.getDraft(docNo);

		model.addAttribute("dto", dto);
		return "views/approval/draft-doc";
	}
    
	// 전자결재 승인or반려
	@PostMapping("/{result}/{no}")
	public String docAccept(@PathVariable(name = "result") String result, @PathVariable(name = "no") Long docno, Authentication authentication) {
		MyUser user = (MyUser) authentication.getPrincipal();
		approvalService.changeResult(docno, result ,user.getMemberNo());
		
		return "redirect:/approval/wait-list/" + docno;
	}

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
	
	// 첨부파일 임시저장
	@ResponseBody
	@PostMapping("/temp-upload")
	public Map<String, String> s3fileUpload(@RequestParam(name = "file") MultipartFile file) {
		return awsService.s3fileTempUpload(file);
	}
	
	// 파일 다운로드
	@GetMapping("/download")
	public ResponseEntity<Resource> downloadFile(@RequestParam(name = "newName") String newName, @RequestParam(name = "orgName") String orgName) {
		return awsService.fileDownload(newName, orgName);
    }
	
}
