beforeEach(function () {
	
	angular.mock.module("serpics.config",['$provide', function ($provide) {
		
		$provide.constant('APP_NAME','Serpics App');
		$provide.constant('APP_VERSION','0.1');
		$provide.constant("URL", "http://localhost:8080");
		$provide.constant('COOKIE_EXPIRES','20'); //[minuti]
		$provide.constant('STORE','default-store'); //Store constant for Auth Connect
		$provide.constant('TIMEOUT','15');//[minuti] 

//		$provide.config(function($logProvider){
//			$logProvider.debugEnabled(false);
//			});
    }]);
	

});