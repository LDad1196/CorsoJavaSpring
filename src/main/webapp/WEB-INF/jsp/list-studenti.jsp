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
<h1>Elenco Studenti</h1>

<a class="btn btn-primary mb-3" href="<c:url value='/studenti/nuovo'/>">Nuovo Studente</a>

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
</body>
</html>
