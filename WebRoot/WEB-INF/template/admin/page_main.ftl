<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>TowerX管理系统</title>
<script type="text/javascript" src="${base}/template/common/js/jquery.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/jquery.blinkTitle.js"></script>
<script type="text/javascript">
$().ready( function() {
});
</script>
</head>
<frameset id="mainFrameset" name="mainFrameset" cols="189,7,*" frameborder="no" border="0" framespacing="0">
  <frame id="menuFrame" name="menuFrame" src="menu!mainMenu.action" frameborder="no" scrolling="no" noresize="noresize" />
  <frame id="middleFrame" name="middleFrame" src="page!middle.action" frameborder="no" scrolling="no" noresize="noresize" />
  <frameset id="parentFrameset" rows="39,*" cols="*" frameborder="no" border="0" framespacing="0">
  		<frame id="headerFrame" name="headerFrame" src="page!header.action" frameborder="no" scrolling="no" noresize="noresize" />
		<frame id="mainFrame" name="mainFrame" src="page!index.action" frameborder="no" noresize="noresize" />
  </frameset>
</frameset>

<!--<frameset id="parentFrameset" rows="39,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame id="headerFrame" name="headerFrame" src="page!header.action" frameborder="no" scrolling="no" noresize="noresize" />
	<frameset id="mainFrameset" name="mainFrameset" cols="219,7,*" frameborder="no" border="0" framespacing="0">
		<frame id="menuFrame" name="menuFrame" src="menu!setting.action" frameborder="no" scrolling="no" noresize="noresize" />
		<frame id="middleFrame" name="middleFrame" src="page!middle.action" frameborder="no" scrolling="no" noresize="noresize" />
		<frame id="mainFrame" name="mainFrame" src="page!index.action" frameborder="no" noresize="noresize" />
	</frameset>
</frameset>-->
<noframes>
	<body>
		noframes
	</body>
</noframes>
</html>