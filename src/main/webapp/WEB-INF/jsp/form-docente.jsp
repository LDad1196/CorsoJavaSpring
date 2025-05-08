<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Nuovo Docente</title>
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

        <form:form method="post" modelAttribute="docente" action="${pageContext.request.contextPath}/docenti/salva">
            <form:hidden path="id_docente"/>

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
                <label for="data_di_nascita">Data di nascita:</label>
                <form:input path="data_di_nascita" type="date" id="data_di_nascita" cssClass="form-control"/>
                <form:errors path="data_di_nascita" cssClass="text-danger"/>
            </div>

            <button type="submit" class="btn btn-primary">Salva</button>
        </form:form>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
