'use strict';

angular.module('App.Services')
.factory('TipoUsuario', ['URLS', '$resource', function(URLS, $resource) {
	return $resource(URLS.TIPO_USUARIO, { order: '@order' }, {
		all : {
			method : 'GET',
			isArray : true,
			params: { order: 'descricao' }
		}
	});
} ]);