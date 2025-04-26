package com.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.Dto.StudentDto;
import com.practice.entity.Student;
import com.practice.service.StudentService;

//import jakarta.validation.Valid;
import javax.validation.Valid;

//@Controller
//public class StudentControllerView {
	

//April 16th class using validation updated code for create statement 
//	@Autowired
//	private StudentService service;
//	@PostMapping("/createReg")
//	public ResponseEntity<?> createStudent( @RequestBody 
//		    @Valid StudentDto dto ,
//		    BindingResult result
//		    ) {
//	    if (result.hasErrors()) {
//	        return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
//	    }
//	StudentDto saved= service.createStudent(dto);
//	return new ResponseEntity<>(saved ,HttpStatus.CREATED) ;
//	}
//--------------------1st Approach ------>
	//using http 3 approaches kept in word docs 1st one
//@Controller
//public class StudentControllerView {
//	   @Autowired
//	    private StudentService service;
//	    @RequestMapping("/view")
//	    public String viewRegistrationpage() {
//	        return "create_registration"; // JSP name
//	    }
//	    @RequestMapping("/createReg")
//	    public String createStudent(
//	    @ModelAttribute StudentDto dto ,ModelMap model  // 1	
//	    		) { 
//	    			
//	    	StudentDto saved =service.createStudent(dto);//1
//	    	model.addAttribute("msg","Record saved");//TO RETURN A MESSAGE saves inside model
//	        return "create_registration"; // 1 or redirect to confirmation view
//	    }
//2nd approach 
@Controller
public class StudentControllerView {
	   @Autowired
	    private StudentService service;
	    @RequestMapping("/view")
	    public String viewRegistrationpage() {
	        return "create_registration"; // JSP name
	    }
//	    @RequestMapping("/createReg")
//	    public String createStudent(
//	    		@RequestParam String name,  
//                @RequestParam String email,
//                @RequestParam String course,
//                @RequestParam int mobile,
//	    ModelMap model  	
//	    		) { 
//	    	StudentDto dto= new StudentDto(); 
//	    	dto.setName(name); //
//	    	dto.setEmail(email);
//	    	dto.setCourse(course);
//	    	dto.setMobile(mobile);
//	    	StudentDto saved =service.createStudent(dto);//1
//	    	model.addAttribute("msg","Record saved");//TO RETURN A MESSAGE saves inside model
//	        return "create_registration"; // 1 or redirect to confirmation view
//	    }
	    
	    //3rd approach
	    @RequestMapping("/createReg")
	    public String createStudent(
	    		@ModelAttribute Student student ,
	    ModelMap model  	
	    		) { 
	    	StudentDto dto= new StudentDto(); 
	    	dto.setName(student.getName()); //
	    	dto.setEmail(student.getEmail());
	    	dto.setCourse(student.getCourse());
	    	dto.setMobile(student.getMobile());
	    	StudentDto saved =service.createStudent(dto);//1
	    	model.addAttribute("msg","Record saved");//TO RETURN A MESSAGE saves inside model
	        return "create_registration"; // 1 or redirect to confirmation view
	    }
	    
	    
	    
	

// -----------------------------
//read data
//	@PutMapping("/readall")
//	public ResponseEntity<List<StudentDto>> findstudent(){
//		List<StudentDto> listall =service.findstudent();
//        return new ResponseEntity<>(listall, HttpStatus.OK);	
//	}

//Read data using paging sorting etc
//	@GetMapping("/readallReg")
//	public ResponseEntity<List<StudentDto>> findstudent(
//			@RequestParam(name="pageNo",defaultValue="0",required=false)int pageNo,
//    		@RequestParam(name="pageSize",defaultValue="5",required=false)int pageSize,
//    		@RequestParam(name="sortBy",defaultValue="id",required=false)String sortBy,
//            @RequestParam(name="sortDir",defaultValue="Asc",required=false)String sortDir
//			){
//		List<StudentDto> listall =service.findstudent(pageNo,pageSize,sortBy,sortDir);
//        return new ResponseEntity<>(listall, HttpStatus.OK);	
//	}
//display all data in the form
	    @GetMapping("/findallstudentsReg")
	    public String findstudent(
	    		@RequestParam(name="pageNo",defaultValue="0",required=false)int pageNo,
	    		@RequestParam(name="pageSize",defaultValue="3",required=false)int pageSize,
	    		@RequestParam(name="sortBy",defaultValue="id",required=false)String sortBy,
	    		@RequestParam(name="sortDir",defaultValue="asc",required=false)String sortDir,
	    		Model model //model map or model both works
	    		) {
		  List<StudentDto> listall = service.findstudent(pageNo,pageSize,sortBy, sortDir);
           model.addAttribute("students", listall);
	        return "list_registration";

	  }
	
//update
	@PutMapping("/updateReg")
	public ResponseEntity<StudentDto> updateStudent( @RequestParam Long id ,@RequestBody StudentDto dto ) {
	StudentDto update= service.updateStudent(id , dto);
	return new ResponseEntity<>(update ,HttpStatus.OK) ;
	}
//modifying update for the http
	
	
	
	
	
//	@DeleteMapping("/deleteReg")
//	public ResponseEntity<?> deleteStudent( @RequestParam Long id ) {
//	StudentDto delete= service.deleteStudent(id);
//	return new ResponseEntity<>(delete ,HttpStatus.OK) ;
//	}
	
//modified delete reg for http purpose
	   @RequestMapping("/deleteReg")
	    public String deleteStudent(@RequestParam Long id, ModelMap model) {
	        service.deleteStudent(id);
	        // Optional: add success message to show after deletion
	        model.addAttribute("msg", "Student deleted successfully.");
	        return "redirect:/listReg";  // Redirect to list page after deletion
	    }
	
//find student by id
//	@GetMapping("/FindByIdReg")
//	public ResponseEntity<?>  fetchbyid(@RequestParam Long id) {
//		StudentDto fetchid =service.fetchbyid(id);
//		if (fetchid == null) {
//	        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(fetchid, HttpStatus.OK);
//
//		}
//	
//lets change or use this for update registration page
	   @GetMapping("/FindByIdReg")
		  public String getbyid(@RequestParam long id, 
				  Model model) { //Which ever id comes here we are fetching it and using model
			  StudentDto fetchid =service.fetchbyid(id);
			  model.addAttribute("fetchid",fetchid);
		        return "update_registration";
		  }
	
//find student by email
		@GetMapping("/FindByEmailReg")
		public ResponseEntity<?>  fetchbyemail(@RequestParam String email) {
			StudentDto fetchemail =service.fetchbyemail(email);
			if (fetchemail == null) {
		        return new ResponseEntity<>("Student email not found", HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(fetchemail, HttpStatus.OK);

			}
	
//find student by course
		@GetMapping("/FindByCourseReg")
		public ResponseEntity<?> fetchbycourse(@RequestParam String course) {
			List<StudentDto> fetchcourse =service.fetchbycourse(course);
			return new ResponseEntity<>(fetchcourse, HttpStatus.OK); 
		}
		
//find student by email and mobile
		@GetMapping("/FindByEmailandMobileReg")
		public ResponseEntity<?> fetchbyemailandmobile(@RequestParam String email , @RequestParam int mobile) {
			StudentDto fetchbyemailandmobile =service.fetchbyemailandmobile(email, mobile);
			if (fetchbyemailandmobile == null) {
		        return new ResponseEntity<>("Student Not Registered", HttpStatus.NOT_FOUND);
	        }
			return new ResponseEntity<>(fetchbyemailandmobile, HttpStatus.OK); 
		}


}
