jQuery(function() {
	//$('img[@src$=.png]').ifixpng(); 
	
	/* EASING **/
	/* http://gmarwaha.com/jquery/jcarousellite/#demo */
	
	jQuery(".sliderImages").jCarouselLite({
        btnNext: ".next",
        btnPrev: ".prev",
		visible: 1,
		vertical: true,
        auto: 3000,
        speed: 1000,
		easing: "easeOutQuint"
    });
	jQuery(".sliderText").jCarouselLite({
        btnNext: ".next",
        btnPrev: ".prev",
		visible: 1, 
        auto: 3000,
        speed: 1000,
		easing: "easeOutQuint"
    });

});