var table = "";

$(document).ready(function() {
	datatableView();

	var connectionId = Utils.getParameterByName("connectionId");
	connection = new Connection(connectionId);
	connection.getDetails(populateConnectionDetails);
});

function populateConnectionDetails(connectionDetails) {
	connectionDetailsObj = JSON.parse(connectionDetails);
	console.log(connectionDetailsObj);
	populateDatabaseList(connectionDetailsObj.authenticatedDbList);
}

function populateDatabaseList(databases) {
	var databaseListHTML = "";
	if (databases != 'undefined' || databases != "") {
		for (i = 0; i < databases.length; i++) {
			databaseListHTML += '<li><a href="#"><i class="fa fa-database fa-fw"></i> '
					+ databases[i] + '<span class="fa arrow"></span></a></li>';
		}
	}
	
	$("#side-menu").html(databaseListHTML);
}

/* json Viewer */
function formatJSON(data, id) {
	// console.log(id)
	try {
		var rawdata = '{"id":1001,"type":"donut","name":"Cake","description":"http://en.wikipedia.org/wiki/Doughnut","price":2.55,"available":{store:42,warehouse:600},"topping":[{"id":5001,"type":"None"},{"id":5002,"type":"Glazed"},{"id":5005,"type":"Sugar"},{"id":5003,"type":"Chocolate"},{"id":5004,"type":"Maple"}]}';
		var input = eval('(' + data + ' )');
	} catch (error) {
		return alert("Cannot eval JSON: " + error);
	}
	var options = {
		collapsed : false,
		withQuotes : false
	};
	$('#' + id).jsonViewer(input, options);
}

function datatableView() {
	$("#table-response-id").show();
	$("#text-resp-id").hide();
	var dataSet = [
			[
					'1',
					'{"id":1001,"type":"donut","name":"Cake","description":"http://en.wikipedia.org/wiki/Doughnut","price":2.55,"available":{store:42,warehouse:600},"topping":[{"id":5001,"type":"None"},{"id":5002,"type":"Glazed"},{"id":5005,"type":"Sugar"},{"id":5003,"type":"Chocolate"},{"id":5004,"type":"Maple"}]}' ],
			[
					'2',
					'{"id":1002,"type":"donut","name":"Cake","description":"http://en.wikipedia.org/wiki/Doughnut","price":2.55,"available":{store:42,warehouse:600},"topping":[{"id":5001,"type":"None"},{"id":5002,"type":"Glazed"},{"id":5005,"type":"Sugar"},{"id":5003,"type":"Chocolate"},{"id":5004,"type":"Maple"}]}' ] ];
	table = $("#dataTables-example").DataTable(
			{
				data : dataSet,
				columns : [ {
					title : "Serial No.",
					width : "30px"
				}, {
					title : "JSON"
				} ],
				"fnRowCallback" : function(nRow, aData, iDisplayIndex,
						iDisplayIndexFull) {
					AddToArray(aData);
					return nRow;
				}
			});

}

var jsonArray = new Array();
function AddToArray(data) {
	jsonArray.push(data);
}

function toggleTextView() {
	$("#table-response-id").hide();
	$("#text-resp-id").show();
	// get datatable data
	for ( var i in jsonArray) {
		if (jsonArray[i][0] != undefined || jsonArray[i][0] instanceof Array) {
			var jsonString = eval('(' + jsonArray[i][1] + ')');
			var contentText = '/* ' + jsonArray[i][0] + ' */<br>'
					+ JSON.stringify(jsonString, null, '\t') + '<br><br>';
			$("#json").append(contentText);
		}
	}
	$('#dataTables-example').DataTable().destroy();
}