<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quest game</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
</head>
<body>
<div class="d-flex justify-content-center pt-5 mw-100">
    <img src="${pageContext.request.contextPath}/img/SpeedometerMs.png" style="max-width: 18%; height: auto">
</div>
<div class="d-flex justify-content-center mw-100">
    <h1>SPEED TEST</h1>
</div>
<div class="d-flex justify-content-evenly pt-2">
    <div class="d-flex align-items-start flex-column bd-highlight pt-10" style="height: 20px">
        <p class="h4">Redis</p>
        <p class="h4">${redis}
        </p>
    </div>
    <button type="button" class="btn btn-danger btn-lg" onclick="location.href='speedTest'">Start</button>
    <div class="d-flex align-items-start flex-column bd-highlight mb-1 ml-5" style="height: 20px">
        <p class="h4">MySql</p>
        <p class="h4">${mysql}</p>
    </div>
</div>
</body>
</html>

