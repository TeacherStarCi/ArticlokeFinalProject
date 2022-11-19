<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src=
                "https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
        </script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <link rel="stylesheet" href="Asset/CSS/StarCiCSS4.css"/>
        <script src="https://kit.fontawesome.com/9689277209.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" defer></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Review Paper</title>
    </head>
    <body>

        <section class ="starci-full-container starci-img-background">
            <section class ="p-4"></section>
            <section class ="starci-section-container">
                <form class="was-validated" id ="ReviewCoreServlet" action="ReviewCoreServlet">  </form>

                <input   form = "ReviewCoreServlet"  type ="hidden" name ="ID" value ="${requestScope.paper.ID}"> 
                <section class ="starci-horizontal-center position-relative p-4 starci-rounded shadow " style = "background: #EADACD; width: 60%"  >

                    <div class = "position-relative text-center starci-font-alteration starci-horizontal-center h3"> 
                        REVIEW PENDING PAPER   
                    </div>
                    <div class ="row mt-5 ">
                        <div class ="col-6">
                            <div class="form-floating">
                                <input form = "ReviewCoreServlet" type="text" class="form-control" id="floatingInput" name ="title"  value ="${requestScope.paper.title}${requestScope.title}"  placeholder="name@example.com" >
                                <label class="form-label" for="floatingInput"> Title </label>
                            </div>
                            <c:if test = "${not empty requestScope.titleError}">
                                <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                    <strong>Error! </strong> ${requestScope.titleError}
                                </div>
                            </c:if>

                            <div class="form-floating mt-3">
                                <select form = "ReviewCoreServlet" class="form-select" id="sel1" name ="topic">
                                    <c:forEach items = "${applicationScope.topics}" var = "topic">
                                        <option <c:if test = "${requestScope.paper.topic == topic.topic || requestScope.topic == topic.topic}"> selected </c:if> value = "${topic.topic}"> ${topic.topic}</option>
                                    </c:forEach>
                                </select>
                                <label for="sel1" class="form-label">Topic</label>
                            </div>


                        </div> 
                        <div class ="col-6">
                            <input form = "ReviewCoreServlet" type ="hidden" name = "picture" value = "${requestScope.paper.picture}${requestScope.picture}">
                            <img class = "position-relative" src ="${requestScope.paper.picture}${requestScope.picture}" style = "width: 100%">
                        </div> 
                    </div>
                    <div class = "mt-3 "> Description </div>

                    <textarea  style = "height: 120px"  id ="name"  form = "ReviewCoreServlet" name="description" class="form-control">${requestScope.paper.description}${requestScope.description}</textarea>
                    <c:if test = "${not empty requestScope.descriptionError}">
                        <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            <strong>Error! </strong> ${requestScope.descriptionError} 
                        </div>
                    </c:if>


                    <div class = "mt-3"> Content </div>

                    <textarea style = "height: 200px" form = "ReviewCoreServlet" name="content" class="form-control">${requestScope.paper.content}${requestScope.content}</textarea>  
                    <c:if test = "${not empty requestScope.contentError}">
                        <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            <strong>Error! </strong> ${requestScope.contentError}
                        </div>
                    </c:if>


                    <div class ="position-relative mt-3 d-inline-block starci-horizontal-center">
                        <button type="button" class="btn btn-lg starci-button-sp me-3" data-bs-toggle="modal" data-bs-target="#myModal">
                            <span class = "h5"> Reject</span></button>
                        <button form = "ReviewCoreServlet" class ="btn  starci-button-sp btn-lg starci-background position-relative" name = "action" value = "approve"> <span class = "h5"> Approve </span></button>
                    </div>

                </section>

                <div class="modal" id="myModal">
                    <div class="modal-dialog">
                        <div class="modal-content">

                            <!-- Modal Header -->
                            <div class="modal-body">
                                Feedback
                                <button type="button" class="btn-close position-relative float-end" data-bs-dismiss="modal"></button>

                                <textarea form = "ReviewCoreServlet" name="feedback" class="form-control" cols="20" rows="5"></textarea>  
                              
                                <button form ="ReviewCoreServlet" name ="action" value ="reject" type="submit" class="mt-3 starci-horizontal-center btn  starci-button-sp btn-lg starci-background position-relative" data-bs-dismiss="modal">Send</button>
                            </div>

                        </div>
                    </div>
                </div>
            </section>  
            <section class ="p-4"></section>
        </section>
    </body>
</html>
