package com.khayayphyu.controller.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredPermission {
	public String[] value();
	//public LoginUserType userType() default LoginUserType.DEFAULT;
}
