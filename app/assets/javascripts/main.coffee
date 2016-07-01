angular.module('todoApplication', []).config( ['$compileProvider', ($compileProvider) ->
  $compileProvider.debugInfoEnabled(false)
])
