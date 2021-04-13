<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>GIF Reviewr</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
    </head>

    <body>
        <div class="container">
            <nav class="navbar navbar-light navbar-expand-md">
                <div class="container-fluid">
                    <a class="navbar-brand" href="home">GIF Reviewr</a>
                    <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navcol-1">
                        <ul class="navbar-nav">
                            <li class="nav-item"><a class="nav-link active" href="home">Home</a></li>
                            <li class="nav-item"><a class="nav-link" href="add-gif">Add GIF</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="row mb-3">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Find GIFs</h4>
                            <form action="home" method="get">
                                <div class="form-row">
                                    <div class="col-8">
                                        <input class="form-control" type="text" name="query" placeholder="Search query" value="<c:out value="${query}" />">
                                    </div>
                                    <div class="col-3">
                                        <select class="form-control" name="rating">
                                            <option value="0" <c:if test="${rating == null || rating == 0}">selected</c:if>>Any stars</option>
                                            <option value="1" <c:if test="${rating == 1}">selected</c:if>>At least 1 star</option>
                                            <option value="2" <c:if test="${rating == 2}">selected</c:if>>At least 2 stars</option>
                                            <option value="3" <c:if test="${rating == 3}">selected</c:if>>At least 3 stars</option>
                                            <option value="4" <c:if test="${rating == 4}">selected</c:if>>At least 4 stars</option>
                                            <option value="5" <c:if test="${rating == 5}">selected</c:if>>5 stars</option>
                                        </select>
                                    </div>
                                    <div class="col-1">
                                        <button class="btn btn-primary btn-block" type="submit">Search</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">GIFs</h4>
                            <h6 class="text-muted card-subtitle mb-2">
                                <c:out value='${String.format("Found %d GIFs", gifs.size())}' />
                            </h6>
                            <div class="row">
                                <c:forEach items="${gifs}" var="gif">
                                <div class="col-4 text-center">
                                    <a href="gif?id=${gif.getId()}">
                                        <figure class="figure">
                                            <img class="img-fluid figure-img" src="${gif.getUrl()}">
                                            <figcaption class="figure-caption">
                                                ${gif.getTitle()}
                                                <br>
                                                ${String.format("%.1f stars out of 5", gif.getAverageRating())}
                                            </figcaption>
                                        </figure>
                                    </a>
                                </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
    </body>

</html>