function showImageThumbnailFirst(fileInput){
//alert("ready");

	file =fileInput.files[0];
	reader=new FileReader();

	reader.onload=function(e){
		$('#thumbnail0').attr('src',e.target.result);
	}	
	reader.readAsDataURL(file)
}

$(document).ready(function(){
	$('#mainImage').change(function(){
		//alert("ready");
		showImageThumbnailFirst(this);
	});
	
	$('#extraImage1').change(function(){
	//	alert("ready");
		showImageThumbnailSecond(this);
	});
	
	$('#extraImage2').change(function(){
	//	alert("ready");
		showImageThumbnailThird(this);
	});
	
	$('#extraImage3').change(function(){
	//	alert("ready");
		showImageThumbnailForth(this);
	});
	
	
});

function showImageThumbnailSecond(fileInput){
	//alert("ready");

	file =fileInput.files[0];
	reader=new FileReader();

	reader.onload=function(e){
		$('#thumbnail1').attr('src',e.target.result);
	}	
	reader.readAsDataURL(file)
}
function showImageThumbnailThird(fileInput){
	//alert("ready");

	file =fileInput.files[0];
	reader=new FileReader();

	reader.onload=function(e){
		$('#thumbnail2').attr('src',e.target.result);
	}	
	reader.readAsDataURL(file)
}
function showImageThumbnailForth(fileInput){
//	alert("ready");

	file =fileInput.files[0];
	reader=new FileReader();

	reader.onload=function(e){
		$('#thumbnail3').attr('src',e.target.result);
	}	
	reader.readAsDataURL(file)
}