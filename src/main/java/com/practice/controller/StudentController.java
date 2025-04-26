package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.Dto.StudentDto;
import com.practice.entity.Student;
import com.practice.service.StudentService;

//import jakarta.validation.Valid;
import javax.validation.Valid;

@RestController
public class StudentController {
	
//	@Autowired
//	private StudentService service;
//	@PostMapping("/create")
//	public ResponseEntity<?> createStudent( @RequestBody StudentDto dto ) {
//	StudentDto saved= service.createStudent(dto);
//	return new ResponseEntity<>(saved ,HttpStatus.CREATED) ;
//	}
//April 16th class using validation updated code for create statement 
	@Autowired
	private StudentService service;
	@PostMapping("/create")
	public ResponseEntity<?> createStudent( @RequestBody 
		    @Valid StudentDto dto ,
		    BindingResult result
		    ) {
	    if (result.hasErrors()) {
	        return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
	    }
	StudentDto saved= service.createStudent(dto);
	return new ResponseEntity<>(saved ,HttpStatus.CREATED) ;
	}
//same post method using optional 
//	SAME CODE USING OPTIONAL 
//	if (result.hasErrors()) {
//	    List<String> errors = result.getFieldErrors()
//	                                .stream()
//	                                .map(FieldError::getDefaultMessage)
//	                                .collect(Collectors.toList());
//	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	}

// -----------------------------
//read data
//	@PutMapping("/readall")
//	public ResponseEntity<List<StudentDto>> findstudent(){
//		List<StudentDto> listall =service.findstudent();
//        return new ResponseEntity<>(listall, HttpStatus.OK);	
//	}

//Read data using paging sorting etc
	@PutMapping("/readall")
	public ResponseEntity<List<StudentDto>> findstudent(
			@RequestParam(name="pageNo",defaultValue="0",required=false)int pageNo,
    		@RequestParam(name="pageSize",defaultValue="5",required=false)int pageSize,
    		@RequestParam(name="sortBy",defaultValue="id",required=false)String sortBy,
            @RequestParam(name="sortDir",defaultValue="Asc",required=false)String sortDir
			){
		List<StudentDto> listall =service.findstudent(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(listall, HttpStatus.OK);	
	}
	
//update
	@PutMapping("/update")
	public ResponseEntity<StudentDto> updateStudent( @RequestParam Long id ,@RequestBody StudentDto dto ) {
	StudentDto update= service.updateStudent(id , dto);
	return new ResponseEntity<>(update ,HttpStatus.OK) ;
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteStudent( @RequestParam Long id ) {
	StudentDto delete= service.deleteStudent(id);
	return new ResponseEntity<>(delete ,HttpStatus.OK) ;
	}
	
//find student by id
	@GetMapping("/FindById")
	public ResponseEntity<?>  fetchbyid(@RequestParam Long id) {
		StudentDto fetchid =service.fetchbyid(id);
		if (fetchid == null) {
	        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(fetchid, HttpStatus.OK);

		}
	
	
//find student by email
		@GetMapping("/FindByEmail")
		public ResponseEntity<?>  fetchbyemail(@RequestParam String email) {
			StudentDto fetchemail =service.fetchbyemail(email);
			if (fetchemail == null) {
		        return new ResponseEntity<>("Student email not found", HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(fetchemail, HttpStatus.OK);

			}
	
//find student by course
		@GetMapping("/FindByCourse")
		public ResponseEntity<?> fetchbycourse(@RequestParam String course) {
			List<StudentDto> fetchcourse =service.fetchbycourse(course);
			return new ResponseEntity<>(fetchcourse, HttpStatus.OK); 
		}
		
//find student by email and mobile
		@GetMapping("/FindByEmailandMobile")
		public ResponseEntity<?> fetchbyemailandmobile(@RequestParam String email , @RequestParam int mobile) {
			StudentDto fetchbyemailandmobile =service.fetchbyemailandmobile(email, mobile);
			if (fetchbyemailandmobile == null) {
		        return new ResponseEntity<>("Student Not Registered", HttpStatus.NOT_FOUND);
	        }
			return new ResponseEntity<>(fetchbyemailandmobile, HttpStatus.OK); 
		}


}
