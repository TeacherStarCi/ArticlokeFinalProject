<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" defer></script>
        <link rel="stylesheet" href="Asset/CSS/StarCiCSS4.css"/>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/9689277209.js" crossorigin="anonymous"></script>
    </head>
    <body>
       
        <section class = "starci-full-container starci-img2-background">
            <section class ="position-absolute starci-center shadow p-4 starci-rounded" style ="width: 50%;  background: #EADACD">

                <div class = "text-center h2 starci-font-alteration"> 
                    SIGN IN
                </div>

                <div class = "row">
                    <div class = "col-6 position-relative">
                        <form action = "SignInServlet" id = "SignInServlet" method = "POST">                          
                        </form>
                        <div class = "h5 text-center mt-3">
                            ARTICLOKE ACCOUNT
                        </div>
                        <div class="form-floating mt-3 ">
                            <input form = "SignInServlet" type="text" class="form-control" id="floatingInput" name = "username" value = "${requestScope.username}" placeholder="name@example.com" >
                            <label class="form-label" for="floatingInput"> Username </label>
                        </div>

                        <div class="form-floating mt-3">
                            <input form = "SignInServlet" type="password" class="form-control" id="floatingInput" name = "password" value = "${requestScope.password}" placeholder="name@example.com" >
                            <label class="form-label" for="floatingInput"> Password </label>
                        </div>

                        <c:if test = "${not empty requestScope.signInError}">
                            <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                <strong>Error! </strong>   Incorrect Username or Password.
                            </div>
                        </c:if>    
                        <button form = "SignInServlet" class ="btn btn-lg mt-3 starci-horizontal-center starci-button-sp starci-background position-relative me-3" name = "action" value = "Create"> <span class = "h5"> Sign In </span></button>

                        <div class = "mt-3 text-center">
                            Do not have an account?
                            <a href = "SignUp.jsp" class = "starci-hyperlink">
                                Sign Up
                            </a>    
                            <br> &nbsp;
                        </div>
                    </div>

                    <div class =" col-6 position-relative">
                        <div class = "h5 text-center mt-3">
                            GOOGLE ACCOUNT
                        </div>
                        <div class = "starci-center position-absolute" style = "width: 80px; height: 80px; top: 8rem"> 
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile&redirect_uri=http://localhost:8080/Articloke/GoogleSignInServlet&response_type=code
                               &client_id=52738076021-1bvsa3fq1vg3ktl82ghicqd9c8k3vet9.apps.googleusercontent.com&approval_prompt=force">
                                <img class ="img-fluid" src = "Asset/Image/Application/GoogleIcon.png"> 
                            </a>
                        </div>
                        <div class = "position-absolute starci-horizontal-center text-center starci-no-break" style ="bottom: 0" > By Continuing with Google, <br> you are agreeing to our Terms
                            & Conditions.</div>
                    </div>
                </div>

            </section>
        </section>
    </body>
</html>
