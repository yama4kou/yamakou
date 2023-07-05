<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録確認</title>
<style>
body {
  font-family: Arial, sans-serif;
  background-color: #f4f4f4;
}

.container {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.container h2, p {
  text-align: center;
}

.container label {
  display: block;
}

.container input[type="text"],
.container input[type="password"],
.container button {
  width: 70%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

.container button {
  width: 100%;
  padding: 10px;
  background-color: #4caf50;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.container button:hover {
  background-color: #45a049;
}

.container .input-wrapper {
  display: flex;
  align-items: center;
  margin: 2px;
}

.container .input-wrapper label {
  flex-basis: 30%;
}

.container .input-wrapper input[type="text"],
.container .input-wrapper input[type="password"] {
  flex-basis: 70%;
}

.container .regist {
  margin-top: 10px;
}
</style>

</head>
<body>
	<div class="container">
		<h2>ユーザー登録確認</h2>
		<p>以下の情報で登録しますか？</p>

		<form:form
			action="${pageContext.request.contextPath}/registrationComplete"
			method="post" modelAttribute="user">

			<div class="input-wrapper">
				<label for="companyName">会社名:</label>
				<form:input path="companyName" id="companyName" readonly="true"
					value="${user.companyName}" />
			</div>

			<div class="input-wrapper">
				<label for="departmentName">所属部署名:</label>
				<form:input path="departmentName" id="departmentName"
					readonly="true" value="${user.departmentName}" />
			</div>

			<div class="input-wrapper">
				<label for="lastName">姓:</label>
				<form:input path="lastName" id="lastName" readonly="true"
					value="${user.lastName}" />
			</div>

			<div class="input-wrapper">
				<label for="firstName">名:</label>
				<form:input path="firstName" id="firstName" readonly="true"
					value="${user.firstName}" />
			</div>

			<div class="input-wrapper">
				<label for="lastNameKana">姓(フリガナ):</label>
				<form:input path="lastNameKana" id="lastNameKana" readonly="true"
					value="${user.lastNameKana}" />
			</div>

			<div class="input-wrapper">
				<label for="firstNameKana">名(フリガナ):</label>
				<form:input path="firstNameKana" id="firstNameKana" readonly="true"
					value="${user.firstNameKana}" />
			</div>

			<div class="input-wrapper">
				<label for="email">メールアドレス:</label>
				<form:input path="email" id="email" readonly="true"
					value="${user.email}" />
			</div>

			<div class="regist">
				<button type="submit">登録</button>
			</div>
		</form:form>
		<p>
			<a href="${pageContext.request.contextPath}/registrationForm">入力画面</a>へ戻る
		</p>
	</div>
</body>
</html>
