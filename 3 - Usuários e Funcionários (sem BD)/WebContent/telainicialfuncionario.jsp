<!DOCTYPE html>
<html>
<head>
    <title>Tela Inicial do Funcionário</title>
</head>
<body>
    <h1>Bem-vindo, Funcionário</h1>
    <p>Você pode cadastrar itens aqui:</p>
    <form action="telafuncionario" method="post">
        <label for="item">Item:</label>
        <input type="text" name="item" id="item" required><br>

        <label for="quantidade">Quantidade:</label>
        <input type="number" name="quantidade" id="quantidade" required><br>

        <input type="submit" value="Cadastrar Item">
    </form>
    <a href="logout">Log Out</a>
</body>
</html>
