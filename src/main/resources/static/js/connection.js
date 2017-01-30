class Connection {
	constructor(connectionId) {
		this.connectionId = connectionId;
	}
	
	getDetails(callback) {
		var url = "/connection/details?connectionId="+ this.connectionId;
		REST.get(url, callback);
	}
}