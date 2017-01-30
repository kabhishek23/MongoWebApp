class REST {
	
	static get(url, callback) {
		$.ajax({
			url : url,
			type : "GET",
			success : callback
		});
	}
	
	static Put(url, data, options, callback) {
		$.ajax({
			url : url,
			type : "PUT",
			data : data,
			success : callback
		});
	}
	
	static Post(url, data, options, callback) {
		$.ajax({
			url : url,
			type : "POST",
			data : data,
			success : callback
		});
	}
}