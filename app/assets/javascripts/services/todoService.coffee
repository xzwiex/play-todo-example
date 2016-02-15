TodoService = ($http, $q) ->

  todoList = ->
    $http.get('/todo/list').then( (data) -> data.data )

  addTodo = (entity) ->
    $http.post('/todo/add', entity).then( (data) -> data.data )

  return {
    todoList : todoList
    addTodo : addTodo
  }


angular.module('todoApplication').factory 'todoService', TodoService
