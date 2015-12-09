(function(){
	//use in this modality:   <span serpics-spinner="{radius:30, width:8, length: 16}"></span>
	// to spart spin every ajax call start. Make dependencies with erpics
    angular.module('serpicsSpinner', ['angularSpinner'])
    .directive('serpicsSpinner',   ['$http', '$rootScope' ,function ($http, $rootScope){
        return {
            link: function (scope, elm, attrs)
            {
                $rootScope.spinnerActive = false;
                scope.isLoading = function () {
                    return $http.pendingRequests.length > 0;
                };

                scope.$watch(scope.isLoading, function (loading)
                {
                    $rootScope.spinnerActive = loading;
                    if(loading){
                        elm.removeClass('ng-hide');
                    }else{
                        elm.addClass('ng-hide');
                    }
                });
            }
        };

    }]);
}).call(this);