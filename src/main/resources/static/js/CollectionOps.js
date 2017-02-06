/* JSON Formatter */

var sampleDocument = {
	"name" : "abhishek",
	"data" : {
		"age" : 30
	}
};

renderDocument = function(mongoDocument) {

	var config = {
		hoverPreviewEnabled : false,
		hoverPreviewArrayCount : 100,
		hoverPreviewFieldCount : 5,
		theme : '',
		animateOpen : true,
		animateClose : true
	}

	formatter = new JSONFormatter(mongoDocument, 1, config, mongoDocument._id);

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

	queryDbForDocuments(query, processAndRenderDocuments);

}

/**
 * Execute query, Input from QueryExecutor
 */
var executeQuery = function() {
	
	var queryStr = editor.doc.getValue();

	queryDbForDocuments(queryStr, processAndRenderDocuments);
}

var queryDbForDocuments = function(queryStr, callback) {
	query = new Query(queryStr);

	var connectionId = Utils.getConnectionId();

	Mongo.find(connectionId, editor.dbInstance, query.collection,
			query.command, query.query, callback);

}

var processAndRenderDocuments = function(documentResource) {

	$(".schema-explorer").html("");

	var documentSourceObj = JSON.parse(documentResource);

	var documentList = documentSourceObj.payload.documents;

	for (i = 0; i < documentList.length; i++) {
		renderDocument(documentList[i]);
	}
}
