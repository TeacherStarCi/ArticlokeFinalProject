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
 <input type ="hidden" form ="currentJSP" name ="JSPName" value ="StartRepositoryServlet">
    <body class = "starci-hide-scrollbar">

        <section class ="starci-full-container starci-img2-background">

            <jsp:include page="NavBar.jsp" />
            <div class ="row m-0 p-0">

                <div class ="col-4 p-4">

                    <section class = "p-4">
                        <i class="fa-solid fa-pager"></i> MY REPOSITORY    
                    </section>


                    <section class = "p-4 starci-rounded shadow" style = "background: #D2C4B8">
                        <div class = "h2 starci-font-alteration" > SEARCH</div>
                        <form action = "RepositoryCoreServlet" id = "RepositoryCoreServlet"></form>
                        <div class ="d-flex position-relative" style = "width: 100%">
                            <div class="form-floating flex-grow-1 me-3">
                                <input form ="RepositoryCoreServlet" type="text" name ="keyword" value ="${requestScope.keyword}" class=" form-control" id="floatingInput" placeholder="name@example.com"   >
                                <label for="floatingInput" > Keyword </label>
                            </div>
                            <div class="form-floating flex-grow-1 me-3">
                                <select form ="RepositoryCoreServlet" class="form-select me-3" id="sel1" name ="topic">
                                    <option <c:if test = "${requestScope.topic == 'All'}"> selected </c:if> value = "All"> All </option>
                                    <c:forEach items = "${applicationScope.topics}" var = "topic">
                                        <option <c:if test = "${requestScope.topic == topic.topic}"> selected </c:if> value = "${topic.topic}"> ${topic.topic} &nbsp;

                                            </option>
                                    </c:forEach>

                                </select>
                                <label for="sel1" class="form-label">Topic</label> 
                            </div>
                            <button  form ="RepositoryCoreServlet"type = "submit" class = " btn btn-white starci-button-sp2 float-end position-relative">
                                <i class="fa-solid fa-search fa-2x"></i>
                            </button>

                        </div>

                        <div class="dropdown">
                            <a class ="starci-no-hyperlink h5" href ="#" data-bs-toggle="dropdown">

                                <button type = "submit" class = "btn btn-white mt-3 starci-button-sp2" style = "width: 60%">
                                    <span class = "h5"> Feedback (<c:if test = "${not empty sessionScope.feedbacks}">${sessionScope.feedbacks.size()}</c:if><c:if test = "${empty sessionScope.feedbacks}">0</c:if>)</span>
                                    </button>


                                </a>
                                <ul class="dropdown-menu position-absolute" style = "left:-50rem; width: 50rem">
                                <c:forEach items="${sessionScope.feedbacks}" var = "feedback">
                                    <li><a class="dropdown-item my-0" style ="white-space: normal !important" href="#"><div style = "font-weight: bold"> ${feedback.feedbackDate}  </div> 
                                            <div style = "font-weight: bold">${feedback.header} </div>
                                            <div style ="width: 100%"> 
                                                ${feedback.content}</div></a></li>
                                            </c:forEach>
                                <div class = "p-3">
                                    <form action ="DeleteFeedbackServlet"> 
                                        <button type = "submit" class = "btn btn-white mt-3 starci-button-sp" style = "width: 100%">
                                            <span class = "h5"> Clear Feedback </span>
                                        </button>
                                    </form>
                                </div>

                            </ul>
                        </div>
                    </section>

                </div>

            </div>


            <section class ="p-4">
                <section class ="p-4">
                    <div class = "h2 starci-font-alteration"> 

                        <div class = " me-3"> DRAFT PAPER (${requestScope.draftPapers.size()})</div> 


                    </div>


                    <section style = "overflow: auto">
                        <div class ="d-flex" > 
                            <c:forEach items = "${requestScope.draftPapers}" var = "paper"> 
                                <a href ="StartEditServlet?ID=${paper.ID}" class = "starci-no-hyperlink">
                                    <div  class ="shadow mb-3 starci-hover-gray starci-pointer p-3 me-5 starcii" style = "width: 300px">
                                        <div class ="starci-square-container" style = "width: 100%; overflow: hidden">  
                                            <img src ="${paper.picture}" class = "img-fluid position-absolute">
                                        </div>
                                        <div class = "h4 starci-no-break mt-3" style = "text-overflow: ellipsis; overflow: hidden"> ${paper.title} </div>

                                        <div> <span style = "font-weight: bold">  ID </span> &nbsp; ${paper.ID} </div>
                                        <div>  <span style = "font-weight: bold"> Author </span> &nbsp;${paper.username} </div>
                                        <div> <span style = "font-weight: bold"> Published Status </span>&nbsp; ${paper.publishedStatus} </div>

                                        <div>  <span style = "font-weight: bold"> Topic </span> &nbsp;${paper.topic} <i class ="fa-solid fa-lg ${applicationScope.topicsPicture.get(paper.topic)}"></i> </div>

                                        <div> <span style = "font-weight: bold"> Created Date </span> &nbsp;${paper.createdDate} </div>
                                        <div> <span style = "font-weight: bold">Modified Date </span> &nbsp;
                                            <c:if test = "${not empty paper.modifiedDate}">
                                                ${paper.modifiedDate}
                                            </c:if>
                                            <c:if test = "${empty paper.modifiedDate}">
                                                Not Yet
                                            </c:if>
                                        </div>
                                    </div>

                                </a>
                            </c:forEach>
                        </div>

                    </section>
                    <a href="StartCreatePaperServlet" class ="starci-no-hyperlink d-inline-block mt-3"> 
                        <button type = "submit" class = " btn btn-white starci-button-sp">
                            <span class = "h5"> Create </span>
                        </button>
                    </a>
                </section>
            </section>
            <section class ="row m-0 p-0">      
                <starci class ="col-6 p-0"> 
                    <section class ="p-4">
                        <section class ="shadow starci-rounded p-4" style = "background: #EADACD"> 
                            <div class = "h2 starci-font-alteration"> PENDING PAPER (${requestScope.pendingPapers.size()})</div>

                            <section style = "overflow: auto">
                                <div class ="d-flex" > 
                                    <c:forEach items = "${requestScope.pendingPapers}" var = "paper"> 
                                        <a href ="PaperShowServlet?ID=${paper.ID}" class = "starci-no-hyperlink">
                                            <div  class ="shadow mb-3 starci-hover-gray starci-pointer p-3 me-5 starcii" style = "width: 300px">
                                                <div class ="starci-square-container" style = "width: 100%; overflow: hidden">  
                                                    <img src ="${paper.picture}" class = "img-fluid position-absolute">
                                                </div>
                                                <div class = "h4 starci-no-break mt-3" style = "text-overflow: ellipsis; overflow: hidden"> ${paper.title} </div>

                                                <div> <span style = "font-weight: bold">  ID </span> &nbsp; ${paper.ID} </div>
                                                <div>  <span style = "font-weight: bold"> Author </span> &nbsp;${paper.username} </div>
                                                <div> <span style = "font-weight: bold"> Published Status </span>&nbsp; ${paper.publishedStatus} </div>

                                                <div>  <span style = "font-weight: bold"> Topic </span> &nbsp;${paper.topic} <i class ="fa-solid fa-lg ${applicationScope.topicsPicture.get(paper.topic)}"></i> </div>

                                                <div> <span style = "font-weight: bold"> Submitted Date </span> &nbsp;${paper.submittedDate} </div>
                                                <div> <span style = "font-weight: bold"> Expected Reviewing Date <i class="fa-solid fa-clock"></i> </span> <br>
                                                    <c:if test = "${not empty requestScope.correspondingPendingTimes.get(paper.ID)}">
                                                        <span style ="font-style: italic">
                                                            ${requestScope.correspondingPendingTimes.get(paper.ID).date} DAYS
                                                            ${requestScope.correspondingPendingTimes.get(paper.ID).hour} HOURS
                                                            ${requestScope.correspondingPendingTimes.get(paper.ID).minute} MINUTES
                                                            ${requestScope.correspondingPendingTimes.get(paper.ID).second} SECONDS
                                                        </span>
                                                    </c:if>
                                                    <c:if test = "${empty requestScope.correspondingPendingTimes.get(paper.ID)}">
                                                        <span style ="font-style: italic">  EXPIRED   </span>
                                                    </c:if>
                                                </div>
                                            </div>

                                        </a>
                                    </c:forEach>
                                </div>
                            </section>
                        </section>
                    </section>
                </starci>
                <starci class = "col-6 p-0"> 
                    <section class ="p-4">

                        <section class ="p-4">
                            <div class = "h2 starci-font-alteration"> APPROVED PAPER (${requestScope.approvedPapers.size()}) </div>

                            <section style = "overflow: auto">
                                <div class =" d-flex" > 
                                    <c:forEach items = "${requestScope.approvedPapers}" var = "paper"> 
                                        <a href ="PaperShowServlet?ID=${paper.ID}" class = "starci-no-hyperlink">
                                            <div  class =" float-end shadow mb-3 starci-hover-gray starci-pointer p-3 me-5 starcii" style = "width: 300px">
                                                <div class ="starci-square-container" style = "width: 100%; overflow: hidden">  
                                                    <img src ="${paper.picture}" class = "img-fluid position-absolute">
                                                </div>
                                                <div class = "h4 starci-no-break  mt-3" style = "text-overflow: ellipsis; overflow: hidden"> ${paper.title} </div>

                                                <div> <span style = "font-weight: bold">  ID </span> &nbsp; ${paper.ID} </div>
                                                <div>  <span style = "font-weight: bold"> Author </span> &nbsp;${paper.username} </div>
                                                <div> <span style = "font-weight: bold"> Published Status </span>&nbsp; ${paper.publishedStatus} </div>

                                                <div>  <span style = "font-weight: bold"> Topic </span> &nbsp;${paper.topic} <i class ="fa-solid fa-lg ${applicationScope.topicsPicture.get(paper.topic)}"></i> </div>

                                                <div> <span style = "font-weight: bold"> Submitted Date </span> &nbsp;${paper.submittedDate} </div>
                                                <div> <span style = "font-weight: bold"> Expected Publishing Date <i class="fa-solid fa-clock"></i> </span> <br>
                                                    <c:if test = "${not empty requestScope.correspondingApprovingTimes.get(paper.ID)}">
                                                        <span style ="font-style: italic">
                                                            ${requestScope.correspondingApprovingTimes.get(paper.ID).date} DAYS
                                                            ${requestScope.correspondingApprovingTimes.get(paper.ID).hour} HOURS
                                                            ${requestScope.correspondingApprovingTimes.get(paper.ID).minute} MINUTES
                                                            ${requestScope.correspondingApprovingTimes.get(paper.ID).second} SECONDS
                                                        </span>
                                                    </c:if>
                                                    <c:if test = "${empty requestScope.correspondingApprovingTimes.get(paper.ID)}">
                                                        <span style ="font-style: italic">  EXPIRED   </span>
                                                    </c:if>
                                                </div>
                                            </div>

                                        </a>
                                    </c:forEach>
                                </div>
                            </section>
                        </section>
                    </section>    
                </starci>
            </section>  
        </div>                           

        <section class ="p-4">
            <section class ="p-4">
                <div class = "h2 starci-font-alteration"> ARTICLE (${requestScope.articles.size()})

                </div>

                <section style = "overflow: auto">
                    <div class ="d-flex mt-3" > 
                        <c:forEach items = "${requestScope.articles}" var = "article"> 
                            <a href ="ViewArticleServlet?ID=${article.ID}" class = "starci-no-hyperlink">
                                <div  class ="shadow mb-3 starci-hover-gray starci-pointer p-3 me-5 starcii" style = "width: 300px">
                                    <div class ="starci-square-container" style = "width: 100%; overflow: hidden">  
                                        <img src ="${article.picture}" class = "img-fluid position-absolute">
                                    </div>
                                    <div class = "h4 starci-no-break" style = "text-overflow: ellipsis; overflow: hidden"> ${article.title} </div>
                                    <div> <span style = "font-weight: bold">  ID </span> &nbsp; ${article.ID} </div>
                                    <div>  <span style = "font-weight: bold"> Topic </span> &nbsp;${article.topic} <i class ="fa-solid fa-lg ${applicationScope.topicsPicture.get(article.topic)}"></i> </div>
                                    <div>  <span style = "font-weight: bold"> Published Date </span> &nbsp;${article.publishedDate} </div>
                                    <span style = "font-weight: bold"> Description </span>
                                    <div  style = "display: -webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;overflow: hidden;text-overflow: ellipsis;"> ${article.description}</div>



                                </div>

                            </a>
                        </c:forEach>
                    </div>
                </section>
            </section>
        </section>
        <section class = "p-4"> </section>
            <jsp:include page="Footer.jsp" />
    </section>
</html>
