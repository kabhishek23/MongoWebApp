$(document).ready(function () {

    // fetch connection details and populate HTML
    connection = getConnectionDetails();
    connection.getDetails(populateConnectionDetails);

});

$(function () {
    $('#menu').metisMenu({
        toggle : false
    // disable the auto collapse. Default: true.
    });
});

function getConnectionDetails() {
    var connectionId = Utils.getParameterByName("connectionId");
    return new Connection(connectionId);
}

function populateConnectionDetails(connectionDetailStr) {
    connectionDetailsObj = JSON.parse(connectionDetailStr);
    connectionDetails = connectionDetailsObj.payload;

    // Error Handler
    handleError(connectionDetailsObj);

    // database List
    var databaseList = connectionDetails.authenticatedDbList;

    // display server name
    displayServerName(connectionDetails.hostIp, connectionDetails.port);

    // populate database list
    populateDatabaseList(databaseList);

    // load Collections
    loadCollections(databaseList);
}

function handleError(connectionDetailsObj) {
    if (connectionDetailsObj.error) {
        window.location.href = "/";
    }
}

function displayServerName(serverName, portNumber) {
    var serverInfo = '<li class="sidebar-search"><div> <h4> <i class="fa fa-home"></i> '
            + serverName + ':' + portNumber + '</h4></div></li>';
    $("#menu").html(serverInfo);
}

function populateDatabaseList(databases) {
    var databaseListHTML = '';
    if (databases != undefined) {
        for (i = 0; i < databases.length; i++) {
            databaseListHTML += '<li><a class="databaseListClass" id="'
                    + databases[i]
                    + '" href="javascript:void(0)"><i class="fa fa-database fa-fw"></i> '
                    + databases[i] + '<span class="fa arrow"></span></a></li>';
        }
    }

    $("#menu").append(databaseListHTML);
}
