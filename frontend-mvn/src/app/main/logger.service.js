/**
 * Logger Factory
 * @namespace Factories
 */
(function() {
  angular
      .module('serpics.logger',[])
      .factory('logger', logger);

  /**
   * @namespace Logger
   * @desc Application wide logger
   * @memberOf Factories
   */
  logger.$inject = ['$log','DEBUG','SERVICE_DEBUG'];
  /** @ngInject */
  function logger($log,DEBUG,SERVICE_DEBUG) {

	  //Factory method

      var service = {
         error: logError,
         info: logInfo,
         debug: logDebug
      };
      return service;

      ////////////


      /**
       * @name logError
       * @desc Logs errors
       * @param {String} msg Message to log
       * @returns {String}
       * @memberOf Factories.Logger
       */
      function logError(msg) {
          var loggedMsg = 'Error: ' + msg;
          $log.error(loggedMsg);
          return loggedMsg;
      }
      
      /**
       * @name logError
       * @desc Logs errors
       * @param {String} msg Message to log
       * @returns {String}
       * @memberOf Factories.Logger
       */
      function logInfo(msg) {
          var loggedMsg = 'Info: ' + msg;
          $log.info(loggedMsg);
          return loggedMsg;
      }
      
      /**
       * @name logError
       * @desc Logs errors
       * @param {String} msg Message to log
       * @returns {String}
       * @memberOf Factories.Logger
       */
      function logDebug(msg) {
          var loggedMsg = 'Debug: ' + msg;
          if(SERVICE_DEBUG === true){
        	  $log.debug(loggedMsg);
        	  }
          else if (DEBUG === true){
        	  $log.warn(loggedMsg);
          }
          return loggedMsg;
      }
  }
})();