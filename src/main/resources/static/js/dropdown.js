$(function () {
	$("#dropdownMenu img.up").hide();
    $("#dropdownMenu input").click(function () {
        var $box = $('#mask');
        $box.toggle();
        $("#dropdownMenu img.up").toggle();  
        $("#dropdownMenu img.down").toggle();
    });
    $("#dropdownMenu img").click(function () {
        var $box = $('#mask');
        $box.toggle();
        $("#dropdownMenu img.up").toggle();  
        $("#dropdownMenu img.down").toggle();
    });
    $("#mask li").on('click',function () {
        $("#mask").css("display", "none");
        $("#dropdownMenu input").val($(this).html());
        $("#dropdownMenu img.up").hide();  
        $("#dropdownMenu img.down").show();
        
    });
    
    
///////////////////////////////////////////////////////////////////
    $("#dropdownMenu1 img.up").hide();
    $("#dropdownMenu1 input").click(function () {
        var $box = $('#mask1');
        $box.toggle();
        $("#dropdownMenu1 img.up").toggle();  
        $("#dropdownMenu1 img.down").toggle();
    });
    $("#dropdownMenu1 img").click(function () {
        var $box = $('#mask1');
        $box.toggle();
        $("#dropdownMenu1 img.up").toggle();  
        $("#dropdownMenu1 img.down").toggle();
    });
    $("#mask1 li").on('click',function () {
        $("#mask1").css("display", "none");
        $("#dropdownMenu1 input").val($(this).html());
        $("#dropdownMenu1 img.up").hide();  
        $("#dropdownMenu1 img.down").show();
        
    });
    
//////////////////////////////////////////////////////////////////////////////////    
   	$("#dropdownMenu2 img.up").hide();
    $("#dropdownMenu2 input").click(function () {
        var $box = $('#mask2');
        $box.toggle();
        $("#dropdownMenu2 img.up").toggle();  
        $("#dropdownMenu2 img.down").toggle();
    });
    $("#dropdownMenu2 img").click(function () {
        var $box = $('#mask2');
        $box.toggle();
        $("#dropdownMenu2 img.up").toggle();  
        $("#dropdownMenu2 img.down").toggle();
    });
    $("#mask2 li").on('click',function () {
        $("#mask2").css("display", "none");
        $("#dropdownMenu2 input").val($(this).html());
        $("#dropdownMenu2 img.up").hide();  
        $("#dropdownMenu2 img.down").show();
        
    });
    
  //////////////////////////////////////////////////////////////////////////////////    
  //////////////////////////////////////////////////////////////////////////////////    
   	$("#dropdownMenu3 img.up").hide();
    $("#dropdownMenu3 input").click(function () {
        var $box = $('#mask3');
        $box.toggle();
        $("#dropdownMenu3 img.up").toggle();  
        $("#dropdownMenu3 img.down").toggle();
    });
    $("#dropdownMenu3 img").click(function () {
        var $box = $('#mask3');
        $box.toggle();
        $("#dropdownMenu3 img.up").toggle();  
        $("#dropdownMenu3 img.down").toggle();
    });
    $("#mask3 li").on('click',function () {
        $("#mask3").css("display", "none");
        $("#dropdownMenu3 input").val($(this).html());
        $("#dropdownMenu3 img.up").hide();  
        $("#dropdownMenu3 img.down").show();
        
    });
    
  //////////////////////////////////////////////////////////////////////////////////
  $("#dropdownMenu4 img.up").hide();
    $("#dropdownMenu4 input").click(function () {
        var $box = $('#mask4');
        $box.toggle();
        $("#dropdownMenu4 img.up").toggle();  
        $("#dropdownMenu4 img.down").toggle();
    });
    $("#dropdownMenu img").click(function () {
        var $box = $('#mask4');
        $box.toggle();
        $("#dropdownMenu4 img.up").toggle();  
        $("#dropdownMenu4 img.down").toggle();
    });
    $("#mask4 li").on('click',function () {
        $("#mask4").css("display", "none");
        $("#dropdownMenu4 input").val($(this).html());
        $("#dropdownMenu4 img.up").hide();  
        $("#dropdownMenu4 img.down").show();
        
    });
    
    
///////////////////////////////////////////////////////////////////
    $("#dropdownMenu5 img.up").hide();
    $("#dropdownMenu5 input").click(function () {
        var $box = $('#mask5');
        $box.toggle();
        $("#dropdownMenu6 img.up").toggle();  
        $("#dropdownMenu6 img.down").toggle();
    });
    $("#dropdownMenu5 img").click(function () {
        var $box = $('#mask5');
        $box.toggle();
        $("#dropdownMenu5 img.up").toggle();  
        $("#dropdownMenu5 img.down").toggle();
    });
    $("#mask5 li").on('click',function () {
        $("#mask5").css("display", "none");
        $("#dropdownMenu5 input").val($(this).html());
        $("#dropdownMenu5 img.up").hide();  
        $("#dropdownMenu5 img.down").show();
        
    });
    
//////////////////////////////////////////////////////////////////////////////////    
   	$("#dropdownMenu6 img.up").hide();
    $("#dropdownMenu6 input").click(function () {
        var $box = $('#mask6');
        $box.toggle();
        $("#dropdownMenu6 img.up").toggle();  
        $("#dropdownMenu6 img.down").toggle();
    });
    $("#dropdownMenu6 img").click(function () {
        var $box = $('#mask6');
        $box.toggle();
        $("#dropdownMenu6 img.up").toggle();  
        $("#dropdownMenu6 img.down").toggle();
    });
    $("#mask6 li").on('click',function () {
        $("#mask6").css("display", "none");
        $("#dropdownMenu6 input").val($(this).html());
        $("#dropdownMenu6 img.up").hide();  
        $("#dropdownMenu6 img.down").show();
        
    });
    
  //////////////////////////////////////////////////////////////////////////////////    
  //////////////////////////////////////////////////////////////////////////////////    
// 	$("#dropdownMenu3 img.up").hide();
//  $("#dropdownMenu3 input").click(function () {
//      var $box = $('#mask3');
//      $box.toggle();
//      $("#dropdownMenu3 img.up").toggle();  
//      $("#dropdownMenu3 img.down").toggle();
//  });
//  $("#dropdownMenu3 img").click(function () {
//      var $box = $('#mask2');
//      $box.toggle();
//      $("#dropdownMenu3 img.up").toggle();  
//      $("#dropdownMenu3 img.down").toggle();
//  });
//  $("#mask3 li a").on('click',function () {
//      $("#mask3").css("display", "none");
//      $("#dropdownMenu3 input").val($(this).html());
//      $("#dropdownMenu3 img.up").hide();  
//      $("#dropdownMenu3 img.down").show();
//      
//  });
    
  //////////////////////////////////////////////////////////////////////////////////
   	$("#pages img.down").hide();
    $("#pages input").click(function () {
        var $box = $('#page_mask');
        $box.toggle();
        $("#pages img.up").toggle();  
        $("#pages img.down").toggle();
    });
    $("#pages img").click(function () {
        var $box = $('#page_mask');
        $box.toggle();
        $("#pages img.up").toggle();  
        $("#pages img.down").toggle();
    });
    $("#page_mask li").on('click',function () {
        $("#page_mask").css("display", "none");
        $("#pages input").val($(this).html());
        $("#pages img.down").hide();  
        $("#pages img.up").show();
        
    });
});$(function () {
	$("#dropdownMenu img.up").hide();
    $("#dropdownMenu span").click(function () {
        var $box = $('#mask');
        $box.toggle();
        $("#dropdownMenu img.up").toggle();  
        $("#dropdownMenu img.down").toggle();
    });
    $("#dropdownMenu img").click(function () {
        var $box = $('#mask');
        $box.toggle();
        $("#dropdownMenu img.up").toggle();  
        $("#dropdownMenu img.down").toggle();
    });
    $("#mask li a").on('click',function () {
        $("#mask").css("display", "none");
        $("#dropdownMenu span").text($(this).html());
        $("#dropdownMenu img.up").hide();  
        $("#dropdownMenu img.down").show();
        
    });
    
    
///////////////////////////////////////////////////////////////////
    $("#dropdownMenu1 img.up").hide();
    $("#dropdownMenu1 span").click(function () {
        var $box = $('#mask1');
        $box.toggle();
        $("#dropdownMenu1 img.up").toggle();  
        $("#dropdownMenu1 img.down").toggle();
    });
    $("#dropdownMenu1 img").click(function () {
        var $box = $('#mask1');
        $box.toggle();
        $("#dropdownMenu1 img.up").toggle();  
        $("#dropdownMenu1 img.down").toggle();
    });
    $("#mask1 li a").on('click',function () {
        $("#mask1").css("display", "none");
        $("#dropdownMenu1 span").text($(this).html());
        $("#dropdownMenu1 img.up").hide();  
        $("#dropdownMenu1 img.down").show();
        
    });
    
//////////////////////////////////////////////////////////////////////////////////    
   	$("#dropdownMenu2 img.up").hide();
    $("#dropdownMenu2 span").click(function () {
        var $box = $('#mask2');
        $box.toggle();
        $("#dropdownMenu2 img.up").toggle();  
        $("#dropdownMenu2 img.down").toggle();
    });
    $("#dropdownMenu2 img").click(function () {
        var $box = $('#mask2');
        $box.toggle();
        $("#dropdownMenu2 img.up").toggle();  
        $("#dropdownMenu2 img.down").toggle();
    });
    $("#mask2 li a").on('click',function () {
        $("#mask2").css("display", "none");
        $("#dropdownMenu2 span").text($(this).html());
        $("#dropdownMenu2 img.up").hide();  
        $("#dropdownMenu2 img.down").show();
        
    });
    
  //////////////////////////////////////////////////////////////////////////////////    
  //////////////////////////////////////////////////////////////////////////////////    
   	$("#dropdownMenu3 img.up").hide();
    $("#dropdownMenu3 span").click(function () {
        var $box = $('#mask3');
        $box.toggle();
        $("#dropdownMenu3 img.up").toggle();  
        $("#dropdownMenu3 img.down").toggle();
    });
    $("#dropdownMenu3 img").click(function () {
        var $box = $('#mask2');
        $box.toggle();
        $("#dropdownMenu3 img.up").toggle();  
        $("#dropdownMenu3 img.down").toggle();
    });
    $("#mask3 li a").on('click',function () {
        $("#mask3").css("display", "none");
        $("#dropdownMenu3 span").text($(this).html());
        $("#dropdownMenu3 img.up").hide();  
        $("#dropdownMenu3 img.down").show();
        
    });
    
  //////////////////////////////////////////////////////////////////////////////////
   	$("#pages img.down").hide();
    $("#pages span").click(function () {
        var $box = $('#page_mask');
        $box.toggle();
        $("#pages img.up").toggle();  
        $("#pages img.down").toggle();
    });
    $("#pages img").click(function () {
        var $box = $('#page_mask');
        $box.toggle();
        $("#pages img.up").toggle();  
        $("#pages img.down").toggle();
    });
    $("#page_mask li a").on('click',function () {
        $("#page_mask").css("display", "none");
        $("#pages span").text($(this).html());
        $("#pages img.down").hide();  
        $("#pages img.up").show();
        
    });
});