<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>Product Update</h1>
</div>
<div class="content">
    <div class="pure-g pure-u-1">
        <form class="pure-form pure-form-aligned" method="post" action="/admin/products/update">
            <fieldset>
                <c:if test="${entity!=null}">
                    <legend>Update Product</legend>
                    <input name="id" value="${entity.id}" type="hidden"/>
                </c:if>
                <c:if test="${entity==null}">
                    <legend>Add New Product</legend>
                </c:if>
                <div class="pure-control-group">
                    <label for="name">name</label>
                    <input value="${entity.name}" id="name" name="name" type="text" class="pure-input-1-4"
                           placeholder="product name">
                </div>

                <div class="pure-control-group">
                    <label for="type">type</label>
                    <select id="type" name="productType.code" <c:if test="${entity!=null}">disabled="true" </c:if>>
                        <option value="MOBILE" <c:if test="${entity.productType.code=='MOBILE'}">selected </c:if>>
                            MOBILE
                        </option>
                        <option value="ALIPAY" <c:if test="${entity.productType.code=='ALIPAY'}">selected </c:if>>
                            ALIPAY
                        </option>
                        <option value="QB" <c:if test="${entity.productType.code=='QB'}">selected </c:if>>QB</option>
                    </select>
                </div>
                <div class="pure-control-group">
                    <label for="code">code</label>
                    <input
                            <c:if test="${entity!=null}">readonly='true' </c:if>
                            value="${entity.code}" id="code" name="code" type="text" class="pure-input-1-4"
                            placeholder="code,which must be unique">
                </div>
                <div class="pure-control-group">
                    <label for="price"><fmt:message key="label.product.price"/> </label>
                    <input value="${entity.price}" name="price" id="price" type="text" class="pure-input-1-4"
                           placeholder="price">
                </div>

                <div class="pure-control-group">
                    <label for="realPrice"><fmt:message key="label.product.realPrice"/> </label>
                    <input value="${entity.realPrice}" name="realPrice" id="realPrice" type="text"
                           class="pure-input-1-4" placeholder="realPrice">
                </div>
                <div class="pure-control-group">
                    <label for="score"><fmt:message key="label.product.score"/> </label>
                    <input value="${entity.score}" name="score" id="score" type="text" class="pure-input-1-4"
                           placeholder="score">
                </div>
                <div class="pure-control-group">
                    <label for="info">info</label>
                    <input value="${entity.info}" name="info" id="info" type="text" class="pure-input-1-2"
                           placeholder="info">
                </div>
                <div class="pure-control-group">
                    <label for="dnProduct.dnId"><fmt:message key="label.product.dnProduct"/></label>
                    <input value="${entity.dnProduct.dnId}" name="dnProduct.dnId" id="dnProduct.dnId" type="text"
                           class="pure-input-1-4" placeholder="<fmt:message key="label.product.dnProduct"/>">
                </div>
                <div class="pure-control-group">
                    <label for="info"><fmt:message key="label.product.quantity"/> </label>
                    <input value="<c:out value="${entity.quantity}" default="1" />" name="quantity" id="quantity"
                           type="text" class="pure-input-1-4"
                           placeholder="quantity">
                </div>
                <div class="pure-control-group">
                    <label for="valid">isValid</label>
                    <select id="valid" name="valid">
                        <option value="1" <c:if test="${entity.valid==1}">selected </c:if>><fmt:message
                                key="yes"/></option>
                        <option value="0" <c:if test="${entity.valid==0}">selected </c:if>><fmt:message
                                key="no"/></option>
                    </select>
                </div>
                <div class="pure-controls">
                    <label class="pure-checkbox">
                    </label>
                    <button type="submit" class="pure-button pure-button-primary">Submit</button>
                    <button type="cancel" class="pure-button pure-button-primary">Cancel</button>
                </div>
            </fieldset>
        </form>
    </div>
</div>
</body>
</html>
