The Points To Remember 
-Everything is well structured if you want you can add loggers 
- @Id  @GeneratedValue(strategy=GenerationType.IDENTITY) //automatic
-@Column(name="name",nullable=false,unique=true) //for exceptions to check
-@Size(min = 4, message = " name too short") ////validation remeber these terms valid ,binding result
	// if (result.hasErrors()) { return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);}
	//for sorting we should remember name ,default value and required
