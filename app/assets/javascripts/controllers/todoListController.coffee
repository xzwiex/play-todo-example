TodoListController = ($log, todoService) ->
  vm = @
  $log.log 'Controller ready'

  fetchTodos = ->
    todoService.todoList().then (todos) ->
      $log.log todos
      vm.todos = todos

  fetchTodos()

  vm.addTodo = ->

    toInsert =
      text : 'New todo'
      finished : false
      weight : 0

    todoService.addTodo(toInsert).then fetchTodos

  vm


angular.module('todoApplication').controller 'todoListController', TodoListController
