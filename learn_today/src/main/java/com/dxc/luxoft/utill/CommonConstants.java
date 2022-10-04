package com.dxc.luxoft.utill;

public class CommonConstants {
	
	public final static String TRAINER_USER_TYPE = "T";
	public final static String STUDENT_USER_TYPE = "S";
	public final static String ADMIN_USER_TYPE = "A";
	public final static String TRAINER_ROLE_NAME = "TRAINER";
	public final static String STUDENT_ROLE_NAME = "STUDENT";
	public final static String ADMIN_ROLE_NAME = "ADMIN";
	public static final String[] AUTH_WHITELIST = {
	            "/v2/api-docs",
	            "/swagger-resources",
	            "/swagger-resources/**",
	            "/configuration/ui",
	            "/configuration/security",
	            "/swagger-ui.html",
	            "/webjars/**",
	            "/v3/api-docs/**",
	            "/swagger-ui/**",
	            "/actuator/*",
	            "/stu/register/students",
	            "/courses/login",
	            "/login/v1/user/login",
	            "/courses/register/course",
	            "/trainer/register/trainer",
	           // "/admin/register/admin"
	            
	    };
	
	public static final String[] STUDENT_AUTH_LIST = {
            "/stu/get/course/*",
            "/courses/get/course/by/id/*",
            "/stu/delete/student",
            "/courses/get/all/courses"
            
    };
	
	public static final String[] TRAINER_AUTH_LIST = {
            "/trainer/update/password/*",
            
    };
	public static final String[] ADMIN_AUTH_LIST = {
            "/admin/register/admin/*",
            "/courses/get/course/by/id/*",
            "/courses/get/all/courses"
            
    };

}
