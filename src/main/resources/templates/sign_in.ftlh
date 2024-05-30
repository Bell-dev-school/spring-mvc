<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Вход</title>
    <style>
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif, serif;
            font-size: 14px;
            border-collapse: collapse;
            text-align: center;
        }
        th, td:first-child {
            background: #AFCDE7;
            color: white;
            padding: 10px 20px;
        }
        th, td {
            border-style: solid;
            border-width: 0 1px 1px 0;
            border-color: white;
        }
        td {
            background: #D8E6F3;
        }
        th:first-child, td:first-child {
            text-align: left;
        }
        input {
            width: 100%;
            box-sizing:border-box;
        }
    </style>
</head>
<body>
<h2 style="text-align: center;">Вход</h2><form method="post" action="/signin">
    <div style="text-align: center;">
        <table style="display: inline-block;">
            <tr>
                <td colspan="2">
                    <input required="required"
                           type="email"
                           name="email"
                           placeholder="Введите почту">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input required="required"
                           type="password"
                           name="password"
                           placeholder="Введите пароль"
                           title="от 8 до 30 символов содержащих цифры и латинские буквы">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="Войти">
                </td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>
