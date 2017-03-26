<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="header">
    <h1>Channels Update</h1>
</div>
<div class="content">
    <div class="pure-g pure-u-1">
        <form class="pure-form pure-form-aligned" method="post" action="/admin/channels/update">
            <fieldset>
                <c:if test="${entity!=null}">
                    <legend>Update Channel</legend>
                    <input name="id" value="${entity.id}" type="hidden"/>
                </c:if>
                <c:if test="${entity==null}">
                    <legend>Add New Channel</legend>
                </c:if>
                <div class="pure-control-group">
                    <label for="name">name</label>
                     <input value="${entity.name}" id="name" name="name" type="text"  class="pure-input-1-4" placeholder="channel name">
                </div>

                <div class="pure-control-group">
                    <label for="code">code</label>
                    <input
                            <c:if test="${entity!=null}">readonly='true' </c:if>
                            value="${entity.code}" id="code" name="code" type="text" class="pure-input-1-4"
                            placeholder="code,must be unique">
                </div>

                <div class="pure-control-group">
                    <label for="maxScoreTimes"><fmt:message key="label.channel.maxScoreTimes"/> </label>
                     <input value="${entity.maxScoreTimes}" name="maxScoreTimes" id="maxScoreTimes" type="text"  class="pure-input-1-4" placeholder="maxScoreTimes">
                </div>

                <div class="pure-control-group">
                    <label for="maxScoreDaily"><fmt:message key="label.channel.maxScoreDaily"/> </label>
                     <input value="${entity.maxScoreDaily}" name="maxScoreDaily" id="maxScoreDaily" type="text"  class="pure-input-1-4" placeholder="maxScoreDaily">
                </div>
                <div class="pure-control-group">
                    <label for="twiceInternal"><fmt:message key="label.channel.twiceInternal"/> </label>
                     <input value="${entity.twiceInternal}" name="twiceInternal" id="twiceInternal" type="text" class="pure-input-1-4" placeholder="twiceInternal">
                </div>
                <div class="pure-control-group">
                    <label for="dailyTimes"><fmt:message key="label.channel.dailyTimes"/> </label>
                    <input value="${entity.dailyTimes}" name="dailyTimes" id="dailyTimes" type="text" class="pure-input-1-4" placeholder="dailyTimes">
                </div>
                <div class="pure-control-group">
                    <label for="description">description</label>
                     <input value="${entity.description}" name="description" id="description" type="text" class="pure-input-1-2" placeholder="description">
                </div>
                <div class="pure-control-group">
                    <label for="order">order</label>
                     <input value="${entity.order}" name="order" id="order" type="text"  class="pure-input-1-4" placeholder="order">
                </div>
                <div class="pure-control-group">
                    <label for="type">type</label>
                    <select id="type" name="type">
                        <option value="REMOTE" <c:if test="${entity.type=='REMOTE'}">selected </c:if>>REMOTE</option>
                        <option value="LOCAL" <c:if test="${entity.type=='LOCAL'}">selected </c:if>>LOCAL</option>
                    </select>
                </div>
                <div class="pure-control-group">
                    <label for="valid">isValid(/channels)</label>
                    <select id="valid" name="valid">
                        <option value="1" <c:if test="${entity.valid}">selected </c:if>><fmt:message key="yes"/> </option>
                        <option value="0" <c:if test="${!entity.valid}">selected </c:if>><fmt:message key="no"/></option>
                    </select>
                </div>
                <div class="pure-control-group">
                    <label for="valid2">isValid2(/channelss)</label>
                    <select id="valid2" name="valid2">
                        <option value="1" <c:if test="${entity.valid2}">selected </c:if>><fmt:message key="yes"/> </option>
                        <option value="0" <c:if test="${!entity.valid2}">selected </c:if>><fmt:message key="no"/></option>
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
