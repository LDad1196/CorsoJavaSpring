<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Nuovo Corso</title>
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
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/corsi/lista'/>">Corsi</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
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
    <form:form method="post" modelAttribute="corso" action="${pageContext.request.contextPath}/corsi/salva">
        <form:hidden path="id_corso"/>

        <div class="mb-3">
            <label for="nome" class="form-label">Nome Corso</label>
            <input type="text" class="form-control" id="nome" name="nome" value="${corso.nome}" required>
        </div>
        <div class="mb-3">
            <div class="mb-3">
                <label for="anno_accademico" class="form-label">Anno Accademico</label>
                <select name="anno_accademico" id="anno_accademico" class="form-select" required>
                    <option value="">-- Seleziona un anno --</option>
                    <option value="2023/2024" <c:if test="${corso.anno_accademico == '2023/2024'}">selected</c:if>>2023/2024</option>
                    <option value="2024/2025" <c:if test="${corso.anno_accademico == '2024/2025'}">selected</c:if>>2024/2025</option>
                    <option value="2025/2026" <c:if test="${corso.anno_accademico == '2025/2026'}">selected</c:if>>2025/2026</option>
                </select>
            </div>
        </div>
        <div class="mb-3">
             <label for="docente" class="form-label">Docente</label>
             <select name="docente.id_docente" id="docente" class="form-select" required>
                  <option value="">-- Seleziona Docente --</option>
                  <c:forEach var="docente" items="${docenti}">
                        <option value="${docente.id_docente}"
                            <c:if test="${corso.docente != null and docente.id_docente == corso.docente.id_docente}">
                                selected
                            </c:if>>
                            ${docente.nome} ${docente.cognome}
                        </option>
                  </c:forEach>
             </select>
        </div>
        <button type="submit" class="btn btn-primary">Salva</button>
    </form:form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
