//beforeEach(function () {
//  jasmine.addMatchers({
//    toBePlaying: function () {
//      return {
//        compare: function (actual, expected) {
//          var player = actual;
//
//          return {
//            pass: player.currentlyPlayingSong === expected && player.isPlaying
//          };
//        }
//      };
//    }
//  });
//});


beforeEach(function() {
	//creazione di un custom matcher per restituire un messaggio d'errore
	var customMatchers = {
			toEqualMessage: function(util) {
				return {
					compare: function(actual, expected,message) {
						
				        if (expected === undefined) {
				        	expected = '';
				        }
				        var result = {};
				        result.pass = util.equals(actual, expected);
				        if (result.pass) {
				          result.message = actual + " equal to "+expected;
				        } else {
				          result.message = message;
				        }
				        return result;
				      }
				    };
				  }
				};

	jasmine.addMatchers(customMatchers);
	
});