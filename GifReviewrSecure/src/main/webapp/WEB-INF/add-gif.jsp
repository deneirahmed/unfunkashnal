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
                            <li class="nav-item"><a class="nav-link" href="home">Home</a></li>
                            <li class="nav-item"><a class="nav-link active" href="add-gif">Add GIF</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div class="row">
                <div class="col">
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
                </div>
            </div>
            <div class="row mt-4">
                <div class="col">
                    <div class="card">
                        <div class="card-body mb-4">
                            <h4 class="card-title">Add GIF</h4>
                            <form action="add-gif" method="post">
                                <input type="hidden" name="token" value="<c:out value="${token}" />">
                                <div class="form-group row">
                                    <label for="title" class="col-sm-2 col-form-label text-md-right">Title</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" name="title" id="title">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="url" class="col-sm-2 col-form-label text-md-right">URL</label>
                                    <div class="col-sm-10">
                                        <input type="url" class="form-control" name="url" id="url">
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