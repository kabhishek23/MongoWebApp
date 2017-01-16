$("#log").submit(function(e) {

	var url = "/login"; // the script where you handle the form input.

	$.ajax({
		type : "POST",
		url : url,
		data : $("#loginForm").serialize(), // serializes the form's elements.
		success : function(data) {
			console.log(data);
		}
	});

	e.preventDefault(); // avoid to execute the actual submit of the form.
});