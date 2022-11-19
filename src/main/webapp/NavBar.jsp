<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section style = "height: 60px"> 
    <nav class="navbar navbar-expand-lg navbar-light fixed-top starci-bottom-border-new shadow" style = "height: 60px; background: #F7ECE2">
        <div class="container-fluid" >
            <a class="navbar-brand py-0" href="Home.jsp">
                <div class ="position-relative d-flex" style = "height: 60px"> 
                    <img class = "position-relative" style ="margin-right: 0.5rem" src ="Asset/Image/Application/LogoIcon.png" style = "height: 60px"> 
                    <div class ="position-relative" style ="height: 60px" > 
                        <div class = "h2 mb-0 starci-font-alteration position-relative starci-vertical-center">  Articloke </div>
                    </div>
                </div>
            </a>

            <ul class="navbar-nav ms-auto py-0">
                <li class="nav-item">
                    <a class="nav-link" href="#"><span class = "h5" style = "color: #000"> About Us </span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><span class = "h5" style = "color: #000"> Contact </span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><span class = "h5" style = "color: #000"> Services </span></a>
                </li>
            </ul>
            <form class="d-flex" action = "ForwardSearchHomeKeywordServlet">
                <input class="form-control me-2 ms-5" name ="keyword" value ="${requestScope.keyword}" type="text" >

                <button type = "submit" class = "btn starci-button-sp2 border-dark" >
                    <span class = "h5"> Search </span>
                </button>

            </form>

            <c:if test = "${empty sessionScope.user}">
                <ul class="navbar-nav ms-5">
                    <li class="nav-item">
                        <a class="nav-link" href="SignIn.jsp"><span class = "h5" style = "color: #000"> Sign In </span></a>
                    </li>
                </ul>
                <form action = "SignUp.jsp">        
                    <button class="btn btn-sm btn-outline-light border-dark" type="submit"><span class = "h5" style = "color: #000"> Sign Up </span></button>
                </form>
            </c:if>
            <c:if test = "${not empty sessionScope.user}">
                <div class="ms-5 dropdown">
                    <a class ="starci-no-hyperlink h5" href ="#" data-bs-toggle="dropdown">
                        <i class="fa-regular fa-bell fa-xl"></i> 
                        <c:if test = "${not empty sessionScope.notis}"> 
                            ${sessionScope.notis.size()}
                        </c:if>
                        <c:if test = "${empty sessionScope.notis}"> 
                            0
                        </c:if>
                    </a>
                    <ul class="dropdown-menu position-absolute" style = "left:-50rem; width: 50rem;">
                        <div style ="max-height:400px; overflow: auto"> 
                        <c:forEach items="${sessionScope.notis}" var = "noti"> 
                            <li><a class="dropdown-item my-0" style ="white-space: normal !important" href="#"><div style = "font-weight: bold"> ${noti.createdDate} </div> 
                                <div style = "font-weight: bold">${noti.header} </div>
                                <div class = "text-justify"> ${noti.content} </div></a></li>
                            </c:forEach>
                   </div>
                            <div class = "p-3">
                            <form id = "currentJSP" action ="DeleteNotificationServlet">
                                <button form = "currentJSP" type = "submit" class = "btn btn-white mt-3 starci-button-sp" style = "width: 100%">
                                    <span class = "h5"> Clear Notifications </span>
                                </button>
                            </form>
                        </div>

                    </ul>
                </div>
                <div class="ms-3 dropdown">
                    <a href ="#" data-bs-toggle="dropdown">
                        <img src ="<c:if test = "${not empty sessionScope.user.picture}">${sessionScope.user.picture}</c:if>
                             <c:if test = "${empty sessionScope.user.picture}"> Asset/Image/Application/NoPicture.png </c:if>
                                 " style = "width: 40px" class = "rounded-circle">
                        </a>
                        <ul class="dropdown-menu position-absolute" style = "left: -20rem; width:20rem;">

                            <li><a class="dropdown-item h4 my-0 " href="MyProfile.jsp">Profile</a></li>
                            <li><a class="dropdown-item h4 my-0 " href="StartRepositoryServlet">Repository</a></li>
                            <li><a class="dropdown-item h4 my-0" href="SignOutServlet">Sign Out</a></li>

                           <c:if test = "${sessionScope.user.role == 'Admin'}"> 
                            <li><a class="dropdown-item h4 my-0" href="AdminStartServlet">Administrator Board</a></li>
                            </c:if> 
                            
                        <c:if test = "${sessionScope.user.role == 'Editor'}"> 
                            <li><a class="dropdown-item h4 my-0" href="StartEditorWorkspaceServlet">Editor Workspace</a></li>
                            </c:if>
                            <c:if test = "${sessionScope.user.role == 'Publisher'}"> 
                            <li>
                                <a class="dropdown-item h4 my-0" href="StartPublisherWorkspaceServlet">Publisher Workspace</a></li>
                            </c:if>
                    </ul>
                </div>
            </c:if>
        </div>
    </nav> 
</section>
