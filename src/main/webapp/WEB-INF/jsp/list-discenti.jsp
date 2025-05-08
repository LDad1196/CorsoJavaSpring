<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Elenco Discenti</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
    <%@ include file="./components/header.jsp" %>

    <main class="container mt-4">
        <h1>Elenco Discenti</h1>

        <a class="btn btn-primary mb-3" href="<c:url value='/discenti/nuovo'/>">Nuovo Discente</a>

        <form class="row g-3 mb-3" method="get" action="${pageContext.request.contextPath}/discenti/cerca">
            <div class="col-auto">
                <input type="text" name="nome" class="form-control" placeholder="Cerca per nome" required>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-outline-primary">Cerca</button>
            </div>
        </form>
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
                <c:forEach var="discente" items="${discenti}">
                    <tr>
                        <td>${discente.id_discente}</td>
                        <td>${discente.nome}</td>
                        <td>${discente.cognome}</td>
                        <td>${discente.matricola}</td>
                        <td>${discente.età}</td>
                        <td>${discente.città_di_residenza}</td>
                        <td>
                            <a class="btn btn-sm btn-secondary" href="<c:url value='/discenti/${discente.id_discente}/edit'/>">Modifica</a>
                            <a class="btn btn-sm btn-danger"
                                href="<c:url value='/discenti/${discente.id_discente}/delete'/>"
                                onclick="return confirm('Sei sicuro di voler eliminare questo discente?')">Elimina</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
