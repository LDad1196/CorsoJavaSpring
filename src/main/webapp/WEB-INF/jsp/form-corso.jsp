<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nuovo Corso</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<%@ include file="./components/header.jsp" %>

<main class="container mt-4">
    <h1>
        <c:choose>
            <c:when test="${corso.id_corso != null}">
                Modifica Corso
            </c:when>
            <c:otherwise>
                Nuovo Corso
            </c:otherwise>
        </c:choose>
    </h1>

    <form:form method="post" class="mb-3" modelAttribute="corso"
               action="${pageContext.request.contextPath}/corsi/salva">
        <form:hidden path="id_corso"/>

        <div class="mb-3">
            <label class="form-label">Nome Corso</label>
            <form:input path="nome" cssClass="form-control" required="true"/>
        </div>

        <div class="mb-3">
            <label class="form-label">Anno Accademico</label>
            <form:select path="anno_accademico" cssClass="form-select" required="true">
                <form:option value="" label="-- Seleziona un anno --"/>
                <form:option value="2023/2024"/>
                <form:option value="2024/2025"/>
                <form:option value="2025/2026"/>
            </form:select>
        </div>

        <div class="mb-3">
            <label class="form-label">Docente</label>
            <form:select path="id_docente" cssClass="form-select" required="true">
                <form:option value="" label="-- Seleziona Docente --"/>
                <c:forEach var="docente" items="${docenti}">
                    <form:option value="${docente.id_docente}">
                        ${docente.nome} ${docente.cognome}
                    </form:option>
                </c:forEach>
            </form:select>
        </div>

        <div class="mb-3">
            <label class="form-label">Discenti iscritti</label>
            <div class="row">
                <c:forEach var="discente" items="${discenti}">
                    <div class="col-md-4">
                        <div class="form-check">
                            <form:checkbox path="id_discenti" cssClass="form-check-input" value="${discente.id_discente}"/>
                            <label class="form-check-label">
                                ${discente.nome} ${discente.cognome}
                            </label>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Salva Corso</button>
    </form:form>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
