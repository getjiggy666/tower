<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="minimal-ui,width=720,user-scalable=no,target-densitydpi=device-dpi">
	<script src="${base}/template/app/js/adaptive.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta name="format-detection" content="telephone=no, email=no" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="msapplication-tap-highlight" content="no">
	<meta name="screen-orientation" content="portrait">
	<meta name="x5-orientation" content="portrait">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${base}/template/app/css/base.css">
	<script src="${base}/template/app/js/jquery.js"></script>
</head>

<body>
<div class="wapcon">
	<ul id="point_list" class="point_list">


	</ul>
	<div id="pop">
		<div class="content"><p></p></div>
		<div class="btn">
			<span>拍照</span>
			<input id="imgChoose" type="file" accept="image/*" capture="camera" />
		</div>
	</div>
	<div id="img"><div class="img_con"></div></div>
</div>

<script>
var liIndex=0;
var latitude="";
var longitude="";	
	
function readURL(input,liIndex) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		var imageData = "";
		reader.onload = function (e) {
			$('.point_list li').eq(liIndex).find('div').html('<img src="'+e.target.result+'" />');
			uploadFile(e.target.result,input.files[0].name.substring(input.files[0].name.lastIndexOf('.')),liIndex);
		}
		reader.readAsDataURL(input.files[0]);
	}
}
$("#imgChoose").change(function(){
	$('#pop').hide();
  readURL(this,liIndex);
});

function uploadFile(imageData,ext,liIndex){
	getLocation();
	var scenicSpotId = $('.point_list li').eq(liIndex).attr("data-id");
	$.post("${base}/app/route!img_upload.action",
	{'imageData':imageData,
	 'ext':ext,
	 'routeCreatorRecordId':'${routeCreatorRecordId}',
	 'scenicSpotId':scenicSpotId,
	 'latitude':latitude,
	 'longitude':longitude,
	 'routeEntrants':'13659811875'
	},
	function(data){
		var d = JSON.parse(data);
		if(d.status=='true'){
			$('.point_list li').eq(liIndex).find('div').html("<img src='${base}/"+ d.filePath +  "'/>");
		}else{
			alert(d.msg);
			$('.point_list li').eq(liIndex).find('div').html("<span></span>");
			init();
		}
	});
}

// 获取线路
$(function(){
	$.post("${base}/app/route!entrants_route.action",{"routeCreatorRecordId":"${routeCreatorRecordId}"},function(data){
		var d = JSON.parse(data);
		if(d.status=='true'){
			if(d.scenicSpotList.length>0){
				for(var i=0;i<d.scenicSpotList.length;i++){
					if(d.scenicSpotList[i].imgStatus==0){
						$("#point_list").append('<li data-id="'+d.scenicSpotList[i].scenicSpotId+'" data-content="'+d.scenicSpotList[i].scenicSpotContent+'"><div><span></span></div>'+d.scenicSpotList[i].scenicSpotName+'</li>');
					}else{
						$("#point_list").append('<li data-id="'+d.scenicSpotList[i].scenicSpotId+'" data-content="'+d.scenicSpotList[i].scenicSpotContent+'"><div><img src="${base}/'+d.scenicSpotList[i].imgUrl+'" /></div>'+d.scenicSpotList[i].scenicSpotName+'</li>');
					}
				}			
			}
			
			$('.point_list li div span').click(function(){
				liIndex = $(this).parents('li').index();
				$('#pop').show();
				$('#pop .content p').html($(this).parents('li').attr("data-content"));
			});
			
			$('.point_list').on('click','img',function(){
				var theImg=$(this).attr('src');
				$('#img').css('display','table');
				$('#img .img_con').html('<img src="'+theImg+'" />')
			});
			$('#img').click(function(){
				$(this).hide();
			});
		}
	});
	getLocation();
});

function init(){
	$('.point_list li div span').click(function(){
		liIndex = $(this).parents('li').index();
		$('#pop').show();
		$('#pop .content p').html($(this).parents('li').attr("data-content"));
	});
	
	$('.point_list').on('click','img',function(){
		var theImg=$(this).attr('src');
		$('#img').css('display','table');
		$('#img .img_con').html('<img src="'+theImg+'" />')
	});
	$('#img').click(function(){
		$(this).hide();
	});
}

    function getLocation(){
        if (navigator.geolocation){
            navigator.geolocation.getCurrentPosition(showPosition);
        }else{
            console.log("Geolocation is not supported by this browser.");
        }
    }
    function showPosition(position){
       latitude = position.coords.latitude;
       longitude = position.coords.longitude;
       console.log(latitude);
       console.log(longitude);
    }
    
</script>
</body>
</html>

