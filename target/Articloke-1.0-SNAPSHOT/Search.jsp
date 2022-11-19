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
    <body class = "starci-hide-scrollbar">

        <section class ="starci-full-container starci-img2-background "> 
            <jsp:include page="NavBar.jsp" />
            <div class ="row p-0 m-0">
                <div class ="col-3 p-4"> 
                    <section class ="p-4"> <i class="fa-solid fa-pager"></i> SEARCH </section>
                    <section class = "p-4 starci-rounded " style = "background: #D2C4B8">
                        <div class = "position-relative starci-font-alteration starci-horizontal-center h2 "> 
                            SEARCH
                        </div>
                        <div class ="d-flex position-relative" style = "width: 100%">
                            <form id = "GetResultServlet" action = "GetResultServlet"> </form>

                            <div class="form-floating flex-grow-1">
                                <input form = "GetResultServlet"type="text" class=" form-control" id="floatingInput" name = "keyword" value = "${requestScope.keyword}" placeholder="name@example.com"   >
                                <label for="floatingInput" > Keyword </label>
                            </div>

                            <button type = "submit" form = "GetResultServlet" class = "ms-2 btn btn-white starci-button-sp2 float-end position-relative">
                                <i class="fa-solid fa-search fa-2x"></i>
                            </button>

                        </div>
                        <button data-bs-toggle="collapse" data-bs-target="#demo" class = "mt-3 starci-button-sp2 btn btn-lg">
                            <span class = "h5"> Advanced  </span> 


                            <i class="fa-solid fa-angles-down fa-lg"></i></button>

                        <section id="demo" class="collapse">
                            <section class = "py-4 pt-0">
                                <div class="form-floating mt-3">
                                    <input form = "GetResultServlet" type="text" class="form-control" id="floatingInput" name = "title" value = "${requestScope.title}" placeholder="name@example.com" >
                                    <label for="floatingInput">Title </label>
                                </div>
                                <div class ="d-flex mt-3">
                                    <div class="form-floating flex-fill">
                                        <input form = "GetResultServlet" type="text" class="form-control" id="floatingInput"  name = "author" value = "${requestScope.author}" placeholder="name@example.com" >
                                        <label for="floatingInput">Author </label>
                                    </div>
                                    <div class="form-floating flex-fill ms-3">
                                        <div class="form-floating">
                                            <select form = "GetResultServlet" class="form-select" id="sel1" name ="topic">
                                                <option <c:if test = "${requestScope.topic == 'All'}"> selected </c:if> value = "All"> All </option>
                                                <c:forEach items = "${applicationScope.topics}" var = "topic">
                                                    <option <c:if test = "${requestScope.topic == topic.topic}"> selected </c:if> value = "${topic.topic}"> ${topic.topic} &nbsp;

                                                        </option>
                                                </c:forEach>
                                            </select>
                                            <label for="floatingInput">Topic </label>
                                        </div>
                                    </div>
                                </div>
                                <div class ="d-flex mt-3">

                                    <div class="form-floating  flex-fill me-3">
                                        <select form = "GetResultServlet" class="form-select" id="sel1" name ="sort">
                                            <option<c:if test = "${requestScope.sort == 'None'}" > selected </c:if> value = "None"> None </option>
                                            <option <c:if test = "${requestScope.sort == 'Latest'}" > selected </c:if> value = "Latest"> Latest </option>
                                            <option <c:if test = "${requestScope.sort == 'Oldest'}" > selected </c:if> value = "Oldest"> Oldest </option>
                                            <option <c:if test = "${requestScope.sort == 'Alphabet'}" > selected </c:if> value = "Alphabet"> Alphabet </option>
                                            </select>
                                            <label for="sel1" class="form-label">Sort</label>
                                        </div>
                                        <div class="form-floating flex-fill">
                                            <input name ="date" type="date" form = "GetResultServlet" class="form-control" id="floatingInput" value ="${requestScope.date}"  placeholder="name@example.com" >
                                        <label for="floatingInput">Date </label>
                                    </div>
                                </div>

                            </section>
                        </section>
                    </section>
                </div>
                <div class ="col-9 p-4">
                    <section class = "p-4"> 
                        <div class = "text-end position-relative starci-font-alteration starci-horizontal-center h2 "> 
                            RESULT 
                            <c:if test = "${not empty requestScope.articles}">
                                (${requestScope.articles.size()})
                            </c:if>
                        </div>

                        <c:if test = "${!requestScope.dispatchFromController}">
                            <div class = "h1 text-end"> Explore scientific, technical, and medical research</div>
                            <img class =" mt-3 position-relative float-end" src = "Asset/Image/Application/SearchDefault.png">
                        </c:if>
                        <c:if test = "${requestScope.dispatchFromController && empty requestScope.articles}">
                            <div class = "starci-no-break text-end"> 
                                <span class = "h1"> No result found </span>
                                &nbsp; Please edit your search query to find results
                            </div>
                            <img class =" mt-3 position-relative float-end" style="width: 40%" src = "Asset/Image/Application/Error.png">
                        </c:if>
                        <c:if test = "${not empty requestScope.articles}"> 
                            <section style = "height: 800px; overflow: auto; overflow-x: hidden"> 
                                <section style = "margin-top: -1.5rem">
                                    <c:set var = "max4Divisible" scope = "page" value = "${requestScope.articles.size() - requestScope.articles.size() % 4 - 1}"/>
                                    <c:if test ="${max4Divisible >= 3}"> 
                                        <c:forEach varStatus = "status" step="4" begin="0" end = "${pageScope.max4Divisible}"> 

                                            <section class ="row"> 
                                                <c:forEach begin="0" end = "3" varStatus="status2"> 
                                                    <section class ="col-3 p-4"> 
                                                        <a href="ViewArticleServlet?ID=${requestScope.articles.get(status.index + status2.index).ID}" class = "starci-no-hyperlink">
                                                            <div class ="starci-hover-gray p-3 starcii shadow starci-pointer ">
                                                                <div class ="starci-square-container" style = "width: 100%; overflow: hidden">  
                                                                    <img src ="${requestScope.articles.get(status.index + status2.index).picture }" class = "position-absolute" style ="width: 100%" >
                                                                </div> 
                                                                <div class ="h4 mt-3 starci-no-break" style = "text-overflow: ellipsis; overflow: hidden"> ${requestScope.articles.get(status.index + status2.index).title}</div>
                                                                <div>
                                                                    <span style = "font-weight: bold">ID </span> ${requestScope.articles.get(status.index + status2.index).ID} <br>
                                                                    <span style = "font-weight: bold">Author </span>  <br> ${requestScope.articles.get(status.index + status2.index).username} <br>
                                                                    <span style = "font-weight: bold">Topic </span> ${requestScope.articles.get(status.index + status2.index).topic} &nbsp;  <i class="fa-solid ${applicationScope.topicsPicture.get(requestScope.articles.get(status.index + status2.index).topic)} fa-lg"></i>
                                                                    <br>
                                                                    <span style = "font-weight: bold">Published Date </span>  <br> ${requestScope.articles.get(status.index + status2.index).publishedDate} <br>
                                                                    <span style = "font-weight: bold">Description </span> <br>
                                                                    <div  style = "display: -webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;overflow: hidden;text-overflow: ellipsis;"> ${requestScope.articles.get(status.index + status2.index).description}</div>
                                                                </div>
                                                            </div>
                                                        </a>
                                                    </section>
                                                </c:forEach>
                                            </section>

                                        </c:forEach> 
                                    </c:if>
                                    <div class ="row">
                                        <c:forEach items = "${requestScope.articles}" begin="${pageScope.max4Divisible + 1}" end = "${requestScope.articles.size()}" var = "article"> 

                                            <section class ="col-3 p-4"> 
    <a href="ViewArticleServlet?ID=${article.ID}" class = "starci-no-hyperlink">
                                                <div class ="starci-hover-gray p-3 starcii shadow starci-pointer ">
                                                    <div class ="starci-square-container" style = "width: 100%; overflow: hidden">  
                                                        <img src ="${article.picture }" class = "position-absolute" style ="width: 100%" >
                                                    </div> 
                                                    <div class ="h4 mt-3 starci-no-break" style = "text-overflow: ellipsis; overflow: hidden"> ${article.title}</div>
                                                    <div>
                                                        <span style = "font-weight: bold">ID </span> ${article.ID} <br>
                                                        <span style = "font-weight: bold">Author </span>  <br> ${article.username} <br>
                                                        <span style = "font-weight: bold">Topic </span> ${article.topic} &nbsp;  <i class="fa-solid ${applicationScope.topicsPicture.get(article.topic)} fa-lg"></i>
                                                        <br>
                                                        <span style = "font-weight: bold">Published Date </span>  <br> ${article.publishedDate} <br>
                                                        <span style = "font-weight: bold">Description </span> <br>
                                                        <div  style = "display: -webkit-box;-webkit-line-clamp: 3;-webkit-box-orient: vertical;overflow: hidden;text-overflow: ellipsis;"> ${article.description}</div>
                                                    </div>

                                                </div>
    </a>
                                            </section>

                                        </c:forEach>

                                    </div>
                                </section>
                            </section>
                        </c:if>

                    </section>
                </div>
            </div>
                                        <section class = "p-5"> </section>
 <jsp:include page="Footer.jsp" />
        </section>
    </body>
</html>
