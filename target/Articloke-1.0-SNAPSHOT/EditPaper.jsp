<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="Asset/CSS/StarCiCSS4.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Pending Paper</title>
    </head>
    <body>

        <section class ="starci-full-container starci-img-background">
            <section class = "p-4"> </section>
            <form id ="EditPaperServlet" action="EditPaperServlet" enctype="multipart/form-data" method = "post"> </form>
            <input form ="EditPaperServlet" type ="hidden" name = "ID" value = "${requestScope.paper.ID}">
            <section class ="position-relative starci-horizontal-center p-4 starci-rounded shadow" style = "width: 60%; background: #EADACD"  >
                <div class = "position-relative text-center starci-font-alteration starci-horizontal-center h3"> 
                    EDIT PAPER
                </div>
         
                <div class ="row mt-5">

                    <div class ="col-6">
                        <div class="form-floating">
                            <input form = "EditPaperServlet" type="text" class="form-control" id="floatingInput" name ="title" value ="${requestScope.paper.title}" placeholder="name@example.com" >
                            <label class="form-label" for="floatingInput"> Title </label>
                        </div>
                        <c:if test = "${not empty requestScope.titleError}">
                            <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                <strong>Error! </strong> ${requestScope.titleError}
                            </div>
                        </c:if>
                        <div class="form-floating mt-3">
                            <select form = "EditPaperServlet" class="form-select" id="sel1" name ="topic">
                                <c:forEach items = "${applicationScope.topics}" var = "topic">
                                    <option <c:if test = "${requestScope.paper.topic == topic.topic}"> selected </c:if> value = "${topic.topic}"> ${topic.topic}</option>
                                </c:forEach>
                            </select>
                            <label for="sel1" class="form-label">Topic</label>
                        </div>

                        <div style = "margin-left: 0.75rem" class = "mt-3"> Picture </div>
                        <input class ="form-control" form = "EditPaperServlet" style ="width: 100%" type="file" name="picture" id="file" accept="image/*" onchange="previewImage();">   
                        <c:if test = "${not empty requestScope.pictureError}" >
                            <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                <strong>Error! </strong>   ${requestScope.pictureError}
                            </div>
                        </c:if>
                    </div> 
                    <div class ="col-6">
                        <script>
                            function previewImage() {
                                var file = document.getElementById("file").files;
                                if (file.length > 0) {
                                    var fileReader = new FileReader();

                                    fileReader.onload = function (event) {
                                        document.getElementById("preview").setAttribute("src", event.target.result);
                                        document.getElementById("hide").style.display = "none";
                                    };

                                    fileReader.readAsDataURL(file[0]);
                                }
                            }
                        </script>


                        <img class ="img-fluid" src="${requestScope.paper.picture}" id ="hide">  
                        <img  class = "img-fluid" id="preview">  

                    </div>
                </div>
                <div class ="mt-3">
                    <div style = "margin-left: 0.75rem"  class = "mb-0"> Description </div>
                    <textarea form = "EditPaperServlet" name="description" class="form-control" cols="20" rows="5">${requestScope.paper.description}</textarea>
                    <c:if test = "${not empty requestScope.descriptionError}">
                        <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            ${requestScope.descriptionError}
                        </div>
                    </c:if>
                </div>
                <div class =" mt-3">
                    <div style = "margin-left: 0.75rem"  class = "mb-0"> Content </div>
                    <textarea form = "EditPaperServlet" name="content" class="form-control" cols="20" rows="10">${requestScope.paper.content}</textarea>  

                    <c:if test = "${not empty requestScope.contentError}">
                        <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            <strong>Error! </strong>  ${requestScope.contentError}
                        </div>
                    </c:if>
                </div>

                <div class ="position-relative d-inline-block starci-horizontal-center mt-3">
                    <button form = "EditPaperServlet" class ="btn btn-lg starci-button-sp starci-background position-relative me-3" name = "action" value = "Create"> <span class = "h5"> Apply </span></button>
                      <div class="dropdown d-inline-block">
                        <a class ="starci-no-hyperlink h5" href ="#" data-bs-toggle="dropdown">
                        
                            <button class ="btn btn-lg starci-button-sp starci-background position-relative" name = "action" value = "Submit"> <span class = "h5"> Submit </span></button> 
                        </a>
                        <ul class="dropdown-menu position-absolute p-3">
                     
                              
                              <button class ="btn btn-lg starci-button-sp starci-background position-relative starci-no-break" form = "EditPaperServlet" name = "action" value = "Standard"> <span class = "h5"> Standard (Free)</span></button> 
                              <button class ="mt-3 btn btn-lg starci-button-sp starci-background position-relative starci-no-break" form = "EditPaperServlet" name = "action" value = "Advanced"> <span class = "h5"> Advanced (49$/Submission)</span></button> 

                           
                        </ul>
                    </div>
                    
                 
                </div>
            </section>
            <section class = "p-4"> </section>
        </section>


    </body>
</html>
