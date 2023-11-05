<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Usuário/Funcionário</title>
</head>
<body>
    <h1>Cadastro de Usuário/Funcionário</h1>
    <form action="cadastro" method="post">
        <label for="username">Usuário:</label>
        <input type="text" name="username" id="username" required><br>

        <label for="password">Senha:</label>
        <input type="password" name="password" id="password" required><br>

        <label for="role">Função:</label>
        <select name="role" id="role">
            <option value="Usuario">Usuário</option>
            <option value="Funcionario">Funcionário</option>
        </select><br>

        <input type="submit" value="Cadastrar">
    </form>
</body>
</html>
