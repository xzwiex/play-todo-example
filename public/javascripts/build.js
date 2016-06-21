!function(t){function o(e){if(n[e])return n[e].exports;var r=n[e]={exports:{},id:e,loaded:!1};return t[e].call(r.exports,r,r.exports,o),r.loaded=!0,r.exports}var n={};return o.m=t,o.c=n,o.p="public/",o(0)}([function(t,o,n){angular.module("todoApplication",[]).config(["$compileProvider",function(t){return t.debugInfoEnabled(!1)}]),n(1),n(2)},function(t,o){var n;n=function(){function t(t,o){this.$http=t,this.$q=o}return t.prototype.todoList=function(){return this.$http.get("/todo/list").then(function(t){return t.data})},t.prototype.addTodo=function(t){return this.$http.put("/todo/add",t).then(function(t){return t.data})},t.prototype.updateTodo=function(t){return this.$http.post("/todo/update",t).then(function(t){return t.data})},t}(),n.$inject=["$http","$q"],angular.module("todoApplication").service("todoService",n)},function(t,o){var n;n=function(){function t(t,o){this.$log=t,this.todoService=o,this.filters="all",this.$log.log("Controller ready"),this.fetchTodos()}var o;return o={text:"New todo",finished:!1,weight:0},t.prototype.fetchTodos=function(){return this.todoService.todoList().then(function(t){return function(o){return t.$log.log(o),t.todos=o}}(this))},t.prototype.addTodo=function(){var t;return t=angular.copy(o),t.text=this.newTodoTitle,this.todoService.addTodo(t).then(function(t){return function(){return t.fetchTodos(),t.newTodoTitle=""}}(this))},t.prototype.updateTodo=function(t){return this.todoService.updateTodo(t)},t}(),n.$inject=["$log","todoService"],angular.module("todoApplication").controller("todoListController",n)}]);
//# sourceMappingURL=build.js.map