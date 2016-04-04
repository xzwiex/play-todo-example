class TodoService
  constructor : (@$http, @$q) ->

  todoList : ->
    @$http.get('/todo/list').then( (data) -> data.data )

  addTodo : (entity) ->
    @$http.put('/todo/add', entity).then( (data) -> data.data )

  updateTodo : (entity) ->
    @$http.post('/todo/update', entity).then( (data) -> data.data )

TodoService.$inject = ['$http', '$q' ]

angular.module('todoApplication').service 'todoService', TodoService
