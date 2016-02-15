TodoListController = ($log, todoService) ->

  vm = @

  vm.filters = 'all'
  $log.log 'Controller ready'

  toInsert =
    text : 'New todo'
    finished : false
    weight : 0

  fetchTodos = ->
    todoService.todoList().then (todos) ->
      $log.log todos
      vm.todos = todos


  vm.addTodo = ->
    entity = angular.copy toInsert
    entity.text = vm.newTodoTitle
    todoService.addTodo(entity).then ->
      fetchTodos()
      vm.newTodoTitle = ''

  vm.updateTodo = (entity) -> todoService.updateTodo(entity)

  fetchTodos()

  vm


angular.module('todoApplication').controller 'todoListController', TodoListController
