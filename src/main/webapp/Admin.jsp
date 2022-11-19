<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="Asset/CSS/StarCiCSS4.css"/> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/9689277209.js" crossorigin="anonymous"></script>
        <title>Administrator Board</title>
    </head>
    <body class = "starci-hide-scrollbar">  
        
        <section class ="starci-full-container starci-img-background">
               <jsp:include page="NavBar.jsp" />
            <div class ="row m-0 p-0">
                <div class ="col-4 p-4">
                    <section class ="p-4"> <i class="fa-solid fa-pager"></i> ADMINISTRATOR BOARD </section>
                    <section class = "p-4 starci-rounded shadow" style = "background: #D2C4B8">
                        <div class = "h3 starci-font-alteration" > SEARCH</div>
                        <form id = "AdminShowServlet" action="AdminShowServlet"> </form>
                        <div class ="d-flex">
                            <div class="form-floating flex-fill me-3">
                                <input form ="AdminShowServlet" type="text" class="form-control" name = "username" value = "${requestScope.username}" id="floatingInput" placeholder="name@example.com"   >
                                <label for="floatingInput" > Username </label>
                            </div>

                            <button form ="AdminShowServlet" type = "submit" class = " btn btn-white starci-button-sp2 float-end position-relative">
                                <i class="fa-solid fa-search fa-2x"></i>
                            </button>
                        </div>

                    </section>
                    <form action = "CreateNewAccount.jsp">
                        <button type = "submit" class = " btn btn-lg starci-button-sp mt-3 position-relative">
                            Create New Account
                        </button>
                    </form>
                </div>

                <div class ="col-8 p-4">         

                    <section class = "p-4">

                        <div class = "h3 starci-font-alteration text-end" > RESULT</div> 
                        <div style = "overflow: auto; overflow-x: hidden; max-height: 800px" class ="sposition-relative p-0">
                            <div style ="margin-top:-1.5rem"> 
                                <c:forEach items = "${requestScope.users}" var = "user"> 
                                    <section class ="p-4">

                                        <a href ="DisableAccountServlet?ID=${user.username}" class = "starci-no-hyperlink">
                                            <div  class =" shadow starci-hover-gray starci-pointer starcii">
                                                <div class ="row p-3">
                                                    <div class ="col-2">
                                                        <img src ="${user.picture}" class = "img-fluid">
                                                    </div>
                                                    <div class ="col-8">
                                                        <div class = "h4"> 

                                                            @${user.username} </div>
                                                        <div class ="row"> 
                                                            <div class ="col-6">
                                                                <div> <span style = "font-weight: bold">  First Name </span> &nbsp; ${user.firstName} </div>
                                                                <div>  <span style = "font-weight: bold"> DOB </span> &nbsp;
                                                                    <c:if test = "${not empty user.DOB}">
                                                                        ${user.DOB}
                                                                    </c:if>
                                                                    <c:if test = "${empty user.DOB}">
                                                                        Unknown
                                                                    </c:if>
                                                                </div>
                                                                <div> <span style = "font-weight: bold"> Address </span>&nbsp; <c:if test = "${not empty user.address}">
                                                                        ${user.address}
                                                                    </c:if>
                                                                    <c:if test = "${empty user.address}">
                                                                        Unknown
                                                                    </c:if> </div>

                                                            </div>
                                                            <div class ="col-6">
                                                                <div>  <span style = "font-weight: bold"> Last Name </span> &nbsp;${user.lastName} </div>
                                                                <div> <span style = "font-weight: bold"> Role </span> &nbsp;<c:if test = "${not empty user.role}">
                                                                        ${user.role}
                                                                    </c:if>
                                                                    <c:if test = "${empty user.role}">
                                                                        Unknown
                                                                    </c:if> </div>
                                                                   <div>  <span style = "font-weight: bold"> Status </span> &nbsp;${user.status} </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                    <div class = "col-2">
                                                        <form action = "DisableAccountServlet"> 
                                                            <input type ="hidden" name = "param" value = "${user.username}">
                                                            <button class ="btn btn-lg starci-button-sp2 float-end position-relative starci-no-break">
                                                                <c:if test = "${user.status}">   
                                                                       <input type ="hidden" name = "act" value = "ban">
                                                                <i class="fa-solid fa-ban fa-2x"></i>
                                                                </c:if>
                                                                 <c:if test = "${!user.status}">       
                                                                     <input type ="hidden" name = "act" value = "allow">
                                                                <i class="fa-solid fa-check fa-2x"></i>
                                                                </c:if>
                                                            </button>
                                                        </form>
                                                    </div>
                                                </div>

                                            </div>
                                        </a>
                                    </section>          
                                </c:forEach>
                            </div>
                        </div> 
                    </section>
                </div>
            </div>
                                    <jsp:include page="Footer.jsp" />
        </section>
</html>