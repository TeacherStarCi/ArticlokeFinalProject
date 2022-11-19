<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" defer></script>
        <link rel="stylesheet" href="Asset/CSS/StarCiCSS4.css"/>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/9689277209.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <section class ="starci-full-container starci-img2-background">
            <section class = "starci-section-container-container">

                <div class ="p-4 starci-center starci-rounded shadow position-absolute" style ="width: 30%; background: #EADACD">
                    <div class = "starci-font-alteration text-center h2">
                        SIGN UP
                    </div>
                    <form action = "SignUpServlet" id = "SignUpServlet" method = "get">                 
                    </form>

                    <div class = "text-center"> 
                        Create an Articloke account
                    </div>
                    <div class="form-floating mt-5">
                        <input form = "SignUpServlet" type="text" class="form-control" id="floatingInput" name = "username" value = "${requestScope.username}" placeholder="name@example.com" >
                        <label class="form-label" for="floatingInput"> Username </label>
                    </div>
                    <c:if test = "${not empty requestScope.usernameError}">
                        <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            <strong>Error! </strong> ${requestScope.usernameError}
                        </div>
                    </c:if>

                    <div class="form-floating mt-3">
                        <input form = "SignUpServlet" type="password" class="form-control" id="floatingInput" name = "password" placeholder="name@example.com" >
                        <label class="form-label" for="floatingInput"> Password </label>
                    </div>
                    <c:if test = "${not empty requestScope.passwordError}">
                        <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            <strong>Error! </strong> ${requestScope.passwordError}
                        </div>
                    </c:if>

                    <div class="form-floating mt-3">
                        <input form = "SignUpServlet" type="password" class="form-control" id="floatingInput" name = "confirm" value = "" placeholder="name@example.com" >
                        <label class="form-label" for="floatingInput"> Confirm Password </label>
                    </div>
                    <c:if test = "${not empty requestScope.confirmError}">
                        <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            <strong>Error! </strong> ${requestScope.confirmError}
                        </div>
                    </c:if>
                    <div class ="d-flex mt-3">
                        <section class = "me-3 flex-fill">   <div class="form-floating">
                                <input form = "SignUpServlet" type="text" class="form-control" id="floatingInput" name = "firstName" value = "${requestScope.firstName}" placeholder="name@example.com" >
                                <label class="form-label" for="floatingInput"> First Name </label>
                            </div>
                            <c:if test = "${not empty requestScope.usernameError}">
                                <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                    <strong>Error! </strong> ${requestScope.firstNameError}
                                </div>
                            </c:if>
                        </section>
                        <section> 
                            <div class="form-floating">
                                <input form = "SignUpServlet" type="text" class="form-control" id="floatingInput" name = "lastName" value = "${requestScope.lastName}" placeholder="name@example.com" >
                                <label class="form-label" for="floatingInput"> Last Name </label>
                            </div>
                            <c:if test = "${not empty requestScope.usernameError}">
                                <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                    <strong>Error! </strong> ${requestScope.lastNameError}
                                </div>
                            </c:if>   

                        </section>  
                    </div>


 
                    <div class ="row mt-3"> 
                        <div class ="col-2 position-relative">
                            <input style = "width: 2rem; height: 2rem" class = "position-absolute starci-center" form ="SignUpServlet" name ="term" form ="SignUp" class = "vertical-center mr-3" type = "checkbox" value = "checked">
                        </div>
                        <div class ="col-10">
                            <div class = "vertical-center" style="text-align:justify; left: 30px;"> 
                                I have read and understood the Registered User Agreement and agree to be bound by all of its terms.
                            </div>
                        </div>
                    </div> 
                   <c:if test = "${not empty requestScope.termError}">
                                <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                    <strong>Error! </strong> ${requestScope.termError}
                                </div>
                            </c:if> 
                     <button form = "SignUpServlet" class ="btn btn-lg mt-3 starci-horizontal-center starci-button-sp starci-background position-relative me-3" name = "action" value = "Create"> <span class = "h5"> Sign Up </span></button>
                    <div class = "mt-3 text-center">
                        Already have an account? 
                        <a href = "SignIn.jsp" class = "starci-hyperlink">
                            Sign In
                        </a>   
                    </div>
                </div>

                </div>  
                <!-- End Section 1 -->

            </section>
        </section>
        <!-- End Div Surround -->
    </body>
</html>
