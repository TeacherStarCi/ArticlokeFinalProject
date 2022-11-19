<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="Asset/CSS/StarCiCSS4.css"/>
        <script src="https://kit.fontawesome.com/9689277209.js" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Paper</title>
    </head>
    <body>

        <section class ="starci-full-container starci-img-background">
            <jsp:include page="NavBar.jsp" />
            <section style = "padding-top: 3rem; padding-left: 3rem ">
                <i class="fa-solid fa-pager"></i> VIEW ARTICLE    
            </section>
            <section class ="position-relative starci-horizontal-center  "  >
                <div class ="row m-0 p-0">
                    <div class ="col-1"></div>
                    <div class ="col-7  p-4 row m-0 ">

                        <div  style = "background: #EADACD" class ="p-4 starci-rounded shadow" > 
                            <div class ="row"> 
                                <div class ="col-6">
                                    <img class = "position-relative" src ="${requestScope.article.picture}" style = "width: 100%">
                                </div>
                                <div class ="col-6">
                                    <div class = "h2 starci-font-alteration" >
                                        <c:set var = "title" value = "${fn:toUpperCase(requestScope.article.title)}" />
                                        ${title}
                                    </div>
                                    <div> <span style = "font-weight: bold"> ID </span> &nbsp; ${requestScope.article.ID} </div>
                                    <div> <span style = "font-weight: bold"> Author </span> &nbsp; ${requestScope.article.username} </div>
                                    <div> <span style = "font-weight: bold"> Topic </span> &nbsp; ${requestScope.article.topic} &nbsp; <i class="fa-solid ${applicationScope.topicsPicture.get(requestScope.article.topic)} fa-lg"></i> </div>
                                    <div> <span style = "font-weight: bold"> Published Date </span> <br> ${requestScope.article.publishedDate} </div>

                                    <div class = "mt-3">
                                        <c:if test = "${not empty sessionScope.user}"> 
                                            <form  id = "LoveTransformServlet" action = "LoveTransformServlet">

                                                <input  type ="hidden" name ="ID" value ="${requestScope.article.ID}">
                                                <c:if test ="${requestScope.stayLove}">
                                                    <input  type ="hidden" name ="action" value ="solveLove">
                                                    <i onclick="document.getElementById('LoveTransformServlet').submit();" class="fa-regular fa-heart fa-2x"></i> 
                                                </c:if>
                                                <c:if test ="${requestScope.stayUnlove}">
                                                    <input  type ="hidden" name ="action" value ="solveUnlove">
                                                    <i onclick="document.getElementById('LoveTransformServlet').submit();" class="fa-solid fa-heart fa-2x"></i> 
                                                </c:if>
                                                ${requestScope.article.totalReaction}
                                            </form>
                                        </c:if>
                                        <c:if test = "${empty sessionScope.user}"> 
                                            <i  class="fa-regular fa-heart fa-2x starci-pointer"></i>             
                                            ${requestScope.article.totalReaction}
                                        </c:if>

                                    </div>
                                </div>
                            </div>
                            <section class = "mt-5"> 
                                <div class = "h2 starci-font-alteration" >
                                    DESCRIPTION
                                </div>   
                                ${requestScope.article.description}
                            </section>
                        </div>

                    </div>
                    <div class ="col-3 p-4">
                        <section class = "p-4 shadow" style = "background:  #D2C4B8">

                            <div class = "text-center h2 starci-font-alteration" >
                                DOWNLOAD
                            </div>   
                            <div class ="text-center mt-5" style = "font-style: italic"> Author Offer Price</div>
                            <div class ="text-center h3" style = "font-style: italic"> ${requestScope.article.price}$</div>
                            <form action = "DownloadArticleServlet"> 
                                <input type ="hidden" name ="ID" value ="${requestScope.article.ID}">
                                <div class ="mt-5" style = "font-style: italic"> Ready to get the full version </div>

                                <div class="dropdown">
                                    <a class ="starci-no-hyperlink" href ="#" data-bs-toggle="dropdown">
                                        <button  type = "submit" class = "btn btn-white starci-button-sp2" style = "width: 100%">
                                            <span class = "h5"> Download </span>
                                        </button>
                                    </a>
                                    <div class="dropdown-menu position-absolute p-3">
                                        <div style = "font-style: italic"> Are you sure to download? You must pay for this. </div>
                                        <button class ="mt-3 btn btn-lg starci-button-sp starci-background position-relative starci-no-break" name = "action" value = "confirm" style = "width: 100%"> <span class = "h5"> Confirm </span></button> 
                                    </div>
                                </div>
                                <c:if test = "${requestScope.notLoginError}">
                                    <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                        <strong>Error! </strong>  You have not Signed In.
                                    </div>
                                </c:if>

                                <c:if test = "${requestScope.downloadSuccess}">
                                    <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                        <strong>Error! </strong>  You have download successfully.
                                    </div>
                                </c:if>
                                <c:if test = "${requestScope.notRichError}">
                                    <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                        <strong>Error! </strong>  Your balance is not enough.
                                    </div>
                                </c:if>
                            </form>
                            <div class =" mt-2" style = "font-style: italic"> <span class ="h5"> OR </span> you want to take a look first </div>
                            <form action = "DownloadDemoServlet"> 
                                <input type ="hidden" name ="ID" value ="${requestScope.article.ID}">
                                
                                <button type = "submit" class = "btn btn-white starci-button-sp2" style = "width: 100%" name = "action" value = "getDemo">
                                    <span class = "h5"> Get Demo </span>
                                </button>  
                            </form>
                            <div class ="text-center mt-5" style = "font-style: italic"> <span style = "font-weight: bold">Downloaded </span>${requestScope.article.totalDownload}</div>

                        </section>
                        <c:if test = "${requestScope.thisIsMyArticle}">

                            <section class ="p-4 shadow mt-5"  style = "background: #D2C4B8"> 
                                <div class = "h2 starci-font-alteration text-center" >

                                    EDIT
                                </div>   
                                <div class ="d-flex"> 
                                    <div class="form-floating flex-fill me-3">
                                        <form action = "EditArticleServlet" id = "EditArticleServlet" > </form>
                                        <input form = "EditArticleServlet" type ="hidden" name ="ID" value ="${requestScope.article.ID}">
                                        <select form = "EditArticleServlet" class="form-select" id="sel1" name ="permission">
                                            <option <c:if test = "${requestScope.permission == 'Public' || requestScope.article.permission == 'Public'}"> selected </c:if> value = "Public"> Public </option>
                                            <option <c:if test = "${requestScope.permission == 'Private' || requestScope.article.permission == 'Private'}"> selected </c:if> value = "Private"> Private </option>

                                            </select>
                                            <label for="sel1" class="form-label">Permission</label>

                                        </div>
                                        <div class="form-floating" style = "width: 40%">
                                            <input form = "EditArticleServlet"  type="text" class="form-control" id="floatingInput" name ="price" value ="${requestScope.price}" placeholder="name@example.com" >
                                        <label class="form-label" for="floatingInput">Price </label>
                                        <c:if test = "${requestScope.validError}">
                                            <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                                <strong>Error! </strong> Please input a number that is not negative
                                            </div>
                                        </c:if>
                                    </div>   
                                </div>
                                <button form = "EditArticleServlet"  type = "submit" class = "btn starci-button-sp2 mt-3" style = "width: 100%">
                                    <span class = "h5"> Apply Changes </span>
                                </button>      
                            </section>  

                        </c:if>

                    </div>
                </div>



                <section class ="py-5"></section>
                    <jsp:include page="Footer.jsp" />
            </section>

    </body>
</html>
