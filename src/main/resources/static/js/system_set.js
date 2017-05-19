$(function(){
	$(".state a").click(function () {
		if($(this).hasClass("current")){
			$(this).attr('disabled',false);   
		}
		else{
			$(this).addClass("current");
			$(this).siblings().removeClass("current");
			$(this).siblings().attr('disabled',true);   
		}
		});
	})
