<!DOCTYPE html>
<html>
<head>
    <title>Cadastro de Usu�rio/Funcion�rio</title>
</head>
<body>
    <h1>Cadastro de Usu�rio/Funcion�rio</h1>
    <form action="cadastro" method="post">
        <label for="username">Usu�rio:</label>
        <input type="text" name="username" id="username" required><br>

        <label for="password">Senha:</label>
        <input type="password" name="password" id="password" required><br>

        <label for="role">Fun��o:</label>
        <select name="role" id="role">
            <option value="Usuario">Usu�rio</option>
            <option value="Funcionario">Funcion�rio</option>
        </select><br>

        <input type="submit" value="Cadastrar">
    </form>
</body>
</html>
