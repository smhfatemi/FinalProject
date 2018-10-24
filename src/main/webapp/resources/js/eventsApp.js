angular.module('eventsApp', ['ui.bootstrap', 'dialogs.main', 'pascalprecht.translate', 'ui.bootstrap.persian.datepicker','ui.bootstrap.datepicker'])
.controller('mainCtrl', ['$scope', '$http', '$timeout', '$translate', 'dialogs', function($scope, $http, $timeout, $translate, dialogs) { 

	document.getElementById("body").style.display = "block";
	
	$scope.search = function() {
		$scope.msg = undefined;
		if ($scope.startDate != undefined && $scope.endDate != undefined)
			if ($scope.startDate >= $scope.endDate) {
				$scope.msg = "start date can't be after end date";
				return;
			}
		var url = "/contacts/rest/event/search?";
		if ($scope.name != undefined)
			url += "name=" + $scope.name + "&";
		if ($scope.description != undefined)
			url += "description=" + $scope.description + "&";
		if ($scope.startDate != undefined)
			url += "startDate=" + new Date($scope.startDate).toUTCString() + "&";
		if ($scope.endDate != undefined)
			url += "endDate=" + new Date($scope.endDate).toUTCString() + "&";
		$http.get(url).then(function(response) {
			$scope.events = response.data;
		});
		$scope.name = $scope.description = $scope.startDate = $scope.endDate = undefined;
	};
	
	$scope.remove = function(e) {
		$scope.msg = undefined;
		var url = "/contacts/rest/event/delete/" + e.id;
		$http.delete(url).then(function(response) {
			if (response.data.success == true) {
				$scope.msg = e.name + " deleted";
				$scope.findAll();
			}
			else
				$scope.msg = "something went wrong";
		}, function(response) {
			$scope.msg = "something went wrong";
		});
	};
	
	$scope.findAll = function() {
		var url = "/contacts/rest/event/findAll";
		$http.get(url).then(function(response) {
				$scope.events = response.data;
		});
	};
	
	$scope.add = function() {		
		$scope.msg = undefined;
		if ($scope.startDate >= $scope.endDate) {
			$scope.msg = "start date can't be after end date";
			return;
		}
		if ($scope.name !== undefined) {	
			var event = {
					"name": $scope.name,
					"description": $scope.description,
					"startDate": $scope.startDate,
					"endDate": $scope.endDate
					};
			var url = "/contacts/rest/event/save";
			$http.post(url, event).then(function(response) {
				if (response.data.success == true) {
					$scope.msg = event.name + " saved";
					$scope.findAll();
				} else
					$scope.msg = "something went wrong";	
			}, function(response) {
				$scope.msg = "something went wrong";
			});
		} else {
			$scope.msg = "enter a name";
		}
		$scope.name = $scope.description = $scope.startDate = $scope.endDate = undefined;
	};
	
	$scope.edit = function(e) {
		$scope.msg = undefined;
		dialogs.create('editEvents.html','editCtrl', {event: e}, {}, '').result.then($scope.findAll);
		$scope.name = $scope.description = $scope.startDate = $scope.endDate = undefined;
	};
	
	$scope.openStartDateDialog = function($event) {
		$event.preventDefault();
	    $event.stopPropagation();
	    $scope.startDateDialogOpen = !$scope.startDateDialogOpen;
	}
	
	$scope.openEndDateDialog = function($event) {
		$event.preventDefault();
	    $event.stopPropagation();
	    $scope.endDateDialogOpen = !$scope.endDateDialogOpen;
	}
	
}]).controller('editCtrl', ['$scope', '$uibModalInstance', '$http', 'data',  function($scope, $uibModalInstance, $http, data) {
	$scope.data = data;
	
	$scope.update = function() {
		$scope.msg = undefined;
		if ($scope.data.event.startDate >= $scope.data.event.endDate) {
			$scope.msg = "start date can't be after end date";
			return;
		}
		var url = "/contacts/rest/event/update";
		$http.put(url, $scope.data.event).then(function(response) {
			if (response.data.success == true) {
				$scope.msg = "event updated";
			} else {
				$scope.msg = "something went wrong";
			}
		}, function(response) {
			$scope.msg = "something went wrong";
		});
	};
	
	$scope.openStartDateDialog = function($event) {
		$event.preventDefault();
	    $event.stopPropagation();
	    $scope.startDateDialogOpen = !$scope.startDateDialogOpen;
	}
	
	$scope.openEndDateDialog = function($event) {
		$event.preventDefault();
	    $event.stopPropagation();
	    $scope.endDateDialogOpen = !$scope.endDateDialogOpen;
	}
	
	$scope.exit = function() {
		$uibModalInstance.close();
	};
	
}]).filter('jalaliDate', function () {
    return function (inputDate, format) {
        var date = moment(inputDate);
//        return date.fromNow() + " " + date.format(format);
        return date.format(format);
    }
});;

