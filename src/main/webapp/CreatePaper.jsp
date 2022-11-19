<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="Asset/CSS/StarCiCSS4.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Paper</title>
    </head>
    <body>

        <section class ="starci-full-container starci-img-background">
            <section class = "p-4"> </section>
            <form id ="CreatePaperServlet" action="CreatePaperServlet" enctype="multipart/form-data" method = "post"> </form>
            <section class ="position-relative starci-horizontal-center p-4 starci-rounded shadow" style = "width: 60%; background: #EADACD"  >
                <div class = "position-relative text-center starci-font-alteration starci-horizontal-center h2"> 
                    CREATE NEW PAPER
                </div>

                <div class ="row mt-5">

                    <div class ="col-6">
                        <div class="form-floating">
                            <input form = "CreatePaperServlet" type="text" class="form-control" id="floatingInput" name ="title" value ="${requestScope.title}" placeholder="name@example.com" >
                            <label class="form-label" for="floatingInput"> Title </label>
                        </div>
                        <c:if test = "${not empty requestScope.titleError}">
                            <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                <strong>Error! </strong> ${requestScope.titleError}
                            </div>
                        </c:if>
                        <div class="form-floating mt-3">
                            <select form = "CreatePaperServlet" class="form-select" id="sel1" name ="topic">
                                <c:forEach items = "${applicationScope.topics}" var = "topic">
                                    <option <c:if test = "${requestScope.topic == topic.topic}"> selected </c:if> value = "${topic.topic}"> ${topic.topic}</option>
                                </c:forEach>
                            </select>
                            <label for="sel1" class="form-label">Topic</label>
                        </div>

                        <div style = "margin-left: 0.75rem" class = "mt-3"> Picture </div>
                        <input class ="form-control" form = "CreatePaperServlet" style ="width: 100%" type="file" name="picture" id="file" accept="image/*" onchange="previewImage();">   
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


                        <img class ="img-fluid" src="Asset/Image/Application/NoImage.png" id ="hide">  
                        <img  class = "img-fluid" id="preview">  

                    </div>
                </div>
                <div class ="mt-3">
                    <div style = "margin-left: 0.75rem"  class = "mb-0"> Description </div>
                    <textarea form = "CreatePaperServlet" name="description" class="form-control" cols="20" rows="5">${requestScope.description}</textarea>
                    <c:if test = "${not empty requestScope.descriptionError}">
                        <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            ${requestScope.descriptionError}
                        </div>
                    </c:if>
                </div>
                <div class =" mt-3">
                    <div style = "margin-left: 0.75rem"  class = "mb-0"> Content </div>
                    <textarea form = "CreatePaperServlet" name="content" class="form-control" cols="20" rows="10">${requestScope.content}</textarea>  

                    <c:if test = "${not empty requestScope.contentError}">
                        <div class="alert alert-warning alert-dismissible fade show mt-2" style = "background: #BCA28E; border: 1px solid #BCA28E">
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                            <strong>Error! </strong>  ${requestScope.contentError}
                        </div>
                    </c:if>
                </div>

                <div class ="position-relative d-inline-block starci-horizontal-center mt-3">
                    <button form = "CreatePaperServlet" class ="btn btn-lg starci-button-sp starci-background position-relative me-3" name = "action" value = "Create"> <span class = "h5"> Create </span></button>
                   


                    
                </div>
            </section>
            <section class = "p-5"> </section>
        </section>


    </body>
</html>
