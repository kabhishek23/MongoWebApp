$(document).ready(function() {
	$('#loginForm').formValidation({
		framework : 'bootstrap',
		excluded: [':disabled'],
		icon : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			host_ip : {
				validators : {
					notEmpty: {
                        message: 'The destination ip address is required'
                    },
                    ip: {
                        message: 'The ip address is not valid'
                    }
				}
			},
			port: {
                validators: {
                    integer: {
                        message: 'The value is not an integer',
                        // The default separators
                        thousandsSeparator: '',
                        decimalSeparator: '.'
                    }
                }
            }
		}
	});
});
