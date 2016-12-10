<%-- 
    Document   : login
    Created on : 09.Aug.2009, 16:15:50
    Author     : Ozkan SARI
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<f:loadBundle basename="general" var="bundle"/>

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>
                <h:outputText value="#{bundle.AppNameHeader}"/>
            </title>

            <link rel="stylesheet" type="text/css"
                  href="<%= request.getContextPath()%>/style/stylesheet.css"/>

            <script language="JavaScript">

                function set_image(button, img)
                {
                    button.src = img;
                }

            </script>
        </head>

        <body>


            <h:form id="form">

                <h:panelGrid columns="2" border="0" cellpadding="3" cellspacing="3">

                    <h:graphicImage url="/images/logo.gif" alt="Welcome to ProjectTrack"
                                    title="Welcome to ProjectTrack" width="149" height="160"/>

                    <h:panelGrid columns="3" border="0" cellpadding="5" cellspacing="3"
                                 headerClass="login-heading">

                        <f:facet name="header">
                            <h:outputText value="#{bundle.AppNameHeader}"/>
                        </f:facet>

                        <h:messages globalOnly="true" styleClass="errors"/> <!>
                        <h:panelGroup/>
                        <h:panelGroup/>

                        <h:outputLabel for="loginName">
                            <h:outputText value="#{bundle.LoginCaption}"/>
                        </h:outputLabel>
                        <h:inputText id="loginName" size="20" maxlength="30"
                                     required="true" value="#{AuthenticationBean.loginName}"> <!>
                            <f:validateLength minimum="5" maximum="30"/>
                        </h:inputText>
                        <h:message for="loginName" styleClass="errors"/>

                        <h:outputLabel for="password">
                            <h:outputText value="#{bundle.PasswordCaption}"/>
                        </h:outputLabel>
                        <h:inputSecret id="password" size="20" maxlength="20"
                                       required="true"
                                       value="#{AuthenticationBean.password}">       <!>
                            <f:validateLength minimum="5" maximum="15"/>
                        </h:inputSecret>
                        <h:message for="password" styleClass="errors"/>

                        <h:panelGroup/>                      <!>
                        <h:commandButton action="#{AuthenticationBean.CheckValidUser}" title="#{bundle.SubmitButtonTitle}"
                                         image="#{facesContext.externalContext.requestContextPath}#{bundle.SubmitButtonImage}"
                                         onmouseover="set_image(this,
                                         '#{facesContext.externalContext.requestContextPath}#{bundle.SubmitButtonOverImage}')"
                                         onmouseout="set_image(this,
                                         '#{facesContext.externalContext.requestContextPath}#{bundle.SubmitButtonImage}');"/>

                        <h:panelGroup/>

                    </h:panelGrid>

                    <!-- Info about login accounts; not in book -->

                    <f:facet name="footer">
                        <h:outputText styleClass="small" value='Login accounts: upper_mgr, proj_mgr, analyst, dev_mgr, sys_mgr, qa_mgr; password is always "faces"'/>
                    </f:facet>

                </h:panelGrid>
            </h:form>

        </body>
    </html>
</f:view>
