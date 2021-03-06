class Utils {
	
	static getParameterByName(param) {
		var paramsArray = Utils.getUrlVars();
		
		if(param != null)
			return paramsArray[param];
		
		return null;
	}
	
	static getConnectionId() {
		return Utils.getParameterByName("connectionId");
	}
	
	static getUrlVars()
	{
	    var vars = [], hash;
	    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
	    for(var i = 0; i < hashes.length; i++)
	    {
	        hash = hashes[i].split('=');
	        vars.push(hash[0]);
	        vars[hash[0]] = hash[1];
	    }
	    return vars;
	}
}