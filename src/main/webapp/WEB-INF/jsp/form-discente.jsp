<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Nuovo Discente</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
    <%@ include file="./components/header.jsp" %>

    <main class="container mt-4">
        <h1>
            <c:choose>
                <c:when test="${discente.id_discente != null}">
                    Modifica Discente
                </c:when>
                <c:otherwise>
                    Nuovo Discente
                </c:otherwise>
            </c:choose>
        </h1>

        <form:form method="post" modelAttribute="discente" action="${pageContext.request.contextPath}/discenti/salva">
            <form:hidden path="id_discente"/>

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
                 <label for="eta">Età:</label>
                 <form:input path="eta" id="eta" cssClass="form-control"/>
                 <form:errors path="eta" cssClass="text-danger"/>
            </div>

            <div class="mb-3">
                  <label for="citta">Città di residenza:</label>
                  <form:input path="citta" id="citta" cssClass="form-control"/>
                  <form:errors path="citta" cssClass="text-danger"/>
            </div>

            <button type="submit" class="btn btn-primary">Salva</button>
        </form:form>
    </main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
