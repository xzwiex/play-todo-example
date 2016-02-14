TodoListController = ($log, todoService) ->
  vm = @
  $log.log 'Controller ready'

  fetchTodos = ->
    todoService.fetch().then (todos) ->
      $log.log todos
      vm.todos = todos

  fetchTodos()

  vm


angular.module('todoApplication').controller 'todoListController', TodoListController
