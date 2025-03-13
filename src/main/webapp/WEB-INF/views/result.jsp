<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<meta charset="UTF-8">
<head>
    <title>당신만의 타로 카드를 만들어보세요!</title>
    <link href="<%= request.getContextPath() %>/assets/form-style.css" rel="stylesheet">
    <title><%= request.getAttribute("pageTitle") %>></title>
    <link href="<%= request.getContextPath() %>/assets/form-style.css" rel="stylesheet">
    <meta property="og:title" content="<%= request.getAttribute("ogTitle") %>" />
    <meta property="og:description" content="<%= request.getAttribute("ogDescription") %>" />
    <meta property="og:image" content="<%= request.getAttribute("ogImageUrl") %>" />
    <meta property="og:url" content="<%= request.getAttribute("ogPageUrl") %>" />
</head>
<body>
<main>
    <section>
        <h1>🃏 타로카드 결과</h1>
    </section>
    <section>
<%--        <p><%= request.getAttribute("uuid") %></p>--%>
        <%
            Object data = request.getAttribute("data");
            String text = "데이터 없음";
            String imageUrl = "";

            if (data != null) {
                try {
                    // JSON 문자열을 파싱하기 위해 ObjectMapper 사용
                    ObjectMapper objectMapper = new ObjectMapper();
                    List<Map<String, Object>> dataList = objectMapper.readValue(data.toString(), List.class);

                    // JSON 배열의 첫 번째 요소에서 "text" 키의 값 가져오기
                    if (!dataList.isEmpty()) {
                        text = dataList.get(0).get("text").toString();
                        imageUrl = dataList.get(0).get("image").toString();
                    }
                } catch (Exception e) {
                    text = "JSON 파싱 오류: " + e.getMessage();
                    imageUrl = "JSON 파싱 오류: " + e.getMessage();
                }
            }
        %>

        <p style="margin-top: 20px"><%= text %></p>
        <div style="width: 100%; margin-top: 20px"><img style="width: 100%; border-radius: 10px;" alt="image" src="<%= imageUrl %>"></div>
        <div style="display: flex; align-items: center; justify-content: center; margin-top: 20px"><button onclick="location.href = '<%= request.getContextPath() %>'">다시 하기</button></div>
    </section>
</main>
</body>
</html>
