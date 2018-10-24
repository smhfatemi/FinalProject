angular.module('contactsApp', ['ui.bootstrap', 'dialogs.main', 'pascalprecht.translate'])
.controller('mainCtrl', function($scope, $rootScope, $http, $timeout, $translate, dialogs) { 

	document.getElementById("body").style.display = "block";
	
	$scope.search = function() {
		$scope.msg = undefined;
		var url = "/contacts/rest/contact/search?";
		if ($scope.name != undefined)
			url += "name=" + $scope.name + "&";
		if ($scope.address != undefined)
			url += "address=" + $scope.address + "&";
		if ($scope.homePhone != undefined)
			url += "homePhone=" + $scope.homePhone + "&";
		if ($scope.cellPhone != undefined)
			url += "cellPhone=" + $scope.cellPhone + "&";
		if ($scope.email != undefined)
			url += "email=" + $scope.email + "&";
		$http.get(url).then(function(response) {
			$scope.contacts = response.data;
		});
		$scope.name = $scope.address = $scope.homePhone = $scope.cellPhone = $scope.email = undefined;
	};
	
	$scope.remove = function(c) {
		$scope.msg = undefined;
		var url = "/contacts/rest/contact/delete/" + c.id;
		$http.delete(url).then(function(response) {
			if (response.data.success == true) {
				$scope.msg = c.name + " deleted";
				$scope.findAll();
			}
			else
				$scope.msg = "something went wrong";
		}, function(response) {
			$scope.msg = "something went wrong";
		});
	};
	
	$scope.findAll = function() {
		var url = "/contacts/rest/contact/findAll";
		$http.get(url).then(function(response) {
				$scope.contacts = response.data;
		});
	};
	
	$scope.add = function() {		
		$scope.msg = undefined;
		if ($scope.name !== undefined) {	
			var contact = {
					"name": $scope.name,
					"address": $scope.address,
					"homePhone": $scope.homePhone,
					"cellPhone": $scope.cellPhone,
					"email": $scope.email
					};
			var url = "/contacts/rest/contact/save";
			$http.post(url, contact).then(function(response) {
				if (response.data.success == true) {
					contact.id = response.data.data;
					$scope.findAll();
					$scope.contacts.push(contact);
					$scope.msg = contact.name + " saved";
				} else
					$scope.msg = "something went wrong";	
			}, function(response) {
				$scope.msg = "something went wrong";
			});
		} else {
			$scope.msg = "enter a name";
		}
		$scope.name = $scope.address = $scope.homePhone = $scope.cellPhone = $scope.email = undefined;
	};
	
	$scope.edit = function(c) {
		$scope.msg = undefined;
		dialogs.create('editContacts.html','editCtrl', {contact: c}, {}, '').result.then($scope.findAll);
		$scope.name = $scope.address = $scope.homePhone = $scope.cellPhone = $scope.email = undefined;
	};
	
}).controller('editCtrl', ['$scope', '$uibModalInstance', '$http', 'data',  function($scope, $uibModalInstance, $http, data) {
	$scope.data = data;
	
	$scope.update = function() {
		$scope.msg = undefined;
		var url = "/contacts/rest/contact/update";
		$http.put(url, $scope.data.contact).then(function(response) {
			if (response.data.success == true) {
				$scope.msg = "contact updated";
			} else {
				$scope.msg = "something went wrong";
			}
		}, function(response) {
			$scope.msg = "something went wrong";
		});
	};
	
	$scope.exit = function() {
		$uibModalInstance.close();
	};
	
}]);

