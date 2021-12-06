<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
   //파라메터 메시지
   if('${param.msg}'!='')
      alert('${param.msg}');
   //requestScope 메시지
   if('${msg}'!='')
      alert('${msg}');
</script>
</head>
<body>

</body>
</html>