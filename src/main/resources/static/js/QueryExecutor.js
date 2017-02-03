/* codemirror */
var editor = CodeMirror.fromTextArea(document
		.getElementById("query-executor-editor"), {
	lineNumbers : false,
	matchBrackets : true,
	theme : "dracula",
	continueComments : "Enter",
	extraKeys : {
		"Ctrl-Q" : "toggleComment"
	}
});

// read user input from editor
var queryString = editor.doc.getValue();

console.log(queryString);



class Query {
	
	constructor(queryString) {
		this.queryParts = queryString.split(".");
	}
	
	get collection() {
		return this.findCollectionName();
	}
	
	findCollectionName() {
		if(this.queryParts[1] != undefined) {
			return this.queryParts[1];
		}
	}
	
	get command() {
		return this.processCommand();
	}
	
	processCommand() {
		
		if(this.queryParts[2] != undefined) {
			var commandStr = this.queryParts[2];
			return commandStr.split("(")[0];
		}
	
		
	}
	
	
	
	processQueryString() {
		
		if(this.queryParts[2] != undefined) {
			var commandStr = this.queryParts[2];
			var startIndex = commandStr.indexOf("(");
			var endIndex = commandStr.indexOf(")");
			
			return commandStr.substring(startIndex + 1, endIndex);
		}
	
		
	}
	
	get query() {
		var querystr = this.processQueryString();
		
		return querystr.split(",")[0];
	}
	
	get projection() {
		var querystr = this.processQueryString();
		
		return querystr.split(",")[1];
	}
	
	get limit() {
		return this.findLimit();
	}
	
	findLimit() {
		
			var commandStr = this.queryParts[this.findModifierIndex("limit")];
			
			if(commandStr != undefined) {
				var modifier = commandStr.substring(0, commandStr.indexOf("("));
				
				if(modifier == 'limit') {
					return commandStr.substring(commandStr.indexOf("(") + 1, commandStr.indexOf(")"));
				} 
			}
		
	}
	
	get sort() {
		return this.findSort();
	}
	
	findSort() {
		
			var commandStr = this.queryParts[this.findModifierIndex("sort")];
			
			if(commandStr != undefined) {
				
				var modifier = commandStr.substring(0, commandStr.indexOf("("));
				
				if(modifier == 'sort') {
					return commandStr.substring(commandStr.indexOf("(") + 1, commandStr.indexOf(")"));
				}
	
			}
	}
	
	
	findModifierIndex(modifierToFind) {
		
		if(this.queryParts[3] != undefined || this.queryParts[4] != undefined ) {
			var commandStr = this.queryParts[3];
			var modifier = commandStr.substring(0, commandStr.indexOf("("));
			
			if(modifier == modifierToFind) {
				return 3;
			} else {
				return 4;
			}
		}
	
		
	}
	
}

var query = new Query(queryString);
console.log(query.collection);
console.log(query.command);
console.log(query.query);
console.log(query.projection);
console.log(query.sort);
console.log(query.limit);
