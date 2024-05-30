<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
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
    <title>Новый заказ</title>
</head>
<body>
<c:if test="errors">
<h2 style="color: #ff003b">${errors}</h2>
</c:if>
<div style="width: 50%; float: left">
    <a href="/c_orders">Мои заказы</a>
</div>
<div style="width: 50%; float: right">
    <div style="float: right">
        <a href="/c_profile">Профиль</a>
    </div>
</div>

<h2 style="text-align: center;">Новый заказ</h2>
<form:form method="post" action="/new_order">
    <div style="text-align: center;">
        <table style="display: inline-block;">
            <tr>
                <th></th><th></th>
                    <th>
                        <input type="number" name="price"
                               required="required"
                               value="400"
                               placeholder="Введите стоимость доставки"
                               pattern="[1-9]{1}[0-9]{,6}"
                               title="несколько цифр от 0 до 9">
                    </th>
                <th></th><th></th>
            </tr>
            <tr>
                <th></th><th></th>
                    <th>
                        <input type="number" name="weight"
                               required="required"
                               value="0"
                               placeholder="Введите вес (не обязательно)"
                               pattern="[0-9]{,3}"
                               title="от 1 до 3 цифр от 0 до 9">
                    </th>
                <th></th><th></th>
            </tr>

            <tr>
                <td>Номер</td>
                <td>Контакт</td>
                <td>Телефон</td>
                <td>Адрес</td>
                <td>Описание</td>
            </tr>

<c:forEach items="${orderPointForm.orderPoints}" var="orderPoint" varStatus="status">
            <tr >
                <td>
                    <input disabled="disabled"
                           name="number"
                           value="${status.index + 1}">
                    <input type="hidden"
                           name="orderPoint${status.index}.numberOfPoint"
                           value="${status.index + 1}">
                </td>
                <td>
                    <input type="text"
                           name="orderPoint${status.index}.nameOfClient"
                           required="required"
                           placeholder="Введите имя"
                           pattern="[А-Яа-яЁё-\s]{,20}">
                </td>

                <td>
                    <input id="online_phone${status.index + 1}"
                           type="tel"
                           name="orderPoint${status.index}.telNumber"
                           required="required"
                           value="+7(___)___-__-__"
                           pattern="\+7\s?[\(]{0,1}9[0-9]{2}[\)]{0,1}\s?\d{3}[-]{0,1}\d{2}[-]{0,1}\d{2}"
                           placeholder="+7(___)___-__-__">
                </td >

                <td>
                    <input type="text"
                           name="orderPoint${status.index}.address"
                           required="required"
                           placeholder="Введите адрес">
                </td>

                <td>
                    <input type="text"
                           name="orderPoint${status.index}.description"
                           placeholder="Введите детали доставки (не обязательно)">
                </td>
            </tr>
</c:forEach>
            <tr>
                <td></td><td></td>
                    <td>
                        <input type="submit" value="Создать">
                    </td>
                <td></td><td></td>
            </tr>
        </table>
    </div>
</form:form>
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
        var input = document.querySelector("#online_phone1");
        input.addEventListener("input", mask, false);
        setCursorPosition(3, input);
    });

    window.addEventListener("DOMContentLoaded", function() {
        var input = document.querySelector("#online_phone2");
        input.addEventListener("input", mask, false);
        setCursorPosition(3, input);
    });
</script>