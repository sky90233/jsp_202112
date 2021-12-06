<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includefile.jsp" %>
<%@ include file="../include/sessionCheck.jsp" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<script type="text/javascript">
	//하이퍼 링크를 클릭했을때 리스트 조회
	function Listmove(e,curpage){
		e.preventDefault();//href(클릭했을때 이동하는)기본이벤트 실행하지 않기
		var findkey = frmList.findkey.value;
		var findvalue = frmList.findvalue.value;
		console.log(findkey);
		console.log(findvalue);
		console.log(curpage);
		location.href='${path}/board/list?findkey='+findkey+
						'&findvalue='+findvalue+'&curpage='+curpage;
	}

</script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<!-- 삼항연산자 -->
	<%-- <c:out value="${page.findkey =='subcon'?'selected':'' }"/> --%>
	
	<h2>리스트</h2>
	<form name="frmList" action="${path}/board/list">
		<select name="findkey">
			<option value="email" <c:out value="${page.findkey =='email'?'selected':''}"/>>이메일</option>
			<option value="subject"<c:out value="${page.findkey =='subject'?'selected':''}"/>>제목</option>
			<option value="content"<c:out value="${page.findkey =='content'?'selected':''}"/>>내용</option>
			<option value="subcon"<c:out value="${page.findkey =='subcon'?'selected':''}"/>>제목+내용</option>
		</select>
	
		<input type="text" name="findvalue" value="${page.findvalue}">
		<button>조회</button>
	</form>
	<hr>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>작성자이메일</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성일자</th>
		</tr>
		<c:forEach var="board" items="${blist}">
			<tr>
				<td>${board.bnum}</td>
				<td>${board.email}</td>
				<td><a href="${path}/board/detail?bnum=${board.bnum}&cntplus=1">${board.subject}</a></td>
				<td>${board.readcnt}</td>
				<td><fmt:formatDate value="${board.regidate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/> </td>
			</tr>
		</c:forEach>
	</table>
	<%-- ${page} --%>
	<hr>
	<c:if test="${page.startpage != 1}">
		<a href="" onclick="Listmove(event,${page.startpage-1})">이전<< </a>
	</c:if>
	<c:forEach var="i" begin="${page.startpage}" end="${page.endpage}">
		<a href="" onclick="Listmove(event,${i})">${i}</a>		
	</c:forEach>
	<c:if test="${page.totpage>page.endpage}">
		<a href="" onclick="Listmove(event,${page.endpage+1})">>>다음 </a>
	</c:if>
	
	

</body>
</html>