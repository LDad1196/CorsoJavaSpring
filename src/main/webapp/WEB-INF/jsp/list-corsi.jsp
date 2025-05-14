<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Corsi</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<%@ include file="./components/header.jsp" %>

<main class="container mt-4">
    <h1>Elenco Corsi</h1>

    <a class="btn btn-primary mb-3" href="<c:url value='/corsi/nuovo'/>">Nuovo Corso</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Anno Accademico</th>
            <th>ID Docente</th>
            <th>Discenti Iscritti (ID)</th>
            <th>Azioni</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="corso" items="${corsi}">
            <tr>
                <td>${corso.id_corso}</td>
                <td>${corso.nome}</td>
                <td>${corso.anno_accademico}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty corso.id_docente}">
                            ${docentiMap[corso.id_docente]}
                        </c:when>
                        <c:otherwise>
                            <em>-- Nessun docente --</em>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:forEach var="idDiscente" items="${corso.id_discenti}">
                        <span class="badge text-bg-secondary me-1">${discentiMap[idDiscente]}</span>
                    </c:forEach>
                </td>
                <td>
                    <a class="btn btn-sm btn-secondary" href="<c:url value='/corsi/${corso.id_corso}/edit'/>">Modifica</a>
                    <a class="btn btn-sm btn-danger"
                       href="<c:url value='/corsi/${corso.id_corso}/delete'/>"
                       onclick="return confirm('Sei sicuro di voler eliminare questo corso?')">Elimina</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>