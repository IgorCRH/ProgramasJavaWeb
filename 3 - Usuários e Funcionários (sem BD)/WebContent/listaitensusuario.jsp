<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <title>Tela Inicial do Usuário</title>
</head>
<body>
    <h1>Bem-vindo, Usuário</h1>
    <a href="compra.jsp">Comprar mais itens</a>
    <!-- Link de log out (invalidate a sessão) -->
    <a href="logout">Log Out</a>
    <p>Itens Comprados:</p>
    <ul>
        <%
        // Recupere a lista de itens comprados da sessão
        @SuppressWarnings("unchecked")
        List<String> itensComprados = (List<String>) session.getAttribute("itensComprados");

        // Verifique se a lista de itens comprados está vazia ou não existe
        if (itensComprados != null && !itensComprados.isEmpty()) {
            for (String itemComprado : itensComprados) {
        %>
        <li><%= itemComprado %></li>
        <%
            }
        } else {
        %>
        <li>Nenhum item comprado.</li>
        <%
        }
        %>
    </ul>
</body>
</html>
