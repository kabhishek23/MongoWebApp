var url = window.location;

var element = $('ul.nav a').filter(function() {
	return this.href == url;
}).addClass('active').parent();

while (true) {
	if (element.is('li')) {
		element = element.parent().addClass('in').parent();
	} else {
		break;
	}
}

///* json Viewer */
//$(function() {
//
//	try {
//		var rawdata = '{"id":1001,"type":"donut","name":"Cake","description":"http://en.wikipedia.org/wiki/Doughnut","price":2.55,"available":{store:42,warehouse:600},"topping":[{"id":5001,"type":"None"},{"id":5002,"type":"Glazed"},{"id":5005,"type":"Sugar"},{"id":5003,"type":"Chocolate"},{"id":5004,"type":"Maple"}]}';
//		var input = eval('(' + rawdata + ' )');
//	} catch (error) {
//		return alert("Cannot eval JSON: " + error);
//	}
//	var options = {
//		collapsed : false,
//		withQuotes : false
//	};
//	console.log(input);
////	$("#json-renderer").html(rawdata);
//	 $('#json-renderer').jsonViewer(input, options);
//});