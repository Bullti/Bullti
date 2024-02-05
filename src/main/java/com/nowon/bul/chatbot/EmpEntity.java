package com.nowon.bul.chatbot;

import com.nowon.bul.chatbot.PhoneInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "emp")
@Entity
public class EmpEntity {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	private String name;
	private String phone;
	
	
	@JoinColumn(name = "mgr_no")
	@ManyToOne
	private EmpEntity mgr;
	
		
	@JoinColumn(name = "dept_no")
	@ManyToOne
	private DeptEntity dept;
	
	public PhoneInfo toPhoneInfo() {
		return PhoneInfo.builder()
				.deptName(dept.getName())
				.phone(phone)
				.memberName(name)
				.build();
	}
}
