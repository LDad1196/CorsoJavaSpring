<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Studenti</title>
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

<h1>Elenco Studenti</h1>

<a class="btn btn-primary mb-3" href="<c:url value='/studenti/nuovo'/>">Nuovo Studente</a>

<form class="row g-3 mb-3" method="get" action="${pageContext.request.contextPath}/studenti/cerca">
    <div class="col-auto">
        <input type="text" name="nome" class="form-control" placeholder="Cerca per nome" required>
    </div>
    <div class="col-auto">
        <button type="submit" class="btn btn-outline-primary">Cerca</button>
    </div>
</form>
<table class="table table-striped">
    <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Matricola</th>
            <th>Età</th>
            <th>Città di residenza</th>
            <th>Azioni</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="studente" items="${studenti}">
            <tr>
                <td>${studente.id_studente}</td>
                <td>${studente.nome}</td>
                <td>${studente.cognome}</td>
                <td>${studente.matricola}</td>
                <td>${studente.età}</td>
                <td>${studente.città_di_residenza}</td>
                <td>
                    <a class="btn btn-sm btn-secondary" href="<c:url value='/studenti/${studente.id_studente}/edit'/>">Modifica</a>
                    <a class="btn btn-sm btn-danger"
                       href="<c:url value='/studenti/${studente.id_studente}/delete'/>"
                       onclick="return confirm('Sei sicuro di voler eliminare questo studente?')">Elimina</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
