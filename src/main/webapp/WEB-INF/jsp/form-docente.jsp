<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Docente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<%@ include file="./components/header.jsp" %>

<main class="container mt-4">
    <h1>
        <c:choose>
            <c:when test="${docente.id_docente != null}">
                Modifica Docente
            </c:when>
            <c:otherwise>
                Nuovo Docente
            </c:otherwise>
        </c:choose>
    </h1>

    <form:form method="post" action="${pageContext.request.contextPath}/docenti/salva" modelAttribute="docente" class="mb-3">
        <form:hidden path="id_docente"/>

        <div class="mb-3">
            <label for="nome" class="form-label">Nome</label>
            <form:input path="nome" cssClass="form-control" id="nome" required="true"/>
            <form:errors path="nome" cssClass="text-danger"/>
        </div>

        <div class="mb-3">
            <label for="cognome" class="form-label">Cognome</label>
            <form:input path="cognome" cssClass="form-control" id="cognome" required="true"/>
            <form:errors path="cognome" cssClass="text-danger"/>
        </div>

        <div class="mb-3">
            <label for="data_di_nascita" class="form-label">Data di Nascita</label>
            <form:input path="data_di_nascita"  type="date" cssClass="form-control" id="data_di_nascita" required="true"/>
            <form:errors path="data_di_nascita" cssClass="text-danger"/>
        </div>

        <button type="submit" class="btn btn-success">Salva</button>
        <a href="${pageContext.request.contextPath}/docenti/lista" class="btn btn-secondary">Annulla</a>
    </form:form>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>