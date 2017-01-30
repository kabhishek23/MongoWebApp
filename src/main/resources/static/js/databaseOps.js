//$(".nav").on(
//		"click",
//		"li a.databaseListClass",
//		function(e) {
//			var db = $(this).attr("id");
//
//			var connectionId = Utils.getConnectionId();
//
//			var getCollectionsURL = "/" + db + "/collections?connectionId="
//					+ connectionId;
//
//			REST.get(getCollectionsURL, populateCollectionsList);
//
//			e.preventDefault();
//		});
//


function populateCollectionsList(collectionsInfoStr) {

	var collectionsInfo = JSON.parse(collectionsInfoStr);

	console.log(collectionsInfo);

	if (collectionsInfo.error)
		return;

	var collectionsListHTML = '<ul class="nav nav-second-level"><li><a href="flot.html">Collections<span class="fa arrow"></span></a><ul class="nav nav-third-level">';

	var database = null;

	if (collectionsInfo != 'undefined' || collectionsInfo != "") {

		var collections = collectionsInfo.collections;
		database = collectionsInfo.db;

		for (i = 0; i < collections.length; i++) {
			collectionsListHTML += '<li class="collectionListClass" id="'
					+ collections[i] + '"><a href="#">' + collections[i]
					+ '</a></li>'
		}
	}

	collectionsListHTML += '</ul></li><li><a href="#">System<span class="fa arrow"></span></a></li><li><a href="#">Indexes<span class="fa arrow"></span></a></li></ul>';

//	$("#" + database).parent().append(collectionsListHTML);

}