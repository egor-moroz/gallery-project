<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Главная страница</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css" type="text/css">

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js"></script>

    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-ui.js"></script>
    <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.js"></script>
    <script>
        $(function () {
            $("#birthDate").datepicker();
            inline:true
        });
    </script>
    <script>
        $(document).ready(function () {
            $("#register-form").validate({
                rules: {
                    firstName: {
                        required: true,
                    },

                    lastName: {
                        required: true,
                    },

                    confirmPassword: {
                        required: true,
                        equalTo: "#password",
                    },
                    email: {
                        required: true,
                        email: true
                    }
                },
                messages: {

                    firstName: {
                        required: "Имя обязателено"

                    },
                    lastName: {
                        required: "Фамилия обязателена"

                    },
                    password: {
                        required: "Пароль Обязателен"

                    },
                    confirmPassword: {
                        required: "Подтверждение пароля обязательно",
                        equalTo: "Пароли должны совпадать"

                    },
                    email: {
                        required: "email обязателен",
                        email: "Не корректный email"
                    }
                }

            });
            $.validator.methods.email = function (value, element) {
                return this.optional(element) || /^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[a-z]{2,4}$/.test(value);
            }
        });

    </script>
    <fmt:formatDate value="${user.birthDate}" var="dateFormat" type="date" pattern="yyyy-MM-dd"/>

    <spring:message code="label_site_name" var="labelSiteName"/>
    <spring:message code="label_login" var="labelLogin"/>
    <spring:message code="label_user_registration" var="labelUserRegistration"/>
    <spring:message code="label_user_name" var="labelUserName"/>
    <spring:message code="label_user_last_name" var="labelUserLastName"/>
    <spring:message code="label_user_birth_day" var="labelUserBirthDay"/>
    <spring:message code="label_user_emai" var="labelUserEmail"/>
    <spring:message code="label_user_password" var="labelUserPassword"/>
    <spring:message code="label_user_confirm_password" var="labelUserConfirmPassword"/>
    <spring:message code="label_user_information" var="labelUserInformation"/>
    <spring:message code="label_user_button_register" var="labelUserButtonRegister"/>
    <spring:message code="label_enter_if_register" var="labelEnterIfRegister"/>
    <spring:message code="label_main_title" var="labelMainTitle"/>

    <title>${labelMainTitle}</title>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">${labelSiteName}</a>
        </div>
        <center>
            <div class="navbar-collapse collapse" id="navbar-main">

                <form action="/login" method="post" class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="Email" required="true">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Password"
                               required="true">
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-default">${labelLogin}</button>
                    <c:if test="${message != null}">
                        <p class="has-error">${message.toString()}</p>
                    </c:if>
                </form>

            </div>
        </center>
    </div>
</div>
<div id="page-wrap">


    <div id="content">

        <form:form action="${pageContext.request.contextPath}/user" method="post" id="register-form"
                   modelAttribute="user" class="registerForm" role="form">

            <h2>${labelUserRegistration}</h2>

            <div id="form-content">
                <fieldset class="field_set">

                    <div class="fieldgroup form-group">
                        <label for="firstName">${labelUserName}</label>
                        <form:input path="firstName" type="text" name="firstName" id="firstName"
                                    cssErrorClass="input-error"  value="${user.firstName}" required="true"/>

                        <form:errors path="firstName" class="has-error"/>

                    </div>

                    <div class="fieldgroup form-group">
                        <label for="lastName">${labelUserLastName}</label>
                        <form:input path="lastName" type="text" name="lastName" id="lastName"
                                    cssErrorClass="input-error"   value="${user.lastName}" required="true"/>

                        <form:errors path="lastName" class="has-error"/>

                    </div>

                    <div class="fieldgroup form-group">
                        <label for="birthDate">${labelUserBirthDay}</label>
                        <form:input path="birthDate" type="text" name="birthDate" id="birthDate"
                                    cssErrorClass="input-error" value="${dateFormat}"/>

                        <form:errors path="birthDate" class="has-errore"/>

                    </div>

                    <div class="fieldgroup form-group">
                        <label for="email">${labelUserEmail}</label>
                        <form:input path="email" type="text" name="email" id="email"
                                    required="true" pattern="^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$"
                                    value="${user.email}" cssErrorClass="input-error"/>

                        <form:errors path="email" class="has-error"/>

                    </div>


                    <div class="fieldgroup form-group">
                        <label for="password">${labelUserPassword}</label>
                        <form:input path="password" type="password" name="password" id="password"
                                    cssErrorClass="input-error"  required="true" value="${user.password}"/>

                        <form:errors path="password" class="has-error"/>

                    </div>

                    <div class="fieldgroup form-group">
                        <label for="confirmPassword">${labelUserConfirmPassword}</label>
                        <input type="password" name="confirmPassword" id="confirmPassword"
                               cssErrorClass="input-error" required="true">

                        <form:errors path="password" class="has-error"/>

                    </div>

                    <div class="fieldgroup form-group">
                        <label for="information">${labelUserInformation}</label>
                        <form:input path="information" type="text" name="information" id="information"
                                    cssErrorClass="input-error"  value="${user.information}"/>

                        <form:errors path="information" class="has-error"/>

                    </div>

                    <div class="fieldgroup form-group">
                        <input type="submit" value="${labelUserButtonRegister}" class="submit">
                    </div>

                </fieldset>
            </div>

            <div class="fieldgroup">
                <p>${labelEnterIfRegister}</p>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form:form>


    </div>

</div>

</body>
</html>

