<%@ include file="../include/importTag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width initial-scale=1.0">
<title>Shwe Me Logistic</title>
<%@ include file="../include/csstemplate.jsp"%>
</head>
<body>
        <div class="error-form" style="text-align: center;">
          <tiles:insertAttribute name="body"></tiles:insertAttribute>
        </div>
        <%@ include file="../include/jstemplate.jsp"%>
</body>
</html>