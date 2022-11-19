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
        <title>Editor Workspace</title>
    </head>
    <body class = "starci-hide-scrollbar"> 
         
        <section class ="starci-full-container starci-img-background">
             <jsp:include page="NavBar.jsp" />
            <div class ="row m-0 p-0">
                <div class ="col-4 p-4">
                    <section class ="p-4"> <i class="fa-solid fa-pager"></i> PUBLISHER WORKSPACE </section>
                    <section class = "p-4 starci-rounded shadow" style = "background: #D2C4B8">
                        <div class = "h3 starci-font-alteration" > SEARCH</div>
                        <form id = "SearchPublisherWorkspaceServlet" action="SearchPublisherWorkspaceServlet"> </form>
                        <div class ="d-flex">
                            <div class="form-floating flex-fill me-3">
                                <input form ="SearchPublisherWorkspaceServlet" type="text" class="form-control" name = "keyword" value = "${requestScope.keyword}" id="floatingInput" placeholder="name@example.com"   >
                                <label for="floatingInput" > Keyword </label>
                            </div>

                            <button form ="SearchPublisherWorkspaceServlet" type = "submit" class = " btn btn-white starci-button-sp2 float-end position-relative">
                                <i class="fa-solid fa-search fa-2x"></i>
                            </button>
                        </div>
                        <div class ="d-flex mt-3"> 
                            <div class ="flex-fill">
                                <div class="form-floating me-3">
                                    <select form ="SearchPublisherWorkspaceServlet" class="form-select" id="sel1" name ="display">
                                        <option <c:if test = "${requestScope.display == 'All'}"> selected </c:if> value = "All" > All </option>
                                        <option  <c:if test = "${requestScope.display == 'Expired'}"> selected </c:if> value = "Expired" > Expired</option>
                                        <option  <c:if test = "${requestScope.display == 'Closing Date'}"> selected </c:if> value = "Closing Date" > Closing Date</option>
                                        <option  <c:if test = "${requestScope.display == 'Latest'}"> selected </c:if> value = "Latest" > Latest </option>    
                                        </select>
                                        <label for="sel1" class="form-label">Display</label>
                                    </div>

                                </div>
                                <div class ="flex-fill"> 
                                    <div class="form-floating ">

                                        <select form ="SearchPublisherWorkspaceServlet" class="form-select" id="sel1" name="topic">
                                            <option  <c:if test = "${requestScope.topic == 'All'}"> selected </c:if> value = "All" > All </option>
                                        <c:forEach items = "${applicationScope.topics}" var = "topic">      
                                            <option <c:if test = "${requestScope.topic == topic.topic}"> selected </c:if> value = "${topic.topic}"> ${topic.topic}</option>
                                        </c:forEach>
                                    </select>
                                    <label for="sel1" class="form-label">Topic</label>
                                </div>
                            </div>
                        </div>
                        <div class = "d-flex mt-3" style = "margin-left: 0.75rem"> 
                            <div class = "mt-2 me-3">Advanced </div>
             
                            <input style = "width: 2rem; height: 2rem" form ="SearchPublisherWorkspaceServlet" name ="advanced" class = "vertical-center mr-3" type = "checkbox" value = "checked" <c:if test = "${requestScope.advanced}"> checked </c:if>>
                        </div>     
                                     
                                        
                    </section>
                </div>

                <div class ="col-8 p-4">         

                    <section class = "p-4">

                        <div class = "h3 starci-font-alteration text-end" > RESULT</div> 
                        <div style = "overflow: auto; overflow-x: hidden; max-height: 800px" class ="sposition-relative p-0">
                            <div style ="margin-top:-1.5rem"> 
                                <c:forEach items = "${requestScope.approvedPapers}" var = "paper"> 
                                    <section class ="p-4">
                                        <a href ="PublishCoreServlet?ID=${paper.ID}" class = "starci-no-hyperlink">
                                            <div  class =" shadow starci-hover-gray starci-pointer starcii">
                                                <div class ="row p-3">
                                                    <div class ="col-2">
                                                        <img src ="${paper.picture}" class = "img-fluid">
                                                    </div>
                                                    <div class ="col-10">
                                                        <div class = "h4"> 
                                                            <c:if test = "${paper.advanced}">
                                                                <span style ="font-weight: bold">  [Advanced] </span> 
                                                            </c:if>
                                                            ${paper.title} </div>
                                                        <div class ="row"> 
                                                            <div class ="col-6">
                                                                <div> <span style = "font-weight: bold">  ID </span> &nbsp; ${paper.ID} </div>
                                                                <div>  <span style = "font-weight: bold"> Author </span> &nbsp;${paper.username} </div>
                                                                <div> <span style = "font-weight: bold"> Published Status </span>&nbsp; ${paper.publishedStatus} </div>
                                                            </div>
                                                            <div class ="col-6">
                                                                <div>  <span style = "font-weight: bold"> Topic </span> &nbsp;${paper.topic} &nbsp; <i class="fa-solid ${applicationScope.topicsPicture.get(paper.topic)} fa-lg"></i> </div>
                                                                <div> <span style = "font-weight: bold"> Submitted Date </span> &nbsp;${paper.submittedDate} </div>
                                                            </div>
                                                        </div>
                                                        <div>  <span  style = "font-weight: bold" > Description </span> <br> 
                                                            <span style = "display: -webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;overflow: hidden;text-overflow: ellipsis;">
                                                                ${paper.description} 
                                                            </span>
                                                        </div>
                                                        <div class = "mt-3">  <span  style = "font-weight: bold" > Remaining Time <i class="fa-solid fa-clock"></i> </span> 
                                                            &nbsp;  
                                                            <c:if test = "${not empty requestScope.correspondingTimes.get(paper.ID)}">
                                                                <span style ="font-style: italic">
                                                                    ${requestScope.correspondingTimes.get(paper.ID).date} DAYS
                                                                    ${requestScope.correspondingTimes.get(paper.ID).hour} HOURS
                                                                    ${requestScope.correspondingTimes.get(paper.ID).minute} MINUTES
                                                                    ${requestScope.correspondingTimes.get(paper.ID).second} SECONDS
                                                                </span>
                                                            </c:if>
                                                            <c:if test = "${empty requestScope.correspondingTimes.get(paper.ID)}">
                                                                <span style ="font-style: italic">  EXPIRED   </span>
                                                            </c:if>
                                                        </div>
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