package model

import model.db.Todo
import play.api.libs.json.Json

/**
 * Created by zwie on 04.07.16.
 */
case class SiteTodo(id: Long, text: String, finished: Boolean)

object SiteTodo {
  implicit val fmt = Json.format[SiteTodo]

  def fromDto(dto: Todo) = SiteTodo(dto.id, dto.text, dto.finished)
}