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

prepareDocumentToRender = function(formattedDoc) {

}

renderDocument(sampleDocument);
renderDocument(sampleDocument);