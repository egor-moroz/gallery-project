<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/global.css" type="text/css">


    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slides.min.jquery.js"></script>


    <script>
        $(function () {
            $('#products').slides({
                preload: true,
                preloadImage: 'img/loading.gif',
                effect: 'slide, fade',
                crossfade: true,
                slideSpeed: 200,
                fadeSpeed: 500,
                generateNextPrev: true,
                generatePagination: false
            });
        });
    </script>

    <spring:message code="label_site_name" var="labelSiteName"/>
    <spring:message code="label_log_out" var="labelLogOut"/>
    <spring:message code="label_upload_files" var="labelUploadFiles"/>
    <spring:message code="label_choose_file" var="labelChooseFile"/>
    <spring:message code="label_send" var="labelSend"/>
    <spring:message code="label_empty_gallery" var="labelEmptyGallery"/>
    <spring:message code="label_cabinet_title" var="labelCabientTitle"/>

    <title>${labelCabientTitle}</title>
</head>
<body>
<div class="navbar navbar-default navbar-fixed-top ">
    <div class="container ">
        <div class="navbar-header">
            <button class="navbar-toggle " type="button" data-toggle="collapse" data-target="#navbar-main">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">${labelSiteName}</a>
        </div>
        <center>

            <div class="navbar-collapse collapse " id="navbar-main">
                <div class="navbar-form navbar-left">
                    <h5 class="form-group"> ${user.firstName}</h5>
                    <h5 style="padding-right: 3px; border-right: solid 1px #c5c5c5"
                        class="form-group"> ${user.lastName}</h5>
                    <h5 style="padding-right: 3px; border-right: solid 1px #c5c5c5"
                        class="form-group">${user.birthDate}</h5>
                    <h5 class="form-group">${user.email}</h5>
                </div>

                <form action="/logout" method="post" class="navbar-form navbar-right" role="search">

                    <div class="form-group">
                        <input type="submit" value="${labelLogOut}" class="btn btn-default"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </div>
                </form>

            </div>
        </center>
    </div>
</div>
<div id="container " class="myConteiner">
    <div id="products_example">
        <div id="products">
            <div class="slides_container">
                <c:choose>
                    <c:when test="${fn:length(user.pictures) > 0}">
                        <c:forEach var="picture" items="${user.pictures}" varStatus="loop">
                            <a href="#" target="_blank">
                                <img src="/user/photo/${picture.id}" width="366" alt="1144953 P 2x"></a>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>${labelEmptyGallery}</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <c:if test="${fn:length(user.pictures) > 0}">
                <ul class="pagination">
                    <c:forEach var="picture" items="${user.pictures}" varStatus="loop">
                        <li><a href="#"><img src="/user/photo/${picture.id}" width="55" alt="1144953 P 2x"></a></li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>

        <div class="uploudForm">
            <form enctype="multipart/form-data" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <p>${labelUploadFiles}</p>
                <p><input type="file" name="file" multiple accept="image/jpg,image/jpeg,image/png">
                    <input type="submit" value="${labelSend}"></p>
            </form>
            <c:forEach var="message" items="${errorUploadMessages}" varStatus="loop">
                <p class="has-error">${message}</p>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
