<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>GIF Reviewr</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha512-SfTiTlX6kk+qitfevl/7LibUOeJWlt9rbyDn92a1DqWOw9vWG2MFoays0sgObmWazO5BQPiFucnnEAjpAB+/Sw==" crossorigin="anonymous" />
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
                            <li class="nav-item"><a class="nav-link" href="home">Home</a></li>
                            <li class="nav-item"><a class="nav-link" href="add-gif">Add GIF</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            
            <c:if test="${success != null}">
            <div class="alert alert-success" role="alert">
                <c:out value="${success}" />
            </div>
            </c:if>

            <c:if test="${error != null}">
            <div class="alert alert-danger" role="alert">
                <c:out value="${error}" />
            </div>
            </c:if>
            
            <div class="row text-center">
                <div class="col">
                    <div class="card mb-4">
                        <div class="d-block mt-3">
                            <img class="img-fluid" src="<c:out value="${gif.getUrl()}" />" style="height: 350px;">
                        </div>
                        <div class="card-body">
                            <h4 class="card-title"><c:out value="${gif.getTitle()}" /></h4>
                            <h6 class="text-muted card-subtitle mb-2">
                                <c:out value='${String.format("%.1f stars out of 5 (based %d reviews).", gif.getAverageRating(), reviews.size())}' />
                            </h6>
                            
                            <c:forEach items="${reviews}" var="review">
                            <blockquote class="blockquote">
                                <p class="mb-0"><c:out value="${review.getReview()}" /></p>
                                <footer class="blockquote-footer"><c:out value="${review.getName()}" /></footer>
                            </blockquote>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Leave a Review</h4>
                            <form action="gif" method="post">
                                <input type="hidden" name="token" value="<c:out value="${token}" />">
                                <input type="hidden" name="id" value="<c:out value="${gif.getId()}" />">
                                <fieldset class="form-group row">
                                    <legend class="col-form-label col-sm-2 float-sm-left pt-0 text-md-right">Rating</legend>
                                    <div class="col-sm-10">
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="rating" id="rating1" value="1">
                                            <label class="form-check-label" for="rating1"><i class="fa fa-star"></i></label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="rating" id="rating2" value="2">
                                            <label class="form-check-label" for="rating2"><i class="fa fa-star"></i><i class="fa fa-star"></i></label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="rating" id="rating3" value="3">
                                            <label class="form-check-label" for="rating3"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="rating" id="rating4" value="4">
                                            <label class="form-check-label" for="rating4"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="rating" id="rating5" value="5">
                                            <label class="form-check-label" for="rating5"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></label>
                                        </div>
                                    </div>
                                </fieldset>
                                
                                <div class="form-group row">
                                    <label for="name" class="col-sm-2 col-form-label text-md-right">Name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="name" id="name">
                                    </div>
                                </div>
                                
                                <div class="form-group row">
                                    <label for="review" class="col-sm-2 col-form-label text-md-right">Review</label>
                                    <div class="col-sm-10">
                                        <textarea class="form-control form-control-lg" name="review" id="review"></textarea>
                                    </div>
                                </div>
                                
                                <div class="form-group row text-center">
                                    <div class="col">
                                        <button type="submit" class="btn btn-primary">Submit</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
    </body>

</html>