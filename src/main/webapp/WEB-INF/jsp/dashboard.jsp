<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>ダッシュボード</title>
<style>
body {
  background-color: #f4f4f4;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	border: 1px solid #ccc;
	border-radius: 5px;
}

h1 {
	font-size: 24px;
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 8px;
	border-bottom: 1px solid #ccc;
}

th {
	text-align: left;
}

.button-container {
	display: flex;
	align-items: center;
	justify-content: flex-end;
	margin-top: 20px;
	margin-bottom: 10px; 
}

.button-container .edit-button,
.button-container .delete-button,
.button-container .logout-button {
	padding: 8px 16px;
	border: none;
	border-radius: 4px;
	color: #fff;
	cursor: pointer;
}

.button-container .edit-button {
	background-color: #4CAF50;
	margin-right: 10px;
}

.button-container .delete-button {
	background-color: #f44336;
}

.button-container .logout-button {
	background-color: #2196F3;
	margin-right: 10px;
}

.logout-button {
	margin-left: auto; /* Move the logout button to the right */
}
</style>

</head>
<body>
	<div class="container">
		<h1>ようこそ、${user.lastName}さん</h1>
		<table>
			<tr>
				<td>会社名</td>
				<td>${user.companyName}</td>
			</tr>
			<tr>
				<td>部署名</td>
				<td>${user.departmentName}</td>
			</tr>
			<tr>
				<td>氏名</td>
				<td>${user.lastName}${user.firstName}</td>
			</tr>
			<tr>
				<td>氏名（かな）</td>
				<td>${user.lastNameKana}${user.firstNameKana}</td>
			</tr>
		</table>
		
		<div class="button-container">
			<form action="/logout" method="get">
				<button type="submit" class="logout-button">ログアウト</button>
			</form>
			<form action="/userEdit" method="post">
				<button type="submit" class="edit-button">編集</button>
			</form>
			<form action="/userDelete" method="post">
				<button type="submit" class="delete-button">削除</button>
			</form>
		</div>
		
		<div>
			<h2>メールボックス</h2>
			<!-- ここにメールボックスのコンテンツを追加してください -->
		</div>
	</div>
</body>
</html>
