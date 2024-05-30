<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Профиль</title>
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
<div>
    <a href="/d_orders" >Мои заказы</a>
</div>
<div style="text-align: center;"><h2 style="text-align: center;">Профиль</h2></div>

<form method="post" action="/d_profile">
    <div style="text-align: center;">
        <table style="display: inline-block;">
            <tr>
                <td colspan="2">
                    <input name="name"
                           type="text"
                           value="${user.name}"
                           required="required"
                           placeholder="Введите имя"
                           pattern="[А-Яа-яЁё-\s]{,20}">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input name="surname"
                           type="text"
                           value="${user.surname}"
                           required="required"
                           placeholder="Введите фамилию"
                           pattern="[А-Яа-яЁё-\s]{,20}">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input name="telNumber"
                           type="tel"
                           value="${user.telNumber}"
                           id="online_phone"
                           required="required"
                           placeholder="+7(___)___-__-__"
                           pattern="\+7\s?[\(]{0,1}9[0-9]{2}[\)]{0,1}\s?\d{3}[-]{0,1}\d{2}[-]{0,1}\d{2}">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input name="email"
                           type="email"
                           value="${user.email}"
                           required="required"
                           placeholder="Введите email">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input name="password"
                           type="password"
                           value=""
                           required="required"
                           placeholder="Введите пароль"
                           pattern="[a-zA-Z0-9]{8,30}"
                           title="от 8 до 30 символов содержащих цифры и латинские буквы">
                </td>
            </tr>

            <tr>
                <td colspan="2">
                    <input type="submit" value="Сохранить">
                </td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>

<script type="text/javascript">
    function setCursorPosition(pos, e) {
        if (e.setSelectionRange) e.setSelectionRange(pos, pos);
        else if (e.createTextRange) {
            var range = e.createTextRange();
            range.collapse(true);
            range.moveEnd("character", pos);
            range.moveStart("character", pos);
            range.select()
        }
    }

    function mask() {
        var matrix = this.placeholder,
            i = 0,
            def = matrix.replace(/\D/g, ""),
            val = this.value.replace(/\D/g, "");
        def.length >= val.length && (val = def);
        matrix = matrix.replace(/[_\d]/g, function(a) {
            return val.charAt(i++) || "_"
        });
        this.value = matrix;
        i = matrix.lastIndexOf(val.substr(-1));
        i < matrix.length && matrix !== this.placeholder ? i++ : i = matrix.indexOf("_");
        setCursorPosition(i, this)
    }
    window.addEventListener("DOMContentLoaded", function() {
        var input = document.querySelector("#online_phone");
        input.addEventListener("input", mask, false);
        setCursorPosition(3, input);
    });
</script>