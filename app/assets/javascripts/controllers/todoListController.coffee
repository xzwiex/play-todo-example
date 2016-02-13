(->
  TodoListController = ($log, todoService) ->
    $this = @
    $log.log 'Controller ready'

    $log.log todoService
    todoService.fetch().then (todos) ->
      $log.log todos
      $this.todos = todos


  angular.module('todoApplication').controller 'todoListController', TodoListController
)()