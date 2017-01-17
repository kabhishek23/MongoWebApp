class Connection {
	constructor(connectionId) {
		this.connectionId = connectionId;
	}
	
	getDetails(callback) {
		var url = "/connection/details?connectionId="+ this.connectionId;
		$.ajax({
			url : url,
			type : "GET",
			success : callback
		});
	}
}