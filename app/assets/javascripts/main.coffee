###
require 'services/todoService'
require 'components/ulogin-panel'
require 'controllers/todoListController'
###

angular.module('todoApplication', []).config( ($compileProvider) ->
  $compileProvider.debugInfoEnabled(false)
)