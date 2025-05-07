<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Nuovo Studente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body class="container mt-4">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/">Gestione Scuola</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" href="<c:url value='/studenti/lista'/>">Studenti</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value='/docenti/lista'/>">Docenti</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <h1>
            <c:choose>
                <c:when test="${studente.id_studente != null}">
                    Modifica Studente
                </c:when>
                <c:otherwise>
                    Nuovo Studente
                </c:otherwise>
            </c:choose>
        </h1>

        <form:form method="post" modelAttribute="studente" action="${pageContext.request.contextPath}/studenti/salva">
            <form:hidden path="id_studente"/>

            <div class="mb-3">
                <label for="nome">Nome:</label>
                <form:input path="nome" id="nome" cssClass="form-control"/>
                <form:errors path="nome" cssClass="text-danger"/>
            </div>

            <div class="mb-3">
                <label for="cognome">Cognome:</label>
                <form:input path="cognome" id="cognome" cssClass="form-control"/>
                <form:errors path="cognome" cssClass="text-danger"/>
            </div>

            <div class="mb-3">
                <label for="matricola">Matricola:</label>
                <form:input path="matricola" id="matricola" cssClass="form-control"/>
                <form:errors path="matricola" cssClass="text-danger"/>
            </div>

            <div class="mb-3">
                 <label for="età">Età:</label>
                 <form:input path="età" id="età" cssClass="form-control"/>
                 <form:errors path="età" cssClass="text-danger"/>
            </div>

            <div class="mb-3">
                  <label for="città_di_residenza">Città di residenza:</label>
                  <form:input path="città_di_residenza" id="città_di_residenza" cssClass="form-control"/>
                  <form:errors path="città_di_residenza" cssClass="text-danger"/>
            </div>

            <button type="submit" class="btn btn-primary">Salva</button>
        </form:form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
