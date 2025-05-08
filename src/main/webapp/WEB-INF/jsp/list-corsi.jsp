<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
                    <th>Docente</th>
                    <th>Studenti Iscritti</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="corso" items="${corsi}">
                    <tr>
                        <td>${corso.id_corso}</td>
                        <td>${corso.nome}</td>
                        <td>${corso.anno_accademico}</td>
                        <td>
                            <c:if test="${corso.docente != null}">
                                ${corso.docente.nome} ${corso.docente.cognome}
                            </c:if>
                        </td>
                        <td>
                            <c:forEach var="studente" items="${corso.studenti}">
                                ${studente.nome} ${studente.cognome}<br/>
                            </c:forEach>
                        </td>
                        <td>
                            <a class="btn btn-sm btn-secondary" href="<c:url value='/corsi/${corso.id_corso}/edit'/>">Modifica</a>
                            <a class="btn btn-sm btn-danger"
                                href="<c:url value='/corsi/${corso.id_corso}/delete'/>"
                                onclick="return confirm('Sei sicuro?')">Elimina</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
