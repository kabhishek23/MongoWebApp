function loadCollections(databaseList) {
    if (databaseList != undefined) {
        for (i = 0; i < databaseList.length; i++) {
            db = databaseList[i];
            getCollections(db);
        }
    }
}

function getCollections(database) {
    var connectionId = Utils.getConnectionId();

    var getCollectionsURL = "/" + db + "/collections?connectionId="
            + connectionId;

    REST.get(getCollectionsURL, populateCollectionsList);
}

function populateCollectionsList(collectionsInfoStr) {

    $("#menu").metisMenu('dispose');

    var collectionsInfoObj = JSON.parse(collectionsInfoStr);

    var collectionsInfo = collectionsInfoObj.payload;

    if (collectionsInfoObj.error)
        return;

    var collectionsListHTML = '<ul class="nav nav-second-level">';

    var database = null;

    if (collectionsInfo != undefined) {

        var collections = collectionsInfo.collections;

        var database = collectionsInfo.db;

        for (i = 0; i < collections.length; i++) {
            collectionsListHTML += '<li class="collectionListClass" onClick="fetchDocuments(this)" dbname="'
                    + database
                    + '" id="'
                    + collections[i]
                    + '"><a href="javascript:void(0)"><i class="fa fa-table"></i> '
                    + collections[i] + '</a></li>'
        }
    }

    collectionsListHTML += '</ul>';

    $("#" + database).parent().not(':has(ul)').append(collectionsListHTML);

    $("#menu").metisMenu();
}
