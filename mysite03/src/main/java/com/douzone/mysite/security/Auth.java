package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*어노테이션을 어디에 붙여야 되는지*/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface Auth {
	
	// public String value() default "";
	public String role() default "USER";
	public boolean test() default false;
	
	
	
	

}
