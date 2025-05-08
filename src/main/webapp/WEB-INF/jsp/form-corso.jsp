<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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

        <form:form method="post" class="mb-3" modelAttribute="corso" action="${pageContext.request.contextPath}/corsi/salva">
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
                <form:select path="docente.id_docente" cssClass="form-select" required="true">
                    <form:option value="" label="-- Seleziona Docente --"/>
                    <c:forEach var="docente" items="${docenti}">
                        <form:option value="${docente.id_docente}">${docente.nome} ${docente.cognome}</form:option>
                    </c:forEach>
                </form:select>
            </div>

            <button type="submit" class="btn btn-primary">Salva Corso</button>
        </form:form>

        <c:if test="${corso.id_corso != null}">
            <h3>Studenti Iscritti</h3>
            <ul>
                <c:forEach var="studente" items="${studentiIscritti}">
                    <li>
                        ${studente.nome} ${studente.cognome}
                        <form method="post" action="${pageContext.request.contextPath}/corsi/${corso.id_corso}/rimuoviStudente" style="display:inline;">
                            <input type="hidden" name="id_studente" value="${studente.id_studente}"/>
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Sicuro di voler rimuovere lo studente?')">Rimuovi</button>
                        </form>
                    </li>
                </c:forEach>
            </ul>

            <h4>Aggiungi Studente</h4>
            <form method="post" action="${pageContext.request.contextPath}/corsi/${corso.id_corso}/aggiungiStudente" class="row g-3">
                <div>
                    <select name="studenteId" class="form-select" required>
                        <option value="">-- Seleziona Studente --</option>
                        <c:forEach var="studente" items="${tuttiStudenti}">
                            <c:if test="${not studentiIscritti.contains(studente)}">
                                <option value="${studente.id_studente}">${studente.nome} ${studente.cognome}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <button type="submit" class="btn btn-success">Aggiungi Studente</button>
                </div>
            </form>
        </c:if>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
