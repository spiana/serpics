describe('Testing directive', function() {
  var $compile,
      $rootScope;

  // Load the myApp module, which contains the directive
  beforeEach(module('myApp'));

  // Store references to $rootScope and $compile
  // so they are available to all tests in this describe block
  beforeEach(inject(function(_$compile_, _$rootScope_){
    // The injector unwraps the underscores (_) from around the parameter names when matching
    $compile = _$compile_;
    $rootScope = _$rootScope_;
  }));
  
  beforeEach(function () {
      module('myApp');
      element = angular.element('<my-drtv/>');
  	inject(function ($rootScope, $compile) {
          var scope = $rootScope.$new();
			scope.name = name;
			$compile(element)(scope);
			scope.$digest();
		});
  });

  it('Replaces the element with the appropriate content', function() {
	  
	  
    // Compile a piece of HTML containing the directive
    var element = $compile("<a-great-eye></a-great-eye>")($rootScope);
    // fire all the watches, so the scope expression {{1 + 1}} will be evaluated
    $rootScope.$digest();
    // Check that the compiled element contains the templated content
    expect(element.html()).toContain("lidless, wreathed in flame, 2 times");
    expect(element.text()).toBe('Hello Homer');
  });
});


//Here are a few tips:
//
//    Be sure to tell the test runner what module you are testing with beforeEach(module('myModule')).
//
//    If you have external templateUrls in your directives, you'll want to somehow pre-cache them for the test runner. The test runner can't asynchronously GET templates. In bootstrap, 
//    we inject the templates into the javascript with a build step, and make each template a module. We use grunt-html2js grunt task.
//
//    In your tests, use the inject helper in a beforeEach to inject $compile and $rootScope and any other services you'll need. Use var myScope = $rootScope.$new() 
//    to create a fresh scope for each test. You can do var myElement = $compile('<my-directive></my-directive>')(myScope); 
//    to create an instance of your directive, and have access to its element.
//
//    If a directive creates its own scope and you want to test against it, 
//    you can get access to that directive's scope by doing var directiveScope = myElement.children().scope() - It will get the element's 
//    child (the directive itself), and get the scope for that.
//
//    For testing timeouts, you can use $timeout.flush() to end all pending timeouts.
//
//    For testing promises, remember that when you resolve a promise, it will not call its then callbacks until the next digest. 
//    So in tests you have to do this a lot: deferred.resolve(); scope.$apply();.
//
//You can find tests for directives of varying complexity in the bootstrap repo. Just look in src/{directiveName}/test/.