<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("pageTitle") %></title>
    <link href="<%= request.getContextPath() %>/assets/form-style.css" rel="stylesheet">
    <meta property="og:title" content="<%= request.getAttribute("ogTitle") %>" />
    <meta property="og:description" content="<%= request.getAttribute("ogDescription") %>" />
    <meta property="og:image" content="<%= request.getAttribute("ogImageUrl") %>" />
    <meta property="og:url" content="<%= request.getAttribute("ogPageUrl") %>" />
</head>
<body>
<main>
    <section>
        <h1>🃏 나만의 타로카드 만들기</h1>
    </section>
    <section>
        <figure>
            <img alt="main-image" src="<%= request.getContextPath() %>/assets/taro.jpeg">
        </figure>
        <form method="post">
            <label for="description">
                ⌨️ 설명 입력
            </label>
            <input id="description" type="text" name="description" placeholder="당신에 대한 설명을 적어주세요!">
            <button>결과 보기</button>
        </form>
    </section>
</main>
</body>
</html>
