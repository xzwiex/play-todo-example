console.debug 'Hello, world'

angular.module('todoApplication', []).config( ($compileProvider) ->
  $compileProvider.debugInfoEnabled(false)
)