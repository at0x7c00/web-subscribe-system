<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en-us">
  <head>
    <title>我爱云直播</title>
    <style type="text/css">
    body{
    	background: #000;
   		color:#fff;
   		margin:0px;
   		padding:0px;
    }
    a,a:link,a:active,a:hover,a:visited{
    	color:#fff;
    	text-decoration: none;
    }
    .p{
    	border:1px solid #ddd;
    	padding:10px;
    	display:inline-block;
    	width:120px;
    	margin:5px;
    	font-weight:bold;
    }
    @media all and (-webkit-min-device-pixel-ratio:0) and (min-resolution: .001dpcm) { 
	    .p:hover{
	        background-image: -webkit-linear-gradient(left, #147B96, #E6D205 25%, #147B96 50%, #E6D205 75%, #147B96);
	        -webkit-background-size: 200% 100%;
	        -webkit-animation: masked-animation 4s infinite linear;
	    }
	    
	    body{
	        background-image: -webkit-linear-gradient(left, #147B96, #6dad4d 25%, #147B96 50%, #e61ca8 75%, #147B96);
	        -webkit-background-size: 200% 100%;
	        -webkit-animation: masked-animation 19s infinite linear;
	    }
	}
	@-webkit-keyframes masked-animation {
	    0%  { background-position: 0 0;}
	    100% { background-position: -100% 0;}
	}
	
	@media  (min-width:10px) and (max-width:800px) {
		.p{
			display:block;
			width:100%;
		}
	}
    </style>
  </head>
  <body>
  
  <div style="text-align:center;width:100%;background:#000;height:488px;margin:0px auto;">
  <iframe frameborder="0" width="960px" height="488px" marginheight="0" marginwidth="0" scrolling="no" src="https://cctv5zhibo.oss-cn-shanghai.aliyuncs.com/player.htm?pid=${pid}"></iframe>
  </div>
  
  <c:forEach begin="1" end="15" var="x">
  	<a href="${basePath}f/live.do?pid=cctv${x }" class="p" target="self">
  	CCTV-${x}
  	</a>
  </c:forEach>
  
  	<a href="${basePath}f/live.do?pid=cctv5plus" class="p" target="self">CCTV5赛事</a>
  	<a href="${basePath}f/live.do?pid=cctvnews" class="p" target="self">CCTV-News</a>
  	<a href="${basePath}f/live.do?pid=cctvespanol" class="p" target="self">西班牙语频道</a>
  	<a href="${basePath}f/live.do?pid=cctvfrench" class="p" target="self">法语频道</a>
  	<a href="${basePath}f/live.do?pid=cctvarabic" class="p" target="self">阿拉伯语频道</a>
  	<a href="${basePath}f/live.do?pid=cctvrussian" class="p" target="self">俄语频道</a>
  	
  </body>
</html>
