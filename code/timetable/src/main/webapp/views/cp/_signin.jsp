<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title><tiles:insertAttribute name="title" ignore="true" /></title>
        <link href="<c:url value="/assets/css/reset.css" />" rel="stylesheet" type="text/css" media="all"/>
        <link href="<c:url value="/assets/css/style.css" />" rel="stylesheet" type="text/css" media="all"/>
    </head>
    <body onload='document.f.j_username.focus();'>        
        <form name="f" action="<c:url value="/j_spring_security_check" />" method="post">
            <div id="login_page">
                <div class="box_login">
                    <a class="logo" href="login.html"></a>
                    <div class="login_value">
                        <div class="ent_value clearfix">
                            <span class="tit">Username</span>
                            <input type="text" name="j_username" id="j_username" value="${username}" placeholder="username"/>
                        </div>
                        <div class="ent_value clearfix no-bdb">
                            <span class="tit">Password</span>
                            <input type="password" name="j_password" id="j_password"value="${param.j_password }" placeholder="password"/>
                        </div>
                    </div>
                    <div class="login_submit">
                        <input name="submit" type="submit"value="submit"/>
                    </div>
<!--                    <p align="center">
                        <a class="forgotPass" href="">Forgot Password</a>
                    </p>-->
                    <div class="error_ntc">
                        <b>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</b>
                    </div>
                </div>
            </div>
        </form>
        <div id="footer">
            <div class="main clearfix">                
            </div>
        </div>
    </body>
</html>