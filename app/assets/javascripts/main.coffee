angular.module('todoApplication', []).config( ['$compileProvider', ($compileProvider) ->
  $compileProvider.debugInfoEnabled(false)
])


require './services/todoService.coffee'
require './controllers/todoListController.coffee'
###require './components/ulogin-panel.coffee'
###
