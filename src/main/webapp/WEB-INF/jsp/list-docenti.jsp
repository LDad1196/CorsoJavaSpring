<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
    <title>Elenco Docenti</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
    <%@ include file="./components/header.jsp" %>

    <main class="container mt-4">
        <h1>Elenco Docenti</h1>
        <form class="row g-3 mb-3" method="get" action="${pageContext.request.contextPath}/docenti/cerca">
            <div class="col-auto">
                <input type="text" name="nome" class="form-control" placeholder="Cerca per nome" required>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-outline-primary">Cerca</button>
            </div>
        </form>

        <a class="btn btn-primary mb-3" href="<c:url value='/docenti/nuovo'/>">Nuovo Docente</a>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Cognome</th>
                    <th>Data di nascita</th>
                    <th>Azioni</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="docente" items="${docenti}">
                    <tr>
                        <td>${docente.id_docente}</td>
                        <td>${docente.nome}</td>
                        <td>${docente.cognome}</td>
                        <td>${docente.data_di_nascita}</td>
                        <td>
                            <a class="btn btn-sm btn-secondary" href="<c:url value='/docenti/${docente.id_docente}/edit'/>">Modifica</a>
                            <a class="btn btn-sm btn-danger"
                                href="<c:url value='/docenti/${docente.id_docente}/delete'/>"
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
