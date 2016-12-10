<%-- 
    Document   : error
    Created on : 09.Aug.2009, 16:17:49
    Author     : Ozkan SARI
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Error</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/style/stylesheet.css"/>
    </head>

    <body class="page-background">

        <h:outputText styleClass="error-heading"
                      value="Sorry, a fatal error has occurred. The error has been logged." />

        <p>
            <a href="<%= request.getContextPath()%>">Please log in again.</a>
        </p>

    </body>
</html>
