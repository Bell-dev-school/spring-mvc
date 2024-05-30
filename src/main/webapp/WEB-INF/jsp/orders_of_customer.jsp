<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
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
        .block{
            display:none;
            padding:3px;
            border: none;
        }
        .first{
            cursor:pointer;
        }
    </style>
    <title>Заказы</title>
</head>
<body>
<c:if test="errors">
    <h2 style="color: #ff003b">${errors}</h2>
</c:if>
<div style="float: right">
    <a href="/c_profile">Профиль</a>
</div>
<h2>Мои заказы <span> | </span>
    <a href="/new_order">Создать новый заказ</a>
</h2>
<div>
    <table border="1">
        <tr>

            <th>Номер заказа</th>
            <th>Имя курьера</th>
            <th>Телефон курьера</th>
            <th>Вес</th>
            <th>Цена</th>

        </tr>
        <c:forEach  items="${mapOfCustomerOrders}" var ="order"  varStatus="indexOfOrder">

            <tr data-group='${indexOfOrder.count}'>

                <td>${order.key.id}</td>
                <td>${order.key.deliveryman.name}</td>
                <td>${order.key.deliveryman.telNumber}</td>
                <td>${order.key.weight}</td>
                <td>${order.key.price}</td>

            </tr>
            <tr data-group='${indexOfOrder.count}'>
                <th>Номер точки</th>
                <th>Адрес доставки</th>
                <th>Контакт</th>
                <th>Телефон</th>
                <th>Что нужно сделать?</th>
            </tr>


            <c:forEach  items="${order.value}" var ="orderpoint" varStatus="indexOfPoint">
                <tr data-group='${indexOfOrder.count}'>
                    <td>${orderpoint.numberOfPoint}</td>
                    <td>${orderpoint.address} </td>
                    <td>${orderpoint.nameOfClient}</td>
                    <td>${orderpoint.telNumber}</td>
                    <td>${orderpoint.description}</td>
                </tr>
                <c:set var="nextIndexOfPoint" value="${indexOfPoint.count + 1}"/>
            </c:forEach>
            <tr data-group='${indexOfOrder.count}'>
                <form:form method="post" action="/c_orders" >
                <th style="text-align: left">${nextIndexOfPoint}
                    <input type="hidden"
                           name="orderPoint0.numberOfPoint"
                           value="${nextIndexOfPoint}">
                </th>


                <td>
                    <input type="text"
                           name="orderPoint0.address"
                           required="required"
                           placeholder="Введите адрес">
                </td>

                <td>
                    <input type="text"
                           name="orderPoint0.nameOfClient"
                           required="required"
                           placeholder="Введите имя"
                           pattern="[А-Яа-яЁё-\s]{,20}">
                </td>

                <td>
                    <input id="online_phone"
                           type="tel"
                           name="orderPoint0.telNumber"
                           required="required"
                           value="+7(___)___-__-__"
                           pattern="\+7\s?[\(]{0,1}9[0-9]{2}[\)]{0,1}\s?\d{3}[-]{0,1}\d{2}[-]{0,1}\d{2}"
                           placeholder="+7(___)___-__-__">
                </td >

                <td>
                    <input type="text"
                           name="orderPoint0.description"
                           placeholder="Введите детали доставки (не обязательно)">
                </td>
            </tr>
            <tr>
                <td></td><td></td><td></td><td></td>
                <td>
                    <input name="orderId" type="hidden" value="${order.key.id}">
                    <input name="newPrice" type="hidden" value="${400 + 150*(nextIndexOfPoint - 2)}">
                    <input type="submit" value="Сохранить">
                </td>
            </tr>
            </form:form>
        </c:forEach>
    </table>

</div>
</body>

</html>

<%--<script>
    //обработка всплывающих адресов доставки
    var d = $(document) , groupBlock = "" , el = "" , count ;

    $("[data-group]").each(function(){
        el = $(this);
        groupBlock = d.find(".container-"+el.data("group"));
        count = d.find("[data-group="+el.data("group")+"]").length ;

        if(groupBlock.length){
            el.appendTo(groupBlock);
        }else{

            if(count > 1){
                el.append("<div class='block container-"+el.data("group")+"'></div>").addClass("first")
            }
        }

    }).on('click',function(){
            $(this).find("div.block").stop().slideDown();
    });
</script>--%>
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