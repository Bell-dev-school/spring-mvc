<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            padding:3px;
            border: none;
        }
        .first{
            cursor:pointer;
        }
        .form{
            position: relative;
            transform: translate(-50%, 0);
        }
    </style>
    <title>Доступные заказы</title>
</head>
<body>
<div style="float: right">
    <a href="/d_profile">Профиль</a>
</div>
<h3><a href="/d_orders" >Мои заказы</a>
    <span> | </span>Доступные заказы
</h3>
<div>
    <table border="1">
        <tr>
            <th>Номер заказа</th>
            <th>Имя закачика</th>
            <th>Телефон</th>
            <th>Вес</th>
            <th>Цена</th>
        </tr>
        <c:forEach  items="${mapOfFreeOrders}" var ="order" varStatus="indexOfOrder">

            <tr data-group='${indexOfOrder.count}'>
                <td>${order.key.id}</td>
                <td>${order.key.customer.name} </td>
                <td>${order.key.customer.telNumber}</td>
                <td>${order.key.weight}</td>
                <td>${order.key.price}</td>
            </tr>

            <tr data-group='${indexOfOrder.count}'>
                <th>Номер точки</th>
                <th>Адрес доставки</th>
                <th>Контакт</th>
                <th>Телефон</th>
                <th>Что нужно сделать?</th>
                <td rowspan="${indexOfOrder.count + 2}" style="text-align: center; vertical-align: middle">
                    <form method="post" action="/free_orders">
                        <input name="takenOrderId" type="hidden" value="${order.key.id}">
                        <input type="submit" value="Взять заказ">
                    </form>
                </td>
            </tr>

            <c:forEach  items="${order.value}" var ="orderpoint">
                <tr data-group='${indexOfOrder.count}'>
                    <td>${orderpoint.numberOfPoint}</td>
                    <td>${orderpoint.address}</td>
                    <td>${orderpoint.nameOfClient}</td>
                    <td>${orderpoint.telNumber}</td>
                    <td>${orderpoint.description}</td>
                </tr>
            </c:forEach>

        </c:forEach>
    </table>
</div>

<script>
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
        $(this).find("div.block").stop().slideToggle();
    });
</script>
