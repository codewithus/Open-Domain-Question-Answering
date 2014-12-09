<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="sty.css">
<title>Question Answering System Using Cloud</title>
<script>
function foc()
{
	document.frm.qus.focus();
}
</script>
</head>
<body onload="foc()">

<div id="sercont">
<form action="parse" method="get" name="frm">
Enter Your Question &nbsp;<input type="text" name="qus" class="txt">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="hidden" name="kl" value="true"> <input type="submit" value="Submit" class="but"> &nbsp;&nbsp;&nbsp;(Ex :What is cloud computing)
</form>
</div>
<div id="footer">
<p>On going final year project in Pondicherry University -Karaikal Campus Dept of computer science<br>
<font color="royalblue"> Under the guidance of<br></font>
&nbsp;&nbsp;&nbsp;<font color="black">Mr.G.Suresh Kumar</font> <br><img src="logo.png" width="150px" height="150px">
<br>
<font color="royalblue">Project Members</font><br>
<font color="black">&nbsp;&nbsp;&nbsp;Dinesh Kumar.R <br>&nbsp;&nbsp;&nbsp;Natrayan.S<br>&nbsp;&nbsp;&nbsp;Siva Shanmugam.A<br>&nbsp;&nbsp;&nbsp;Anbarasan.R</p>

</div>
<br>
<div id="footerbar">
<ul>
<li><img src="abt.png">&nbsp;&nbsp;<a href="weather.jsp?pl=chennai">Weather</a></li>
<li><img src="abt.png">&nbsp;&nbsp;<a href="about.jsp">About</a></li>
<li><img src="abt.png">&nbsp;&nbsp;<a href="about.jsp">How It Works</a></li>
<li>&nbsp;&nbsp;<img src="cont.png">&nbsp;&nbsp;<a href="contact.jsp">Contact Us</a></li>
<li>&nbsp;&nbsp;<img src="feed.png">&nbsp;&nbsp;<a href="#">Feedback</a></li>
</ul>
</div>

</body>

</html>