package com.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.practice.Dto.StudentDto;
import com.practice.Exception.ResourceNotFound;
import com.practice.entity.Student;
import com.practice.repo.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repo ;
//create
	public StudentDto createStudent(StudentDto dto) {
		Student s = new Student();
		BeanUtils.copyProperties(dto,s);
		Student saved = repo.save(s);
		BeanUtils.copyProperties(saved,dto);
		return dto ;
		
	}
//Read using Traditional Approach
//	public List<StudentDto> findstudent() {
//        List<StudentDto> studentDtoList = new ArrayList<>();
//		Iterable<Student> all = repo.findAll();
//		for(Student student :all) { //entity we are fetching one by one so we need dto 
//			StudentDto dto =new StudentDto();
//			BeanUtils.copyProperties(student,dto);
//			studentDtoList.add(dto);  
//			}	
//	        return studentDtoList;
//
//	}
	
//READ USING MODERN APPROACH
//Stream Api approach for to list the students 
//	public List<StudentDto> findstudent() {
//	List<Student> all = repo.findAll();
//	List<StudentDto> collect = all.stream().map(s->convert(s)).collect(Collectors.toList());
//	
//	return collect;
//	}
	public StudentDto convert(Student s) {
		StudentDto dto =new StudentDto();
		BeanUtils.copyProperties(s,dto);
		return dto;
	}
//read data using sorting and validation 

	public List<StudentDto> findstudent(int pageNo, int pageSize, String sortBy, String sortDir) {
	    Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
	    Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
	    org.springframework.data.domain.Page<Student> page = repo.findAll(pageable);
	    return page.getContent().stream().map(this::convert)
	               .collect(Collectors.toList());
	    //or we can use s->convert(s) instead of method ref
	}

	
	
//update
	public StudentDto updateStudent(Long id, StudentDto dto) {
		Student s = new Student();
		BeanUtils.copyProperties(dto,s);
		Student saved = repo.save(s);
		BeanUtils.copyProperties(saved,dto);
		return dto ;
	}
	
//delete
	public StudentDto deleteStudent(Long id) {
		repo.deleteById(id);
		return null;
	}
//fetch by id
//	public StudentDto fetchbyid(Long id) {
//		Optional<Student> byId = repo.findById(id);
//		if(byId.isPresent()) {
//		Student student = byId.get();
//		return convert(student);
//		}
//		return null;
//   }
		
//find student by id method modified for exception

	public StudentDto fetchbyid(Long id) {
	    Student student = repo.findById(id)
	        .orElseThrow(() -> new ResourceNotFound("Student with ID " + id + " not found"));
	    
	    return convert(student);
	}

//fetch by email
	public StudentDto fetchbyemail(String email) {
		Optional<Student> byemail = repo.findByemail(email);
		if(byemail.isPresent()) {
		Student student = byemail.get();
		return convert(student);
		}
		return null;
	}
//fetch by course
	 public List<StudentDto> fetchbycourse(String course) {
		List<Student> bycourse = repo.findBycourse(course);
		List<StudentDto> collect = bycourse.stream().map(s->convert(s)).collect(Collectors.toList());
		return collect;
	}
//fetch by email and mobile
	public StudentDto fetchbyemailandmobile(String email ,int mobile) {
		Optional<Student> byEmailandMobile = repo.findByEmailandMobile(email, mobile);
		if(byEmailandMobile.isPresent()) {
		Student student = byEmailandMobile.get();
		return convert(student);
		}
		return null;
	}

}
	

