<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" defer></script>
        <link rel="stylesheet" href="Asset/CSS/StarCiCSS4.css"/>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://kit.fontawesome.com/9689277209.js" crossorigin="anonymous"></script>
        <title>Home</title>
    </head>
    <body>
        <input type ="hidden" form ="currentJSP" name ="JSPName" value ="Home.jsp">
        <section class ="starci-full-container"> 
            <jsp:include page="NavBar.jsp" />
            <section class = "starci-section-container " style ="max-height: 700px; overflow: hidden" >
                <img src="Asset/Image/Application/Desktop.png" style = "width: 100%" class = "position-relative">
                <div class = "position-absolute starci-center" style = "top:40%"> 
                    <p class ="starci-font-alteration text-center" style = "font-size: 6rem; font-weight: bold"> Articloke</p>
                    <p class = "h4"> The garden where knowledge are planted </p>

                </div>  
            </section>
            <section class = "starci-section-container starci-gradient-background">

                <div class ="container"> 

                    <div class = "h2 starci-font-alteration"> LATEST ARTICLES </div>

                    <c:if test = "${applicationScope.articlesLatestPublishedDate.size() >= 7}"> 
                        <div class ="row starci-section-container m-0 mt-2">

                            <c:forEach begin = "0" end = "2" varStatus = "status">
                                <div class ="col-4 position-relative p-1">
                                    <a href = "ViewArticleServlet?ID=${applicationScope.articlesLatestPublishedDate.get(status.index).ID}" class ="starci-no-hyperlink starci-pointer">
                                        <div class ="starci-hover-container"> 

                                            <div class ="starci-hide-image starci-square-container" style = "width: 100%; overflow: hidden">  
                                                <img src ="${applicationScope.articlesLatestPublishedDate.get(status.index).picture}" class = "position-absolute" style ="width: 100%" >
                                            </div> 


                                            <div class ="starci-dislay-text p-3">
                                                <div class ="h4"> ${applicationScope.articlesLatestPublishedDate.get(status.index).title} </div>
                                                <div> <span style = "font-weight: bold">ID </span> ${applicationScope.articlesLatestPublishedDate.get(status.index).ID}  </div>
                                                <div> <span style = "font-weight: bold">Topic </span> ${applicationScope.articlesLatestPublishedDate.get(status.index).topic} <img src ="${applicationScope.topicsPicture.get(applicationScope.articlesLatestPublishedDate.get(status.index).topic)}" style = "width: 1rem"> </div>
                                                <div> <span style = "font-weight: bold">Published Date </span>  ${applicationScope.articlesLatestPublishedDate.get(status.index).publishedDate}  </div>
                                                <div> <span style = "font-weight: bold">Author </span>  ${applicationScope.articlesLatestPublishedDate.get(status.index).username}  </div>                                                               
                                            </div>
                                        </div>
                                    </a>
                                </div>

                            </c:forEach>
                        </div>
                   

                    <div class ="row starci-section-container m-0">
                        <c:forEach begin = "3" end = "6" varStatus = "status" >
                            <div class ="col-3 position-relative p-1">
                                <a href = "ViewArticleServlet?ID=${applicationScope.articlesLatestPublishedDate.get(status.index).ID}" class ="starci-no-hyperlink starci-pointer">
                                    <div class ="starci-hover-container"> 

                                        <div class ="starci-hide-image starci-square-container starci-hide-image" style = "width: 100%; overflow: hidden">  
                                            <img src ="${applicationScope.articlesLatestPublishedDate.get(status.index).picture}" class = "position-absolute" style ="width: 100%" >
                                        </div> 

                                        <div class ="starci-dislay-text p-3">
                                            <div class ="h4"> ${applicationScope.articlesLatestPublishedDate.get(status.index).title} </div>
                                            <div> <span style = "font-weight: bold">ID </span> ${applicationScope.articlesLatestPublishedDate.get(status.index).ID}  </div>
                                            <div> <span style = "font-weight: bold">Topic </span> ${applicationScope.articlesLatestPublishedDate.get(status.index).topic} <img src ="${applicationScope.topicsPicture.get(applicationScope.articlesLatestPublishedDate.get(status.index).topic)}" style = "width: 1rem"> </div>
                                            <div>  <span style = "font-weight: bold">Published Date </span>  ${applicationScope.articlesLatestPublishedDate.get(status.index).publishedDate}  </div>
                                            <div> <span style = "font-weight: bold">Author </span>  ${applicationScope.articlesLatestPublishedDate.get(status.index).username}  </div>                                    
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                         </c:if>
                </div>
            </section>  
            <section class = "pt-5">
                <div class ="container">
                    <div class = "h2 starci-font-alteration"> POPULAR TOPICS </div>

                    <div class ="row starci-section-container m-0" > 
                        <c:forEach items = "${applicationScope.topicsMostCount}" begin = "0" end = "5" step = "2" varStatus = "status" >

                            <div class ="col-4 position-relative">
                                <c:forEach begin = "0" end = "1" varStatus = "status2">
                                    <a class ="starci-no-hyperlink" href = "ForwardSearchHomeTopicServlet?topic=${applicationScope.topicsMostCount.get(status.index + status2.index).topic.topic}"> 
                                        <div class = "p-2 custom-hover-topic shadow starci-hover-light-gray mt-3 starci-rounded">
                                            <i class="fa-solid ${applicationScope.topicsMostCount.get(status.index + status2.index).topic.picture} fa-4x"></i>   &nbsp;
                                            <span class = "h4 ml-1">  ${applicationScope.topicsMostCount.get(status.index + status2.index).topic.topic}  </span>
                                            ${applicationScope.topicsMostCount.get(status.index + status2.index).count} article<c:if test = "${applicationScope.topicsMostCount.get(status.index + status2.index).count > 1}">s</c:if>                                      
                                            </div>
                                        </a>  

                                </c:forEach>
                            </div>

                        </c:forEach>
                    </div>
                </div>      
            </section>
            <section class = "py-5"> </section>
                <jsp:include page="Footer.jsp" />
        </section>
    </body>
</html>
