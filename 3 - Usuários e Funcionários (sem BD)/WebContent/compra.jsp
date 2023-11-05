<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>


<!DOCTYPE html>
<html>
<head>
    <title>Compra de Item</title>
</head>
<body>
    <h1>Compra de Item (Usuário)</h1>
    
    <form action="compra" method="post">
        <label for="item">Item:</label>
        <select name="item" id="item">
        <%
        // Recupere a lista de itens cadastrados pelos funcionários da sessão
         @SuppressWarnings("unchecked")
           List<String> itensFuncionarios = (List<String>) session.getAttribute("itensFuncionarios");

// Verifique se a lista de itens dos funcionários está vazia ou não existe
             if (itensFuncionarios != null && !itensFuncionarios.isEmpty()) {
                 for (String itemFuncionario : itensFuncionarios) {
             %>
             <option value="<%= itemFuncionario %>"><%= itemFuncionario %></option>
             <%
                 }
             }
             %>

        </select><br>

        <label for="quantidade">Quantidade:</label>
        <input type="number" name="quantidade" id="quantidade" min="1" required><br>

        <input type="submit" value="Comprar">
    </form>
    <a href="logout">Log Out</a>
</body>
</html>
