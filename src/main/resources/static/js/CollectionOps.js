/* JSON Formatter */

var sampleDocument = {
	"name" : "abhishek",
	"data" : {
		"age" : 30
	}
};

renderDocument = function(mongoDocument) {

	var config = {
		hoverPreviewEnabled : true,
		hoverPreviewArrayCount : 100,
		hoverPreviewFieldCount : 5,
		theme : '',
		animateOpen : true,
		animateClose : true
	}

	formatter = new JSONFormatter(mongoDocument, 1, config);

	formattedDoc = formatter.render();

	var element = document.createElement('div');
	$(element).addClass('row schema-explorer-panel');

	var documentPanelElement = document.createElement('div');
	$(documentPanelElement).addClass('document-panel');

	var panelBody = document.createElement("div");
	$(panelBody).addClass('panel-body');
	panelBody.appendChild(formattedDoc);

	documentPanelElement.appendChild(panelBody);

	element.appendChild(documentPanelElement);

	$(".schema-explorer").append(element);
};

renderDocument(sampleDocument);
renderDocument(sampleDocument);

/* find documents */

var constructFindQuery = function(collection) {
	var findQueryStr = "db.getCollection('" + collection + "').find({})";

	editor.doc.setValue(findQueryStr);

}

var fetchDocuments = function(element) {
	dbName = $(element).attr("dbName");

	// bind db with editor instance
	editor.dbInstance = dbName;

	collectionName = $(element).attr("id");

	$("#collection-cname").html(dbName + "." + collectionName);

	// update query in queryEditor
	constructFindQuery(collectionName);

	query = editor.doc.getValue();

	queryDbForDocuments(query);

}

var queryDbForDocuments = function(queryStr) {
	query = new Query(queryStr);

	var connectionId = Utils.getConnectionId();

	Mongo.find(connectionId, editor.dbInstance, query.collection,
			query.command, query.query);

}
