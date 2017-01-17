$("#loginForm").submit(
		function(e) {

			var url = "/login"; // the script where you handle the form input.

			$.ajax({
				type : "POST",
				url : url,
				data : $("#loginForm").serialize(), // serializes the form's
				// elements.
				success : function(data) {
					var dataObj = JSON.parse(data);
					console.log(dataObj);
					if (dataObj.error) {
						window.location.href = "/index";
					} else {
						window.location.href = "/dashboard?connectionId="
								+ dataObj.connectionId;
					}
				}
			});

			e.preventDefault(); // avoid to execute the actual submit of the
			// form.
		});
