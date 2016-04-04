class TodoListController

  toInsert =
    text : 'New todo'
    finished : false
    weight : 0

  constructor : (@$log, @todoService) ->

    @filters = 'all'
    @$log.log 'Controller ready'
    @fetchTodos()


  fetchTodos : ->
    @todoService.todoList().then (todos) =>
      @$log.log todos
      @todos = todos

  addTodo : ->
    entity = angular.copy toInsert
    entity.text = @newTodoTitle
    @todoService.addTodo(entity).then =>
      @fetchTodos()
      @newTodoTitle = ''

  updateTodo : (entity) -> @todoService.updateTodo(entity)


TodoListController.$inject = ['$log', 'todoService']


angular.module('todoApplication').controller 'todoListController', TodoListController
