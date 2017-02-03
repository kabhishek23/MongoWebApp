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
