// Modified Primefaces stack.js code : Stack saga kaymasin, duz bir cizgide yukari ciksin
// Must be copied into primefaces.jar\META-INF\resources\primefaces\stack
// Ozkan SARI
PrimeFaces.widget.Stack = function(E, A) {
	var B = PrimeFaces.escapeClientId(E), C = A.openSpeed, D = A.closeSpeed;
	jQuery(B + ".pf-stack>img").toggle(function() {
		var G = 0;
		var F = 0;
		var H = jQuery(this);
		H.next().children().each(function() {
			jQuery(this).animate( {
				top : "-" + G + "px",
				left : F + "px"
			}, C);
			G = G + 55;
			F = (F + 0.01) * 2
		});
		H.next().animate( {
			top : "-50px",
			left : "10px"
		}, C).addClass("openStack").find("li a>img").animate( {
			width : "50px",
			marginLeft : "9px"
		}, C);
		H.animate( {
			paddingTop : "0"
		})
	}, function() {
		var F = jQuery(this);
		F.next().removeClass("openStack").children("li").animate( {
			top : "55px",
			left : "-10px"
		}, D);
		F.next().find("li a>img").animate( {
			width : "79px",
			marginLeft : "0"
		}, D);
		F.animate( {
			paddingTop : "35"
		})
	})
};
